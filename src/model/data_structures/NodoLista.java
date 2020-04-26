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
	 * Elemento T genérico que es guardado en el nodo actual.
	 */
	private T elemento;

	/**
	 * Constructor de la clase NodoLista.
	 * @post:Se inicializa el dato T por el que llega por parámetro.
	 * 		 Se inicializan los nodos siguiente y anterior en null.
	 * @param dato Elemento genérico que será guardado en el nodo.
	 */
	public NodoLista(T dato)
	{
		elemento = dato;
		siguiente = null;
		anterior = null;
	}

	/**
	 * Cambia el puntero del siguiente nodo por el que llega por parámetro.
	 * @param sig Nodo que será el nuevo puntero siguiente.
	 */
	public void cambiarSiguiente(NodoLista<T> sig)
	{
		siguiente = sig;
	}

	/**
	 * Cambia el puntero del anterior nodo por el que llega por parámetro.
	 * @param sig Nodo que será el nuevo puntero anterior.
	 */
	public void cambiarAnterior(NodoLista<T> ant)
	{
		anterior = ant;
	}

	/**
	 * Método que retorna el elemento que guarda el nodo actual.
	 * @return Elemento T
	 */
	public T darElemento()
	{
		return elemento;
	}

	/**
	 * Método que retorna el siguiente nodo.
	 * @return Siguiente nodo.
	 */
	public NodoLista<T> darSiguiente()
	{
		return siguiente;
	}

	/**
	 * Método que retorna el anterior nodo.
	 * @return Anterior nodo.
	 */
	public NodoLista<T> darAnterior()
	{
		return anterior;
	}
}
