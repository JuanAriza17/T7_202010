package model.data_structures;

public interface IMaxHeapCP<T extends Comparable<T>>
{
	/**
	 * Método que retorna el número de elementos presentes en la cola de prioridad.
	 * @return Número de elementos presentes.
	 */
	public int darNumElementos();

	/**
	 * Método que agrega un elemento a la cola de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que será agregado a la cola de prioridad.
	 */
	public void agregar(T elemento);
	
	/**
	 * Saca/atiende el elemento máximo en la cola y lo retorna.
	 * @return Elemento máximo de la cola. Si la cola está vacía retorna null.
	 */
	public T sacarMax();
	
	/**
	 * Obtiene el elemento máximo (sin sacarlo de la cola).
	 * @return  Elemento máximo de la cola. Si la cola esta vacía retorna null.
	 */
	public T darMax();
	
	/**
	 * Retorna si la cola está vacía o no.
	 * @return True en caso de que este vacía. False en caso contrario.
	 */
	public boolean esVacia();

}
