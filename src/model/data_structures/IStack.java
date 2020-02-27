package model.data_structures;

public interface IStack<T extends Comparable<T>> 
{
	/**
	 * Método que agrega al final de la pila un elemento que llega por parámetro.
	 * @post: Se agrega el elemento que llega por parámetro.
	 */
	void push(T elemento);
	
	/**
	 * Método que elimina al último elemento de la pila. Modifica también la longitud de la pila.
	 * @return Elemento T que es eliminado.
	 */
	T pop();
	
	/**
	 * Retorna si la lista está vacía o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	boolean isEmpty();
	
	/**
	 * Retorna el tamaño de la pila.
	 * @return Tamaño de la pila.
	 */
	int size();
	
	/**
	 * Método que retorna el primer ultimo de la pila. En caso de que este sea null, retorna null.
	 * @return Ultimo elemento de la pila.
	 */
	T peek();
	
	/**
	 * String que retorna un mensaje con los elementos de la pila.
	 * @return Mensaje con los elementos de la pila.
	 */
	String darLista();
}
