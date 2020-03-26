package model.data_structures;

import java.util.Iterator;

//ACLARACIÓN PREVIA: La autoría de gran parte de estos métodos y algoritmos se encuentra completamente dada
//A los autores del libro de Algorithms 4th edition. Principalmente nos basamos en el capítulo 3.4 del mismo
//tablas de Hash. Además, se implementaron algoritmos de otras secciones del libro.

//El algoritmo de nextPrime(int) fue sacado del foro stack.overflow.
public class HashLinearProbing<K extends Comparable<K>, V extends Comparable<V>> implements IHashTable<K,V>
{
	/**
	 * Número de pares en la tabla.
	 */
	private int n;

	/**
	 * Tamaño de la tabla.
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
	 * Factor de carga máximo.
	 */
	public static double FACTOR_CARGA_MAX = 0.75;

	/**
	 * Método constructor.
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
	 * Contador del número de rehashes
	 */
	public int darNumeroRehashes()
	{
		return contador;
	}
	/**
	 * Método que retorna el número de pares de la tabla.
	 * @return Número de pares de la tabla.
	 */
	public int darNumPares()
	{
		return n;
	}

	/**
	 * Método que retorna el tamaño de la tabla.
	 * @return Tamaño de la tabla.
	 */
	public int darTamano()
	{
		return m;
	}

	/**
	 * Método que retorna el arreglo de llaves.
	 * @return Arreglo de llaves.
	 */
	public K[] darKeys()
	{
		return keys;
	}

	/**
	 * Método que retorna el arreglo de valores.
	 * @return Arreglo de valores.
	 */
	public ListaEncadenada<V>[] darValues()
	{
		return values;
	}

	/**
	 * Método que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que será agregada o buscada. 
	 * @param pValue Valor que será agregado o reemplazado. pValue no puede ser null.
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
	 * Método que agrega la llave y la cadena de valores.
	 * @param pKey Llave que será agregada.
	 * @param pValue Cadena de valores que será agregada.
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
	 * Método que retorna el valor de la llave determinada.
	 * @param pKey Llave que será buscada.
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
	 * Elimina la dupla de la llave que ingresa por parámetro,
	 * @param pKey Llave que será buscada.
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
	 * Convierte el hashCode() en un índice.
	 * @param pKey. Llave que ingresa por parámetro.
	 * @return Índice que representa el hashCode.
	 */
	public int hash(K pKey)
	{ 
		return (pKey.hashCode() & 0x7fffffff) % m;
	} 

	/**
	 * Método que cambia el tamaño de la tabla por el valor que llega por parámetro.
	 * @param pTamano Tamaño nuevo de la tabla.
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
	 * Método que indica si una llave se encuentra en el arreglo de llaves.
	 * @param key Llave que es buscada
	 * @return True si se encuentra. False en caso contrario.
	 */
	public boolean contains(K key)
	{
		return getSet(key) != null; 
	}
	
	/**
	 * Método que busca números primos para ser enviados al tamaño de la tabla de hash para mayor eficiencia en el algoritmo.
	 * @param pm Valor del rango del primo.
	 * @return Número primo más cercano.
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

}
