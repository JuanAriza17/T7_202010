package model.data_structures;

import java.util.Iterator;

public interface IHashTable<T extends Comparable<T>>
{
	/**
	 * Método que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que será agregada o buscada. 
	 * @param pValue Valor que será agregado o reemplazado. pValue no puede ser null.
	 */
	//TODO OJO AQUÍ FALTA ARREGLAR EL PVALUE
	public void put(T pKey, T pValue);
	
	/**
	 * Método que retorna el valor de la llave determinada.
	 * @param pKey Llave que será buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	//TODO OJO AQUÍ FALTA ARREGLAR EL TIPO T
	public T get(T pKey);
	
	/**
	 * Elimina la dupla de la llave que ingresa por parámetro,
	 * @param pKey Llave que será buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	//TODO OJO AQUÍ FALTA ARREGLAR EL TIPO T
	public T delete(T pKey);
	
	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	Iterator<T> keys();
	
	
	
	
}
