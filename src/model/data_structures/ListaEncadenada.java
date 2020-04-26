package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class ListaEncadenada<T extends Comparable<T>> implements IListaEncadenada<T>
{
	/**
	 * Atributo que guarda el tamaño de la lista.
	 */
	private int longitud;

	/**
	 * Primer nodo de la lista encadenada.
	 */
	private NodoLista<T> primero;

	/**
	 * Nodo actual de la lista ecadenada.
	 */
	private NodoLista<T> actual;

	/**
	 * Último nodo de la lista encadenada.
	 */
	private NodoLista<T> ultimo;

	/**
	 * Constructor de la clase ListaEncadenada
	 * @post:Se inicializa la longitud de la lista en 0.
	 * 		 Los nodos primero y actual empiezan en null.
	 */
	public ListaEncadenada()
	{
		longitud = 0;
		primero = null;
		actual = null;
	}

	/**
	 * Agrega un dato genérico a la lista. Este elemento es agregado al final de la lista.
	 * @param dato T genérico que va a ser agregado.
	 */
	public void agregarFinal(T dato)
	{
		NodoLista<T> nuevo = new NodoLista<T>(dato);

		if(primero!=null)
		{
			ultimo.cambiarSiguiente(nuevo);
			nuevo.cambiarAnterior(ultimo);
			ultimo = nuevo;
		}
		else
		{
			primero = nuevo;
			ultimo = primero;
		}
		longitud++;

	}

	/**
	 * Agrega un elemento a la lista, pero utiliza recorrido (de forma natural en una lista).
	 * @param dato T genérico que será agregado a la lista.
	 */
	public void agregar(T dato)
	{
		if(primero == null)
		{
			primero = new NodoLista<T>(dato);
			ultimo=primero;
			longitud++;
		}
		else
		{
			NodoLista<T> act = primero;

			while(act!=null&&act.darSiguiente()!=null)
			{
				act = act.darSiguiente();
			}

			NodoLista<T> nuevo = new NodoLista<T>(dato);
			if(act.darSiguiente()==null)
			{
				ultimo = nuevo;
			}
			act.cambiarSiguiente(nuevo);
			nuevo.cambiarAnterior(act);
			longitud++;
		}

	}

	/**
	 * Método que agrega a la lista un elemento T al inicio. Si no existe un elemento en la lista, este elemento será el primero.
	 * @param dato T genérico que será agregado al inicio de la lista.
	 */
	public void agregarInicio(T dato)
	{
		NodoLista<T> nuevo = new NodoLista<T>(dato);

		if(primero==null)
		{
			primero = nuevo;
			ultimo = primero;
			longitud++;
		}
		else
		{
			nuevo.cambiarSiguiente(primero);
			primero.cambiarAnterior(nuevo);
			primero = nuevo;
			longitud++;
		}

	}

	/**
	 * Busca un elemento genérico en la lista. La recorre y retorna el elemento encontrado.
	 * @param: dato T genérico que será buscado en la lista.
	 * @return Elemento T que es buscado.
	 */
	public T buscar(T dato) 
	{
		T elemento = null;
		NodoLista<T> act = primero;

		while(act!=null&&act.darElemento().compareTo(dato)!=0)
		{
			act = act.darSiguiente();
		}
		if(act!=null)
		{
			elemento = act.darElemento();
		}
		return elemento;
	}

	/**
	 * Método que retorna la longitud de la lista.
	 * @return  Longitud de la lista.
	 */
	public int darLongitud() 
	{
		return longitud;
	}

	/**
	 * Elimina un dato T que ingresa por parámetro independientemente de su posición en la lista.
	 * @post: Se eliminó el elemento T.
	 * @param dato T genérico que será eliminado.
	 * @return  Dato T eliminado.
	 */

	public T eliminar(T dato) 
	{
		T elemento = buscar(dato);

		if(elemento!=null)
		{
			if(primero.darElemento().compareTo(elemento)==0)
			{
				primero = primero.darSiguiente();
				longitud--;
			}
			else
			{
				NodoLista<T> act = primero;
				NodoLista<T> anterior = null;

				while(act!=null&&act.darElemento().compareTo(elemento)!=0)
				{
					anterior = act;
					act = act.darSiguiente();
				}

				if(act.darSiguiente()!=null)
					act.darSiguiente().cambiarAnterior(anterior);

				anterior.cambiarSiguiente(act.darSiguiente());

				if(ultimo==actual)
					ultimo = anterior;
				longitud--;	
			}
		}

		return elemento;
	}

	/**
	 * Método que retorna el primer nodo de la lista.
	 * @return Primer nodo de la lista.
	 */
	public NodoLista<T> darPrimero()
	{
		return primero;
	}

	/**
	 * Método que retorna el último nodo de la lista.
	 * @return Último nodo de la lista.
	 */
	public NodoLista<T> darUltimo()
	{		
		return ultimo;
	}

	/**
	 * Método que retorna un elemento T genérico según su posición en la lista.
	 * @param posicion Posición de la lista del elemento.
	 * @return Retorna el elemento de la posición determinada.
	 */
	public T darElemento(int posicion)
	{
		T elemento = null;

		if(primero!=null&&posicion<longitud)
		{
			int pos = 0;
			NodoLista<T> act = primero;
			while(act!=null&&posicion!=pos)
			{
				act = act.darSiguiente();
				pos++;
			}

			elemento = act.darElemento();

		}

		return elemento;
	}

	/**
	 * Método que retorna el elemento actual. En caso de que no exista retorna null.
	 * @return Elemento del nodo actual.
	 */
	public T elementoActual()
	{
		return actual!=null?actual.darElemento():null;
	}

	/**
	 * Inicia el recorrido de la lista.
	 * @post El nodo actual es igual al primero.
	 */
	public void iniciarRecorrido()
	{
		actual = primero;
	}

	/**
	 * Avanza en la lista siempre y cuando actual!=null
	 * @post Pasa al siguiene elemento de la lista.
	 */
	public void avanzarActual()
	{
		if(actual!=null)
		{
			actual = actual.darSiguiente();
		}
	}

	/**
	 * Retroce en la lista siempre y cuando ant!=null
	 * @post Pasa al anterior elemento de la lista
	 */
	public void retrocederActual()
	{
		NodoLista<T> ant = primero;

		while(ant!=null&&ant.darSiguiente()!=actual)
		{
			ant= ant.darSiguiente();
		}

		actual = ant;

	}

	/**
	 * Iterador que recorre la lista. (No implementado).
	 * @return Iterador que recorre la lista.
	 */
	public Iterator<T> iterator() 
	{
		return new IteratorLista<T>(primero);
	}

	/**
	 * Elimina el último elemento de la lista.
	 * @return Retorna el elemento eliminado.
	 */
	public T eliminarUltimo()
	{
		T elemento = null;


		if(primero!=null)
		{
			elemento=ultimo.darElemento();

			if(primero==ultimo)
			{
				primero = null;
			}
			else
			{
				ultimo.darAnterior().cambiarSiguiente(ultimo.darSiguiente());
			}
			ultimo = ultimo.darAnterior();

			longitud--;
		}

		return elemento;
	}

	/**
	 * Elimina el primer elemento de la lista.
	 * @return Retorna el elemento eliminado.
	 */
	public T eliminarPrimero()
	{
		T elemento = null;

		if(primero!=null)
		{
			elemento=primero.darElemento();

			if(primero==ultimo)
			{
				ultimo = null;
			}
			else
			{
				primero.darSiguiente().cambiarAnterior(primero.darAnterior());
			}
			primero = primero.darSiguiente();

			longitud--;

		}

		return elemento;
	}

	/**
	 * Método que retorna un arreglo de la lista.
	 * @return Retorna arreglo de la lista.
	 */
	public Comparable[] darArreglo()
	{
		Comparable[] comparable = new Comparable[longitud];

		NodoLista<T> actual = primero;
		int i =0;
		while(actual!=null)
		{
			comparable[i] = actual.darElemento();
			actual=actual.darSiguiente();
			i++;
		}

		return comparable;
	}

	public void agregarOrden(T elemento)
	{
		boolean respuesta = false;
		NodoLista<T> nuevo = new NodoLista<T>(elemento);

		if(primero==null)
		{
			primero = nuevo;
			ultimo = primero;
			respuesta = true;
		}
		else if(elemento.compareTo(primero.darElemento())>0)
		{
			nuevo.cambiarSiguiente(primero);
			primero.cambiarAnterior(nuevo);
			primero=nuevo;
			respuesta = true;
		}
		else if(elemento.compareTo(primero.darElemento())<0)
		{
			NodoLista<T> ant = primero;
			NodoLista<T> act = primero.darSiguiente();
			while(act!=null && elemento.compareTo(act.darElemento())<0)
			{
				ant = act;
				act = act.darSiguiente();
			}

			if(act==null||elemento.compareTo(act.darElemento())>0)
			{
				ant.cambiarSiguiente(nuevo);
				nuevo.cambiarAnterior(ant);
				nuevo.cambiarSiguiente(act);
				if(act==null)
					ultimo = nuevo;
				else
					act.cambiarAnterior(nuevo);
				respuesta = true;
			}

		}

		if(respuesta)
			longitud++;
	}
}