package model.data_structures;


public class MaxHeapCP<T extends Comparable<T>> implements IMaxHeapCP {

	/**
	 * Arreglo dinámico que maneja la cola de prioridad.
	 */
	public IArregloDinamico heap;

	/**
	 * Número de elementos presentes en la cola de prioridad.
	 */
	public int numPresentes; 

	/**
	 * Método constructor de la clase MaxHeapCP.
	 * @post:-Inicializa el arreglo dinámico que manejará el heap.
	 * 		 -Inicializa en cero el número de elementos presentes en el heap.
	 */
	public MaxHeapCP()
	{
		heap=new ArregloDinamico(550000);
		numPresentes=heap.darTamano();

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
	public void agregar(Comparable elemento)
	{
		heap.agregar(elemento);
		int posicionUltimo=heap.darTamano()-1;
		numPresentes++;
		swim(posicionUltimo);
		
	}


	/**
	 * Saca/atiende el elemento máximo en el heap y lo retorna.
	 * @return Elemento máximo de la cola. Si la cola está vacía retorna null.
	 */
	public T sacarMax()
	{
		T mayor = (T) heap.darElemento(1); 
		heap.intercambiarPosiciones(0, heap.darTamano()-1);; 
		heap.ultimoNull(); 
		sink(0); 
		--numPresentes;
		return mayor;
	}

	/**
	 * Obtiene el elemento máximo (sin sacarlo del heap).
	 * @return  Elemento máximo de la cola. Si la cola esta vacía retorna null.
	 */
	public T darMax()
	{
		return (T) heap.darElemento(0);
	}

	/**
	 * Retorna si el heap está vacío o no.
	 * @return True en caso de que este vacía. False en caso contrario.
	 */
	public boolean esVacia()
	{
		return numPresentes==0?true:false;
	}

	/**
	 * Método que envía un elemento de la parte superior a la inferior del heap para preservar el orden.
	 */
	public void sink(int pPosicion) 
	{
		pPosicion++;
		while (2*pPosicion <= heap.darTamano()-1)
		{
			int j = 2*pPosicion;
			if (j < heap.darTamano()-1 && less(j, j+1)) j++;
			if (!less(pPosicion, j)) break;
			heap.intercambiarPosiciones(pPosicion, j);
			pPosicion = j;
		} 
	}

	/**
	 * Método que envía un elemento de la parte inferior a la superior del heap para preservar el orden.
	 */
	public void swim(int pPosicion) 
	{
		while (pPosicion > 0 && less(pPosicion/2, pPosicion))
		{
			heap.intercambiarPosiciones(pPosicion/2, pPosicion);
			pPosicion = pPosicion/2;
		}
	}

	/**
	 * Método que retorna el arreglo dinámico que guarda el heap.
	 * @return Arreglo dinámico del Heap.
	 */
	public IArregloDinamico darArreglo() 
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
		if(heap.darElemento(j)!=null)
		{
			comparador= heap.darElemento(i).compareTo(heap.darElemento(j)) < 0; 
		}
		return comparador;
	}
	
	




}
