package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorLista<T extends Comparable<T>> implements Iterator<T> 
{
	/**
	 * Nodo del próximo elemento.
	 */
	private NodoLista<T> proximo;

	/**
	 * Nodo del próximo anterior elemento.
	 */
	private NodoLista<T> ant_prox;

	/**
	 * Nodo del anterior elemento.
	 */
	private NodoLista<T> ant_ant;

	/**
	 * Constructor de la clase IteratorLista.
	 * Se inicializan los nodos: ant_prox y ant_ant en null
	 * El próximo nodo se inicializa con el nodo que llega por parámetro.
	 * @param primero Nodo que llega por parámetro.
	 */
	public IteratorLista(NodoLista<T> primero)
	{
		proximo = primero;
		ant_prox = null;
		ant_ant = null;
	}

	/**
	 * Retorna la condición si existe un nodo siguiente.
	 * @return True en caso de que tenga siguiente y False en caso contrario.
	 */
	public boolean hasNext() 
	{
		return proximo!=null;
	}

	/**
	 * Retorna el siguiente elemento en la estructura de datos.
	 * @return Próximo elemento de la estructura de datos.
	 */
	public T next() 
	{
		if(proximo==null)
			throw new NoSuchElementException("No hay próximo");

		T elemento = proximo.darElemento();

		proximo = proximo.darSiguiente();

		return elemento;
	}

	/**
	 * Remueve un nodo de la estructura de datos.
	 * @post: Se elimina el elemento de la estructura.
	 */
	public void remove() throws UnsupportedOperationException, IllegalStateException
	{
		throw new UnsupportedOperationException("No implementada");
	}

}
