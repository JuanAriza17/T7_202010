package model.data_structures;

public interface IQueue<T extends Comparable<T>> 
{
	/**
	 * M�todo que agrega al final de la cola un elemento que llega por par�metro.
	 * @param Elemento que ser� agregado en la cola.
	 * @post: Se agrega el elemento que llega por par�metro.
	 */
	void enqueue(T elemento);

	/**
	 * M�todo que elimina al �ltimo elemento de la cola. Modifica tambi�n la longitud de la cola.
	 * @return Elemento T que es eliminado.
	 */
	T dequeue();

	/**
	 * Retorna si la lista est� vac�a o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	boolean isEmpty();

	/**
	 * Retorna el tama�o de la cola.
	 * @return 
	 */
	int size();

	/**
	 * M�todo que retorna el primer elemento de la cola. En caso de que este sea null, retorna null.
	 * @return Primer elemento de la cola.
	 */
	T peek();

	/**
	 * String que retorna un mensaje con los elementos de la cola.
	 * @return Mensaje con los elementos de la cola.
	 */
	String darLista();

	/**
	 * M�todo que retorna la lista de elementos.
	 * @return Lista de elementos.
	 */
	public IListaEncadenada darElementos();

	public void enqueueOrden(T elemento);


}
