package model.data_structures;

import java.util.Comparator;

public class MaxHeapCP<T extends Comparable<T>> implements IMaxHeapCP<T> {

	//ACLARACIÓN PREVIA:
	//Los métodos utilizados para la implementación de esta clase fueron tomados
	//textualmente del libro guía del curso (Algorithms 4th). 
	//Se da entonces crédito total a los autores del libro y sus algoritmos.
	
	/**
	 * Arreglo dinámico que maneja la cola de prioridad.
	 */
	public T[] heap;

	/**
	 * Número de elementos presentes en la cola de prioridad.
	 */
	public int numPresentes; 
	
	private Comparator<T> comparator;

	/**
	 * Método constructor de la clase MaxHeapCP.
	 * @post:-Inicializa el arreglo dinámico que manejará el heap.
	 * 		 -Inicializa en cero el número de elementos presentes en el heap.
	 */
	public MaxHeapCP(int n)
	{
		heap=(T[]) new Comparable[n+1];
		numPresentes=0;
		comparator = null;

	}
	
	public MaxHeapCP(int n, Comparator<T> comp)
	{
		heap=(T[]) new Comparable[n+1];
		numPresentes=0;
		comparator = comp;
	}

	/**
	 * Método que retorna el número de elementos presentes en el heap de prioridad.
	 * @return Número de elementos presentes.
	 */
	public int darNumElementos()
	{
		return numPresentes;
	}
	

	/**
	 * Método que agrega un elemento en el heap de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que será agregado a la cola de prioridad.
	 */
	public void agregar(T elemento)
	{
        if (numPresentes == heap.length - 1) resize(2 * heap.length);

		heap[++numPresentes]=elemento;
		swim(numPresentes);
	}
	
    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];
        for (int i = 1; i <= numPresentes; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

	
	/**
	 * Saca/atiende el elemento máximo en el heap y lo retorna.
	 * @return Elemento máximo de la cola. Si la cola está vacía retorna null.
	 */
	public T sacarMax()
	{
		T mayor = heap[1]; 
		intercambiarPosiciones(1, numPresentes--);
		heap[numPresentes+1]=null;
		sink(1); 
		return mayor;
	}

	/**
	 * Obtiene el elemento máximo (sin sacarlo del heap).
	 * @return  Elemento máximo de la cola. Si la cola esta vacía retorna null.
	 */
	public T darMax()
	{
		return heap[1];
	}

	/**
	 * Retorna si el heap está vacío o no.
	 * @return True en caso de que este vacía. False en caso contrario.
	 */
	public boolean esVacia()
	{
		return numPresentes==0;
	}
	
	/**
	 * Método que envía un elemento de la parte superior a la inferior del heap para preservar el orden.
	 */
	public void sink(int pPosicion) 
	{
		while (2*pPosicion <= numPresentes)
		{
			int j = 2*pPosicion;
			if (j < numPresentes && less(j, j+1))
			{
				j++;
			}
			if (!less(pPosicion, j))
			{
				break;
			}
			intercambiarPosiciones(pPosicion, j);
			
			pPosicion = j;
		} 
	}
	
	/**
	 * Método que envía un elemento de la parte inferior a la superior del heap para preservar el orden.
	 */
	public void swim(int pPosicion) 
	{
		while (pPosicion > 1 && less(pPosicion/2, pPosicion))
		{
			intercambiarPosiciones(pPosicion/2, pPosicion);
			pPosicion = pPosicion/2;
		}
	}

	/**
	 * Método que retorna el arreglo dinámico que guarda el heap.
	 * @return Arreglo dinámico del Heap.
	 */
	public T[] darArreglo() 
	{
		return heap;
	}
	
	/**
	 * Método que compara por una característica natural de la clase del elemento comparable.
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j)
	{ 
		boolean comparador=false;
		if(heap[j]!=null)
		{
			comparador= comparator==null?(heap[i].compareTo(heap[j]) < 0):(comparator.compare(heap[i], heap[j])<0); 
		}
		return comparador;
	}

	/**
	 * Intercambia la posición entre dos elementos del arreglo.
	 * @param i Posición i.
	 * @param j Posición j.
	 */
	public void intercambiarPosiciones(int i, int j) 
	{
		T elemento=heap[i];
		heap[i]=heap[j];
		heap[j]=elemento;
	}

	



}
