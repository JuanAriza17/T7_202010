package model.data_structures;

public class MaxColaCP<T extends Comparable<T>> implements IMaxColaCP {

	/**
	 * Arreglo dinámico que maneja la cola de prioridad.
	 */
	public IArregloDinamico cola;
	
	/**
	 * Número de elementos presentes en la cola de prioridad.
	 */
	public int numPresentes; 
	
	/**
	 * Método constructor de la clase MaxHeapCP.
	 * @post:-Inicializa el arreglo dinámico que manejará la cola de prioridad.
	 * 		 -Inicializa en cero el número de elementos presentes en la cola de prioridad.
	 */
	public MaxColaCP()
	{
		numPresentes=0;
		cola=new ArregloDinamico(100);
	}
	/**
	 * Método que retorna el número de elementos presentes en la cola de prioridad.
	 * @return Número de elementos presentes.
	 */
	public int darNumElementos()
	{
		return numPresentes;
	}

	/**
	 * Método que agrega un elemento a la cola de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que será agregado a la cola de prioridad.
	 */
	public void agregar(Comparable elemento)
	{
		
	}

	
	/**
	 * Saca/atiende el elemento máximo en la cola y lo retorna.
	 * @return Elemento máximo de la cola. Si la cola está vacía retorna null.
	 */
	public T sacarMax()
	{
		return null;
	}
	
	/**
	 * Obtiene el elemento máximo (sin sacarlo de la cola).
	 * @return  Elemento máximo de la cola. Si la cola esta vacía retorna null.
	 */
	public T darMax()
	{
		return null;
	}
	
	/**
	 * Retorna si la cola está vacía o no.
	 * @return True en caso de que este vacía. False en caso contrario.
	 */
	public boolean esVacia()
	{
		return false;
	}
}
