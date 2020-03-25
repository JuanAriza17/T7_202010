package model.data_structures;

import java.util.Iterator;

public class HashLinearProbing<T extends Comparable<T>> implements IHashTable<T>{

	/**
	 * Método que agrega una dupla (pKey, pValue) a la tabla. Si la llave pKey existe, se reemplaza su valor pValue asociado. 
	 * @param pKey Llave que será agregada o buscada. 
	 * @param pValue Valor que será agregado o reemplazado. pValue no puede ser null.
	 */
	@Override
	public void put(T pKey, T pValue) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método que retorna el valor de la llave determinada.
	 * @param pKey Llave que será buscada.
	 * @return El valor asociado a esa llave. Retorna null en caso de que no se encuentre la llave.
	 */
	@Override
	public T get(T pKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Elimina la dupla de la llave que ingresa por parámetro,
	 * @param pKey Llave que será buscada.
	 * @return Valor asociado a la llave. Retorna null en caso de que no se encuentre la llave.
	 */
	@Override
	public T delete(T pKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Conjunto de llaves T presentes en la tabla.
	 * @return Conjunto de llaves T presentes en la tabla.
	 */
	@Override
	public Iterator<T> keys() {
		// TODO Auto-generated method stub
		return null;
	}

}
