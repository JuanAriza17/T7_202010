package model.data_structures;

import java.util.Iterator;

public class HashSeparateChaining<K extends Comparable<K>, V extends Comparable<V>> implements IHashTable<K,V>{

	/**
	 * Número de pares en la tabla.
	 */
	private int n;

	/**
	 * Tamaño de la tabla.
	 */
	private int m;
	
	private Node[] st;
	
	public static double FACTOR_CARGA_MAX = 5.0;

	private int contador;
	
    public HashSeparateChaining(int tamano) 
    {
    	n=0;
    	m = tamano;
    	contador = 0;
    	st= new HashSeparateChaining.Node[tamano];
	}
	
    private class Node {
        private K key;
        private ListaEncadenada<V> values;
        private Node next;

        public Node(K pKey, ListaEncadenada<V> pValues, Node pNext)  {
            key  = pKey;
            values  = pValues;
            next = pNext;
        }
    }
    
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
        
		for (Node x = st[i]; x != null; x = x.next)
		{
			if (pKey.equals(x.key)) 
			{ 
				--n;
				x = x.next;
				iterator = x.values.iterator();
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