package model.data_structures;

public interface IQueue<T extends Comparable<T>> 
{
	/**
	 * Método que agrega al final de la cola un elemento que llega por parámetro.
	 * @param Elemento que será agregado en la cola.
	 * @post: Se agrega el elemento que llega por parámetro.
	 */
	void enqueue(T elemento);

	/**
	 * Método que elimina al último elemento de la cola. Modifica también la longitud de la cola.
	 * @return Elemento T que es eliminado.
	 */
	T dequeue();

	/**
	 * Retorna si la lista está vacía o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	boolean isEmpty();

	/**
	 * Retorna el tamaño de la cola.
	 * @return 
	 */
	int size();

	/**
	 * Método que retorna el primer elemento de la cola. En caso de que este sea null, retorna null.
	 * @return Primer elemento de la cola.
	 */
	T peek();

	/**
	 * String que retorna un mensaje con los elementos de la cola.
	 * @return Mensaje con los elementos de la cola.
	 */
	String darLista();
	
	/**
	 * Método que retorna la lista de elementos.
	 * @return Lista de elementos.
	 */
	public IListaEncadenada darElementos();


}
