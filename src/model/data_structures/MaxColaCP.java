package model.data_structures;

import model.logic.Ordenamientos;

public class MaxColaCP<T extends Comparable<T>> implements IMaxColaCP<T> {

	/**
	 * Arreglo din�mico que maneja la cola de prioridad.
	 */
	public IQueue<T> cola;
	
	/**
	 * N�mero de elementos presentes en la cola de prioridad.
	 */
	public int numPresentes; 
	
	/**
	 * M�todo constructor de la clase MaxHeapCP.
	 * @post:-Inicializa el arreglo din�mico que manejar� la cola de prioridad.
	 * 		 -Inicializa en cero el n�mero de elementos presentes en la cola de prioridad.
	 */
	public MaxColaCP()
	{
		numPresentes=0;
		cola=new Queue<T>();
	}
	/**
	 * M�todo que retorna el n�mero de elementos presentes en la cola de prioridad.
	 * @return N�mero de elementos presentes.
	 */
	public int darNumElementos()
	{
		return numPresentes;
	}

	/**
	 * M�todo que agrega un elemento a la cola de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que ser� agregado a la cola de prioridad.
	 */
	public void agregar(T elemento)
	{
		cola.enqueueOrden(elemento);
		numPresentes++;
	}

	/**
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna.
	 * @return Elemento m�ximo de la cola. Si la cola est� vac�a retorna null.
	 */
	public T sacarMax()
	{
		numPresentes--;
		return cola.dequeue();
	}
	
	/**
	 * Obtiene el elemento m�ximo (sin sacarlo de la cola).
	 * @return  Elemento m�ximo de la cola. Si la cola esta vac�a retorna null.
	 */
	public T darMax()
	{
		return  cola.peek();
	}
	
	/**
	 * Retorna si la cola est� vac�a o no.
	 * @return True en caso de que este vac�a. False en caso contrario.
	 */
	public boolean esVacia()
	{
		return numPresentes==0;
	}
	
	/**
	 * Retorna el arreglo con la lista de la cola.
	 * @return Arreglo con la lista de la cola.
	 */
	public Comparable[] darListaCola()
	{
		return cola.darElementos().darArreglo();
	}
	
	/**
	 * Retorna la cola.
	 * @return cola.
	 */
	public IQueue<T> darCola()
	{
		return cola;
	}
}
