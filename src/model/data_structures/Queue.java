package model.data_structures;

public class Queue<T extends Comparable<T>> implements IQueue<T> 
{
	/**
	 * Interfaz de la lista implementada en la cola.
	 */
	private IListaEncadenada<T> elementos;	

	/**
	 * Método constructor de la clase Queue
	 * @post:Declara una ListaEncadenada genérica vacía.
	 */
	public Queue()
	{
		elementos = new ListaEncadenada<T>();
	}

	/**
	 * Método que agrega al final de la cola un elemento que llega por parámetro.
	 * @param Elemento que será agregado en la cola.
	 * @post: Se agrega el elemento que llega por parámetro.
	 */
	public void enqueue(T elemento) 
	{
		elementos.agregarFinal(elemento);
	}

	/**
	 * Método que elimina al último elemento de la cola. Modifica también la longitud de la cola.
	 * @return Elemento T que es eliminado.
	 */
	public T dequeue() 
	{
		T elemento = elementos.eliminarPrimero();

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
	 * @return 
	 */
	public int size() 
	{
		return elementos.darLongitud();
	}

	/**
	 * Método que retorna el primer elemento de la cola. En caso de que este sea null, retorna null.
	 * @return Primer elemento de la cola.
	 */
	public T peek() 
	{
		NodoLista<T> elemento = elementos.darPrimero();

		return elemento!=null?elemento.darElemento():null;	
	}

	/**
	 * String que retorna un mensaje con los elementos de la cola.
	 * @return Mensaje con los elementos de la cola.
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

	/**
	 * Método que retorna la lista de elementos.
	 * @return Lista de elementos.
	 */
	public IListaEncadenada<T> darElementos()
	{
		return  elementos;
	}

	public void enqueueOrden(T elemento)
	{
		elementos.agregarOrden(elemento);
	}

}
