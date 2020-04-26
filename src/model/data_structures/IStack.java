package model.data_structures;

public interface IStack<T extends Comparable<T>> 
{
	/**
	 * M�todo que agrega al final de la pila un elemento que llega por par�metro.
	 * @post: Se agrega el elemento que llega por par�metro.
	 */
	void push(T elemento);

	/**
	 * M�todo que elimina al �ltimo elemento de la pila. Modifica tambi�n la longitud de la pila.
	 * @return Elemento T que es eliminado.
	 */
	T pop();

	/**
	 * Retorna si la lista est� vac�a o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	boolean isEmpty();

	/**
	 * Retorna el tama�o de la pila.
	 * @return Tama�o de la pila.
	 */
	int size();

	/**
	 * M�todo que retorna el primer ultimo de la pila. En caso de que este sea null, retorna null.
	 * @return Ultimo elemento de la pila.
	 */
	T peek();

	/**
	 * String que retorna un mensaje con los elementos de la pila.
	 * @return Mensaje con los elementos de la pila.
	 */
	String darLista();
}
