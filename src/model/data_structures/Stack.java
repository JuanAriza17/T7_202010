package model.data_structures;

public class Stack<T extends Comparable<T>> implements IStack<T>
{
	/**
     * Interfaz de la lista implementada en la pila.
     */		
	private IListaEncadenada<T> elementos;	
	
	/**
	 * Método constructor de la clase Stack.
	 * @post:Declara una ListaEncadenada genérica vacía.
	 */
	public Stack()
	{
		elementos = new ListaEncadenada<T>();
	}
	
	/**
	 * Método que agrega al final de la pila un elemento que llega por parámetro.
	 * @post: Se agrega el elemento que llega por parámetro.
	 */
	public void push(T elemento) 
	{
		elementos.agregarFinal(elemento);
	}

	/**
	 * Método que elimina al último elemento de la pila. Modifica también la longitud de la pila.
	 * @return Elemento T que es eliminado.
	 */
	public T pop() 
	{
		T elemento = elementos.eliminarUltimo();
		return elemento;
	}

	/**
	 * Retorna si la lista está vacía o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	public boolean isEmpty() 
	{
		return elementos.darPrimero()==null;
	}

	/**
	 * Retorna el tamaño de la cola.
	 * @return Tamaño de la cola.
	 */
	public int size() 
	{
		return elementos.darLongitud();
	}

	/**
	 * Método que retorna el primer ultimo de la pila. En caso de que este sea null, retorna null.
	 * @return Ultimo elemento de la pila.
	 */
	public T peek() 
	{
		NodoLista<T> elemento = elementos.darUltimo();

		return elemento!=null?elemento.darElemento():null;	
	}
	
	/**
	 * String que retorna un mensaje con los elementos de la pila.
	 * @return Mensaje con los elementos de la pila.
	 */
	public String darLista()
	{
		String mensaje="";
		
		for (int i = 0; i < elementos.darLongitud(); i++)
		{
			mensaje+=elementos.darElemento(i).toString()+"\n";
		}
		
		return mensaje;
	}

}
