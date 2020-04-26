package model.data_structures;

import java.util.Comparator;

public interface IListaEncadenada<T extends Comparable<T>> extends Iterable<T> 
{
	/**
	 * Agrega un elemento al final de la lista
	 * Si lista esta vacia se vuelve el primero 
	 * @param dato Elemento a agregar
	 */
	void agregarFinal(T dato);

	/**
	 * Agrega un elemento al inicio de la lista
	 * Si lista esta vacia se vuelve el primero 
	 * @param dato Elemento a agregar
	 */
	void agregarInicio(T dato);

	/**
	 * Agregar un dato al final de la lista
	 * Si lista esta vacia se vuelve el primero 
	 * @param dato nuevo elemento
	 */
	public void agregar( T dato );

	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). null si no se encontro el dato.
	 */
	T buscar(T dato);

	/**
	 * Eliminar un dato del arreglo.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * @param dato Objeto de eliminacion en el arreglo
	 * @return dato eliminado
	 */
	T eliminar( T dato );

	/**
	 * Elimina el �ltimo elemento de la lista encadenada.
	 * @return El �ltimo elemento eliminado.
	 */
	T eliminarUltimo();

	/**
	 * Elimina el primer elemento de la lista encadenada.
	 * @return El primer elemento eliminado.
	 */
	T eliminarPrimero();

	/**
	 * Da la cantidad de elementos de la lista.
	 * @return tama�o lista
	 */
	int darLongitud();

	/**
	 * Da el primer elemento de la lista
	 * @return primer elemento lista
	 */
	NodoLista<T> darPrimero();

	/**
	 * Da el �ltimo elemento de la lista
	 * @return �ltimo elemento lista
	 */
	NodoLista<T> darUltimo();

	/**
	 * Da el elemento en la posicion dada por par�metro
	 * @param posicion Posici�n del elemento, posicion>=0
	 * @return
	 */
	T darElemento(int posicion);

	/**
	 * Da el elemento actual
	 * @return elemento actual
	 */
	T elementoActual();

	/**
	 * Inicializa actual como el primero
	 */
	void iniciarRecorrido();

	/**
	 * Cambia actual por el siguiente
	 */
	void avanzarActual();

	/**
	 * Cambia el actual por el anterior
	 */
	void retrocederActual();

	/**
	 * M�todo que retorna un arreglo de la lista.
	 * @return Retorna arreglo de la lista.
	 */
	Comparable[] darArreglo();

	/**
	 * M�todo que agrega en orden
	 * @param Nuevo dato
	 */
	void agregarOrden(T elemento);

}
