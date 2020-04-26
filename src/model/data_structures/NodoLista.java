package model.data_structures;

public class NodoLista<T>
{
	/**
	 * Nodo del siguiente elemento de la lista.
	 */
	private NodoLista<T> siguiente;

	/**
	 * Nodo del anterior elemento de la lista.
	 */
	private NodoLista<T> anterior;

	/**
	 * Elemento T gen�rico que es guardado en el nodo actual.
	 */
	private T elemento;

	/**
	 * Constructor de la clase NodoLista.
	 * @post:Se inicializa el dato T por el que llega por par�metro.
	 * 		 Se inicializan los nodos siguiente y anterior en null.
	 * @param dato Elemento gen�rico que ser� guardado en el nodo.
	 */
	public NodoLista(T dato)
	{
		elemento = dato;
		siguiente = null;
		anterior = null;
	}

	/**
	 * Cambia el puntero del siguiente nodo por el que llega por par�metro.
	 * @param sig Nodo que ser� el nuevo puntero siguiente.
	 */
	public void cambiarSiguiente(NodoLista<T> sig)
	{
		siguiente = sig;
	}

	/**
	 * Cambia el puntero del anterior nodo por el que llega por par�metro.
	 * @param sig Nodo que ser� el nuevo puntero anterior.
	 */
	public void cambiarAnterior(NodoLista<T> ant)
	{
		anterior = ant;
	}

	/**
	 * M�todo que retorna el elemento que guarda el nodo actual.
	 * @return Elemento T
	 */
	public T darElemento()
	{
		return elemento;
	}

	/**
	 * M�todo que retorna el siguiente nodo.
	 * @return Siguiente nodo.
	 */
	public NodoLista<T> darSiguiente()
	{
		return siguiente;
	}

	/**
	 * M�todo que retorna el anterior nodo.
	 * @return Anterior nodo.
	 */
	public NodoLista<T> darAnterior()
	{
		return anterior;
	}
}
