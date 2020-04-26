package model.data_structures;

public class Stack<T extends Comparable<T>> implements IStack<T>
{
	/**
	 * Interfaz de la lista implementada en la pila.
	 */		
	private IListaEncadenada<T> elementos;	

	/**
	 * M�todo constructor de la clase Stack.
	 * @post:Declara una ListaEncadenada gen�rica vac�a.
	 */
	public Stack()
	{
		elementos = new ListaEncadenada<T>();
	}

	/**
	 * M�todo que agrega al final de la pila un elemento que llega por par�metro.
	 * @post: Se agrega el elemento que llega por par�metro.
	 */
	public void push(T elemento) 
	{
		elementos.agregarFinal(elemento);
	}

	/**
	 * M�todo que elimina al �ltimo elemento de la pila. Modifica tambi�n la longitud de la pila.
	 * @return Elemento T que es eliminado.
	 */
	public T pop() 
	{
		T elemento = elementos.eliminarUltimo();
		return elemento;
	}

	/**
	 * Retorna si la lista est� vac�a o no. 
	 * @return True en caso verdadero, false en caso contrario.
	 */
	public boolean isEmpty() 
	{
		return elementos.darPrimero()==null;
	}

	/**
	 * Retorna el tama�o de la cola.
	 * @return Tama�o de la cola.
	 */
	public int size() 
	{
		return elementos.darLongitud();
	}

	/**
	 * M�todo que retorna el primer ultimo de la pila. En caso de que este sea null, retorna null.
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
