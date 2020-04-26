package model.data_structures;

import java.util.Iterator;

public interface IHashTable<K extends Comparable<K>, V extends Comparable<V>>
{
	/**
	 * M�todo que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que ser� agregada o buscada. 
	 * @param pValue Valor que ser� agregado o reemplazado. pValue no puede ser null.
	 */
	public void putInSet(K pKey, V pValue);

	/**
	 * M�todo que retorna el valor de la llave determinada.
	 * @param pKey Llave que ser� buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> getSet(K pKey);

	/**
	 * Elimina la dupla de la llave que ingresa por par�metro,
	 * @param pKey Llave que ser� buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> deleteSet(K pKey);

	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	Iterator<K> keys();

	/**
	 * Convierte el hashCode() en un �ndice.
	 * @param pKey. Llave que ingresa por par�metro.
	 * @return �ndice que representa el hashCode.
	 */
	public int hash(K pKey);

	/**
	 * M�todo que retorna el n�mero de pares de la tabla.
	 * @return N�mero de pares de la tabla.
	 */
	public int darNumPares();


	/**
	 * M�todo que retorna el tama�o de la tabla.
	 * @return Tama�o de la tabla.
	 */
	public int darTamano();

	/**
	 * M�todo que da el n�mero de rehash realizados
	 * @return n�mero de rehash realizados
	 */
	public int darNumeroRehashes();

	/**
	 * M�todo que indica si una llave se encuentra en el arreglo de llaves.
	 * @param key Llave que es buscada
	 * @return True si se encuentra. False en caso contrario.
	 */
	public boolean contains(K key);

	public IListaEncadenada<V> darListaValores(K pKey);



}
