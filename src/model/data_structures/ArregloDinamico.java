package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico Genérico.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T>
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[])new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	/**
	 * Agrega un dato al arreglo.
	 * @post: Se agregó el elemento al arreglo.
	 * @param dato. Dato que llega por parámetro para ser agregado.
	 */
	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[])new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	/**
	 * Retorna el tamaño máximo del arreglo.
	 * @return Tamaño máximo del arreglo.
	 */
	public int darCapacidad() {
		return tamanoMax;
	}

	/**
	 * Retorna el tamaño actual del arreglo.
	 * @return Tamaño actual del arrelo.
	 */
	public int darTamano() {
		return tamanoAct;
	}

	/**
	 * Retorna el elemento de la posición i.
	 * @param Posición del elemento en el arreglo.
	 * @return Elemento de la posición i.
	 */
	public T darElemento(int i) {
		return (i<tamanoAct)?elementos[i]:null;
	}

	/**
	 * Busca el elemento T que entra por parámetro.
	 * @param Dato T.
	 * @return  Dato T buscado.
	 */
	public T buscar(T dato) {

		T elemento = null;

		boolean encontro = false;

		for (int i = 0; i < tamanoAct && !encontro; i++) 
		{
			if(elementos[i].compareTo(dato)==0)
			{
				encontro = true;
				elemento = elementos[i];
			}
		}

		return elemento;
	}

	/**
	 * Elimina el dato T que llega por parámetro.
	 * @param Dato T.
	 * @return Dato T eliminado.
	 */
	public T eliminar(T dato) {

		T elemento = buscar(dato);
		if(elemento!=null)
		{
			boolean eliminado = false;

			for (int i = 0; i < tamanoAct-1; i++) 
			{
				if(elemento.compareTo(elementos[i])==0)
					eliminado = true;
				if(eliminado)
					elementos[i]=elementos[i+1];
			}

			tamanoAct--;
		}

		return elemento;
	}

	/**
	 * Intercambia la posición entre dos elementos del arreglo.
	 * @param i Posición i.
	 * @param j Posición j.
	 */
	public void intercambiarPosiciones(int i, int j) 
	{
		T elemento=elementos[i];
		elementos[i]=elementos[j];
		elementos[j]=elemento;
	}
	
	/**
	 * Método que vuelve el último elemento del tamaño del arreglo más uno en null.
	 */
	public void ultimoNull()
	{
		elementos[tamanoAct+1]=null;
	}
	
	

}
