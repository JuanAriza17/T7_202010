package model.data_structures;
import java.util.Iterator;

import model.data_structures.RedBlackBST.Node;

public interface IRedBlackBST<K extends Comparable<K>, V extends Comparable<V>>
{
	/**
	 * M�todo que retorna el n�mero de parejas [Llave, Valor] del �rbol.
	 * @return N�meros de parejas.
	 */
	int size();
	
	/**
	 * Informa si el �rbol es vacio
	 * @return True en caso de que sea vacio. False en caso contrario.
	 */
	boolean isEmpty();
	
	/**
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna el valor null.
	 * @param key Llave de la cual se obtendr� el valor.
	 * @return Valor de la llave ingresada por par�metro.
	 */
	V get(K key);
	
	/**
	 * M�todo que retorna  la altura del camino desde la ra�z para llegar a la llave key (si existe).
	 * @param Llave de cual ser� buscada el camino.
	 * @return Altura del camino. En caso de que no exista la llave retorna -1.
	 */
	int getHeight(K key);

	/**
	 * M�todo que indica si la llave ingresada por par�metro se encuentra en el �rbol.
	 * @param key  Llave que ser� buscada.
	 * @return
	 */
	boolean contains(K key);
	
	/**
	 * M�todo que inserta una pareja [key, valor] en el �rbol respetando el balanceo RedBlack. 
	 * Si la llave ya existe se reemplaza el valor. 
	 * Si la llave key o el valor es null se debe lanzar una Exception.
	 * @param key
	 * @param val
	 */
	void put(K key, V val);
	
	/**
	 * M�todo que retorna la altura del �rbol (longitud de la rama m�s alta).
	 * @return Altura del �rbol.
	 */
	int height();
	
	/**
	 * M�todo que retorna la llave m�s peque�a del �rbol.
	 * @return Llave m�s peque�a del �rbol. Retorna null si el �rbol est� vac�o.
	 */
	K min();
	
	/**
	 * M�todo que retorna la llave m�s grande del �rbol.
	 * @return Llave m�s grande del �rbol. Retorna null si el �rbol est� vac�o.
	 */
	K max();
	
	/**
	 * Valida si el �rbol es Binario Ordenado y est� balanceado Rojo-Negro a la izquierda. Hay que validar que: 
	 * (a) la llave de cada nodo sea mayor que cualquiera de su sub-�rbol izquierdo, 
	 * (b) la llave de cada nodo sea menor que cualquiera de su sub-�rbol derecho, 
	 * (c) un nodo NO puede tener enlace rojo a su hijo derecho, 
	 * (d) No puede haber dos enlaces rojos consecutivos a la izquierda. Es decir, un nodo NO puede tener un enlace rojo a su hijo izquierdo y su hijo izquierdo NO puede tener enlace rojo a su hijo izquierdo, 
	 * (e) todas las ramas tienen el mismo n�mero de enlaces negros.
	 */
	boolean check();
	
	/**
	 * M�todo que retorna todas las llaves del �rbol como un iterador.
	 * @return Llaves del �rbol.
	 */
	Iterator<K>keys();
	
	/**
	 * M�todo que retorna los valores V en el �rbol que est�n asociados al rango de llaves dado. 
	 * Debe intentarse NO recorrer el �rbol.
	 * @param init Llave inicial donde empieza el rango.
	 * @param end Llave final del rango.
	 * @return Retorna valores V en el rango establecido.
	 */
	Iterator<V>valuesInRange(K init, K end);
	
	/**
	 * M�todo que retorna las llaves K en el �rbol que est�n asociados al rango de llaves dado. 
	 * Debe intentarse NO recorrer el �rbol.
	 * @param init Llave inicial donde empieza el rango.
	 * @param end Llave final del rango.
	 * @return Retorna las llaves K en el rango establecido.
	 */
	Iterator<K>keysInRange(K init, K end);
	
	/**
	 * M�todo que retorna los nodes de las hojas del �rbol.
	 * @return Iterator que contine los las hojas del �rbol
	 */
	Iterator<RedBlackBST<K, V>.Node> darHojas();
	
	/**
	 * M�todo que retorna el n�mero de elementos en el rango de llaves.
	 * @return numValuesInRange;
	 */
	int darNumValuesInRange();
}
