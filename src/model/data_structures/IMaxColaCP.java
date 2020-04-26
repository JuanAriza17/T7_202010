package model.data_structures;

public interface IMaxColaCP<T extends Comparable<T>> 
{
	/**
	 * M�todo que retorna el n�mero de elementos presentes en la cola de prioridad.
	 * @return N�mero de elementos presentes.
	 */
	public int darNumElementos();

	/**
	 * M�todo que agrega un elemento a la cola de prioridad. Utiliza el comparador natural de la clase T.
	 * @param elemento Elemento que ser� agregado a la cola de prioridad.
	 */
	public void agregar(T elemento);

	/**
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna.
	 * @return Elemento m�ximo de la cola. Si la cola est� vac�a retorna null.
	 */
	public T sacarMax();

	/**
	 * Obtiene el elemento m�ximo (sin sacarlo de la cola).
	 * @return  Elemento m�ximo de la cola. Si la cola esta vac�a retorna null.
	 */
	public T darMax();

	/**
	 * Retorna si la cola est� vac�a o no.
	 * @return True en caso de que este vac�a. False en caso contrario.
	 */
	public boolean esVacia();

	/**
	 * Retorna el arreglo con la lista de la cola.
	 * @return Arreglo con la lista de la cola.
	 */
	public Comparable[] darListaCola();

	/**
	 * Retorna la cola.
	 * @return cola.
	 */
	public IQueue darCola();

}
