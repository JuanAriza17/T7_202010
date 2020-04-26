package model.data_structures;

import java.util.Iterator;

public interface IHashTable<K extends Comparable<K>, V extends Comparable<V>>
{
	/**
	 * Método que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que será agregada o buscada. 
	 * @param pValue Valor que será agregado o reemplazado. pValue no puede ser null.
	 */
	public void putInSet(K pKey, V pValue);

	/**
	 * Método que retorna el valor de la llave determinada.
	 * @param pKey Llave que será buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> getSet(K pKey);

	/**
	 * Elimina la dupla de la llave que ingresa por parámetro,
	 * @param pKey Llave que será buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	public Iterator<V> deleteSet(K pKey);

	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	Iterator<K> keys();

	/**
	 * Convierte el hashCode() en un índice.
	 * @param pKey. Llave que ingresa por parámetro.
	 * @return Índice que representa el hashCode.
	 */
	public int hash(K pKey);

	/**
	 * Método que retorna el número de pares de la tabla.
	 * @return Número de pares de la tabla.
	 */
	public int darNumPares();


	/**
	 * Método que retorna el tamaño de la tabla.
	 * @return Tamaño de la tabla.
	 */
	public int darTamano();

	/**
	 * Método que da el número de rehash realizados
	 * @return número de rehash realizados
	 */
	public int darNumeroRehashes();

	/**
	 * Método que indica si una llave se encuentra en el arreglo de llaves.
	 * @param key Llave que es buscada
	 * @return True si se encuentra. False en caso contrario.
	 */
	public boolean contains(K key);

	public IListaEncadenada<V> darListaValores(K pKey);



}
