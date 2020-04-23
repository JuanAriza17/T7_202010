package model.data_structures;

import java.util.Comparator;

public interface IMaxHeapCP<T extends Comparable<T>>
{
	/**
	 * M�todo que retorna el n�mero de elementos presentes en el heap de prioridad.
	 * @return N�mero de elementos presentes.
	 */
	public int darNumElementos();

	/**
	 * M�todo que agrega un elemento en el heap de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que ser� agregado a la cola de prioridad.
	 */
	public void agregar(T elemento);
		
	/**
	 * Saca/atiende el elemento m�ximo en el heap y lo retorna.
	 * @return Elemento m�ximo de la cola. Si la cola est� vac�a retorna null.
	 */
	public T sacarMax();
	
	/**
	 * Obtiene el elemento m�ximo (sin sacarlo del heap).
	 * @return  Elemento m�ximo de la cola. Si la cola esta vac�a retorna null.
	 */
	public T darMax();
	
	/**
	 * Retorna si el heap est� vac�o o no.
	 * @return True en caso de que este vac�a. False en caso contrario.
	 */
	public boolean esVacia();
	
	/**
	 * M�todo que env�a un elemento de la parte superior a la inferior del heap para preservar el orden.
	 */
	public void sink(int pPosicion);
	
	/**
	 * M�todo que env�a un elemento de la parte inferior a la superior del heap para preservar el orden.
	 */
	public void swim(int pPosicion);
	
	/**
	 * M�todo que retorna el arreglo din�mico que guarda el heap.
	 * @return Arreglo din�mico del Heap.
	 */
	public T[] darArreglo();

	/**
	 * M�todo que agrega un elemento en el heap de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que ser� agregado a la cola de prioridad.
	 * @param Comparator
	 */
	public void agregar(T elemento, Comparator<T> comp);


	/**
	 * Saca/atiende el elemento m�ximo en el heap y lo retorna.
	 * @param Comparator
	 * @return Elemento m�ximo de la cola. Si la cola est� vac�a retorna null.
	 */
	public T sacarMax(Comparator<T> comp);

	/**
	 * M�todo que env�a un elemento de la parte superior a la inferior del heap para preservar el orden.
	 * @param Comparator
	 */
	public void sink(int pPosicion, Comparator<T> comp);
	
	/**
	 * M�todo que env�a un elemento de la parte inferior a la superior del heap para preservar el orden.
	 * @param comp Comparator 
	 */
	public void swim(int pPosicion, Comparator<T> comp);
	
}
