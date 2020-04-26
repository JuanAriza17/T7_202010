package model.data_structures;

import java.util.Iterator;

//ACLARACI�N PREVIA: La autor�a de gran parte de estos m�todos y algoritmos se encuentra completamente dada
//A los autores del libro de Algorithms 4th edition. Principalmente nos basamos en el cap�tulo 3.4 del mismo
//tablas de Hash. Adem�s, se implementaron algoritmos de otras secciones del libro.

//El algoritmo de nextPrime(int) fue sacado del foro stack.overflow.
public class HashLinearProbing<K extends Comparable<K>, V extends Comparable<V>> implements IHashTable<K,V>
{
	/**
	 * N�mero de pares en la tabla.
	 */
	private int n;

	/**
	 * Tama�o de la tabla.
	 */
	private int m;

	/**
	 * Arreglo de las llaves.
	 */
	private K[] keys;

	/**
	 * Numero de rehashes realizados
	 */

	private int contador;

	/**
	 * Arreglo de los valores.
	 */
	private ListaEncadenada<V>[] values;

	/**
	 * Factor de carga m�ximo.
	 */
	public static double FACTOR_CARGA_MAX = 0.75;

	/**
	 * M�todo constructor.
	 * Se inicializan los arreglos de llaves y valores.
	 */
	public HashLinearProbing(int pTamano) 
	{
		n=0;
		m=pTamano;
		keys= (K[]) new Comparable[m];
		values= new ListaEncadenada[m];
		contador = 0;
	}

	/**
	 * Contador del n�mero de rehashes
	 */
	public int darNumeroRehashes()
	{
		return contador;
	}
	/**
	 * M�todo que retorna el n�mero de pares de la tabla.
	 * @return N�mero de pares de la tabla.
	 */
	public int darNumPares()
	{
		return n;
	}

	/**
	 * M�todo que retorna el tama�o de la tabla.
	 * @return Tama�o de la tabla.
	 */
	public int darTamano()
	{
		return m;
	}

	/**
	 * M�todo que retorna el arreglo de llaves.
	 * @return Arreglo de llaves.
	 */
	public K[] darKeys()
	{
		return keys;
	}

	/**
	 * M�todo que retorna el arreglo de valores.
	 * @return Arreglo de valores.
	 */
	public ListaEncadenada<V>[] darValues()
	{
		return values;
	}

	/**
	 * M�todo que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que ser� agregada o buscada. 
	 * @param pValue Valor que ser� agregado o reemplazado. pValue no puede ser null.
	 */
	public void putInSet(K pKey, V pValue) {
		if(n/m>=FACTOR_CARGA_MAX)
		{
			rehash(nextPrime(2*m));
		}
		int i;
		for(i=hash(pKey); keys[i]!=null; i=(i+1)%m)
		{
			if(keys[i].equals(pKey))
			{
				values[i].agregar(pValue);
				return;
			}
		}

		ListaEncadenada<V> lista = new ListaEncadenada<V>();
		lista.agregarFinal(pValue);

		keys[i]=pKey;
		values[i]=lista;
		++n;

	}

	/**
	 * M�todo que agrega la llave y la cadena de valores.
	 * @param pKey Llave que ser� agregada.
	 * @param pValue Cadena de valores que ser� agregada.
	 */
	public void put(K pKey, ListaEncadenada<V> pValue) {
		if(n/m>=FACTOR_CARGA_MAX)
		{
			rehash(nextPrime(2*m));
		}
		int i;
		for(i=hash(pKey); keys[i]!=null; i=(i+1)%m)
		{
			if(keys[i].equals(pKey))
			{
				values[i]=pValue;
				return;
			}
		}
		keys[i]=pKey;
		values[i]=pValue;
		++n;
	}

	/**
	 * M�todo que retorna el valor de la llave determinada.
	 * @param pKey Llave que ser� buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> getSet(K pKey) {

		for (int i= hash(pKey); keys[i]!=null; i=(i+1)%m)
		{
			if (keys[i].equals(pKey))
			{
				return values[i].iterator();
			}
		}
		return null;
	}

	/**
	 * Elimina la dupla de la llave que ingresa por par�metro,
	 * @param pKey Llave que ser� buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> deleteSet(K pKey) {
		if(!contains(pKey))
		{
			return null;
		}
		int i=hash(pKey);
		while(!pKey.equals(keys[i]))
		{
			i=(i+1)%m;
		}
		Iterator<V> retorno=values[i].iterator();
		keys[i]=null;
		values[i]=null;
		i=(i+1)%m;
		while(keys[i]!=null)
		{
			K keyToRedo=keys[i];
			ListaEncadenada<V> valueToRedo=values[i];
			put(keyToRedo,valueToRedo);
			i=(i+1)%m;
		}
		--n;
		return retorno;


	}

	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	@Override
	public Iterator<K> keys() {
		ListaEncadenada<K> lista = new ListaEncadenada<K>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null) lista.agregarFinal(keys[i]);
		return lista.iterator();
	}

	/**
	 * Convierte el hashCode() en un �ndice.
	 * @param pKey. Llave que ingresa por par�metro.
	 * @return �ndice que representa el hashCode.
	 */
	public int hash(K pKey)
	{ 
		return (pKey.hashCode() & 0x7fffffff) % m;
	} 

	/**
	 * M�todo que cambia el tama�o de la tabla por el valor que llega por par�metro.
	 * @param pTamano Tama�o nuevo de la tabla.
	 */
	public void rehash(int pTamano)
	{
		HashLinearProbing<K, V> t;
		t = new HashLinearProbing<K, V>(pTamano);
		for (int i = 0; i < m; i++)
		{
			if (keys[i] != null)
			{
				t.put(keys[i], values[i]);
			}
		}

		keys = t.darKeys();
		values = t.darValues();
		m = t.darTamano();
		contador++;
	}

	/**
	 * M�todo que indica si una llave se encuentra en el arreglo de llaves.
	 * @param key Llave que es buscada
	 * @return True si se encuentra. False en caso contrario.
	 */
	public boolean contains(K key)
	{
		return getSet(key) != null; 
	}

	/**
	 * M�todo que busca n�meros primos para ser enviados al tama�o de la tabla de hash para mayor eficiencia en el algoritmo.
	 * @param pm Valor del rango del primo.
	 * @return N�mero primo m�s cercano.
	 */
	public int nextPrime(int pm)
	{
		int pn = pm*2;
		boolean[] isPrime = new boolean[pn+1];
		for (int i = 2; i <= pn; i++) 
		{
			isPrime[i]=true;
		}

		for (int factor = 2; factor*factor <= pn; factor++) 
		{
			if (isPrime[factor]) 
			{
				for (int j = factor; factor*j <= pn; j++) {
					isPrime[factor*j]=false;
				}
			}
		}

		int primo = 0;
		for (int i = pm; i <= pn; i++) {
			if(isPrime[i])
			{
				primo=i;
			}
		}

		return primo;
	}

	public IListaEncadenada<V> darListaValores(K pKey)
	{
		for (int i= hash(pKey); keys[i]!=null; i=(i+1)%m)
		{
			if (keys[i].equals(pKey))
			{
				return values[i];
			}
		}
		return null;
	}
}
