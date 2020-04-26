package model.data_structures;

import java.util.Iterator;

//ACLARACIÓN PREVIA: La autoría de gran parte de estos métodos y algoritmos se encuentra completamente dada
//A los autores del libro de Algorithms 4th edition. Principalmente nos basamos en el capítulo 3.4 del mismo
//tablas de Hash. Además, se implementaron algoritmos de otras secciones del libro.

//El algoritmo de nextPrime(int) fue sacado del foro stack.overflow.
public class HashSeparateChaining<K extends Comparable<K>, V extends Comparable<V>> implements IHashTable<K,V>{

	/**
	 * Número de pares en la tabla.
	 */
	private int n;

	/**
	 * Tamaño de la tabla.
	 */
	private int m;

	/**
	 * Nodo de la clase
	 */
	private Node[] st;

	/**
	 * Factor de carga
	 */
	public static double FACTOR_CARGA_MAX = 5.0;

	/**
	 * Contador de rehashes
	 */
	private int contador;

	/**
	 * Método constructor. Inicializa los pares en 0, el tamaño según llega por parámetro, el contador en 0 e inicializa la tabla.
	 * @param tamano
	 */
	public HashSeparateChaining(int tamano) 
	{
		n=0;
		m = tamano;
		contador = 0;
		st= new HashSeparateChaining.Node[tamano];
	}

	/**
	 * Clase interna que maneja el nodo.
	 * @author Juan Ariza
	 * @author Sergio Zona
	 *
	 */
	private class Node {
		/**
		 * Llave del nodo
		 */
		private K key;

		/**
		 * Lista encadenada de valores.
		 */
		private ListaEncadenada<V> values;

		/**
		 * Referencia al siguiente nodo.
		 */
		private Node next;

		/**
		 * Método constructor, inicializa los atributos por los que llegan por parámetro.
		 * @param pKey Llave que será inicializada.
		 * @param pValues Lista encadenada de valores que será inicializada.
		 * @param pNext Nodo siguiente que será inicializado.
		 */
		public Node(K pKey, ListaEncadenada<V> pValues, Node pNext)  {
			key  = pKey;
			values  = pValues;
			next = pNext;
		}

	}

	/**
	 * Método que retorn el número de rehashes
	 * @return Número de rehashes.
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
	 * Método que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que será agregada o buscada. 
	 * @param pValue Valor que será agregado o reemplazado. pValue no puede ser null.
	 */
	public void putInSet(K pKey, V pValue) 
	{
		if(n/m>=FACTOR_CARGA_MAX)
		{
			rehash(nextPrime(2*m));
		}

		int i = hash(pKey);

		Node x = st[i];
		while(x!=null)
		{
			if (pKey.equals(x.key)) 
			{ 
				x.values.agregarFinal(pValue); 
				return; 
			}

			x = x.next;
		}

		ListaEncadenada<V> lista = new ListaEncadenada<V>();
		lista.agregarFinal(pValue);		
		st[i] = new Node(pKey, lista, st[i]); 
		++n;
	}

	/**
	 * Método que agrega la llave y la cadena de valores.
	 * @param pKey Llave que será agregada.
	 * @param pValue Cadena de valores que será agregada.
	 */
	public void put(K pKey, ListaEncadenada<V> pValue) 
	{
		if(n/m>=FACTOR_CARGA_MAX)
		{
			rehash(nextPrime(2*m));
		}

		int i = hash(pKey);
		for (Node x = st[i]; x != null; x = x.next)
		{
			if (pKey.equals(x.key)) 
			{ 
				x.values = pValue; 
				return; 
			}
		}

		st[i] = new Node(pKey, pValue, st[i]);
		n++;
	}

	/**
	 * Método que retorna el valor de la llave determinada.
	 * @param pKey Llave que será buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> getSet(K pKey) 
	{
		int i = hash(pKey);
		for (Node x = st[i]; x != null; x = x.next)
			if (pKey.equals(x.key))
				return x.values.iterator();

		return null; 
	}

	/**
	 * Elimina la dupla de la llave que ingresa por parámetro,
	 * @param pKey Llave que será buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> deleteSet(K pKey)
	{
		if(!contains(pKey))
		{
			return null;
		}
		int i = hash(pKey);

		Iterator<V> iterator = null;
		Node a=st[i];
		Node x=a.next;
		if(pKey.equals(a.key))
		{
			iterator=a.values.iterator();
			st[i]=st[i].next;
			--n;
		}
		else
		{
			while(x!=null)
			{
				if (pKey.equals(x.key)) 
				{ 
					--n;
					iterator = x.values.iterator();
					x=null;
				}
				a=x;
				x=x.next;


			}
		}

		return iterator;
	}

	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	public Iterator<K> keys() 
	{
		ListaEncadenada<K> lista = new ListaEncadenada<K>();
		for (int i = 0; i < m; i++)
			if (st[i] != null) lista.agregarFinal(st[i].key);
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
	 * Método que modifica el tamaño de la tabla de ser necesario si se queda sin espacio.
	 * @param chains Cadena de valores que será agregada.
	 */
	public void rehash(int chains) 
	{
		HashSeparateChaining<K, V> temp = new HashSeparateChaining<K,V>(chains);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < st.length; j++)
			{
				if(st[j]!=null)
				{
					Node x = st[j];
					while(x!=null)
					{
						temp.put(x.key, x.values);
						x = x.next;
					}
				}
			}
		}
		m = temp.darTamano();
		n = temp.darNumPares();
		st = temp.st;
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

	public IListaEncadenada<V> darListaValores(K pKey)
	{
		int i = hash(pKey);
		for (Node x = st[i]; x != null; x = x.next)
			if (pKey.equals(x.key))
				return x.values;

		return null;
	}

}