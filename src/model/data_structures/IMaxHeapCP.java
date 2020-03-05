package model.data_structures;

public interface IMaxHeapCP<T extends Comparable<T>>
{
	/**
	 * Método que retorna el número de elementos presentes en el heap de prioridad.
	 * @return Número de elementos presentes.
	 */
	public int darNumElementos();

	/**
	 * Método que agrega un elemento en el heap de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que será agregado a la cola de prioridad.
	 */
	public void agregar(T elemento);
	
	/**
	 * Saca/atiende el elemento máximo en el heap y lo retorna.
	 * @return Elemento máximo de la cola. Si la cola está vacía retorna null.
	 */
	public T sacarMax();
	
	/**
	 * Obtiene el elemento máximo (sin sacarlo del heap).
	 * @return  Elemento máximo de la cola. Si la cola esta vacía retorna null.
	 */
	public T darMax();
	
	/**
	 * Retorna si el heap está vacío o no.
	 * @return True en caso de que este vacía. False en caso contrario.
	 */
	public boolean esVacia();
	
	/**
	 * Método que envía un elemento de la parte superior a la inferior del heap para preservar el orden.
	 */
	public void sink(int pPosicion);
	
	/**
	 * Método que envía un elemento de la parte inferior a la superior del heap para preservar el orden.
	 */
	public void swim(int pPosicion);

}
