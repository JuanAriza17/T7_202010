package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class ListaEncadenada<T extends Comparable<T>> implements IListaEncadenada<T>
{
	private int longitud;
	
	private NodoLista<T> primero;
	
	public ListaEncadenada()
	{
		longitud = 0;
		primero = null;
	}

	public boolean agregar(T dato)
	{
		boolean agregado = false;
		if(primero == null)
		{
			primero = new NodoLista<T>(dato);
			agregado = true;
			longitud++;
		}
		else
		{
			NodoLista<T> actual = primero;
			
			while(actual!=null&&actual.darSiguiente()!=null)
			{
				actual = actual.darSiguiente();
			}
			
			NodoLista<T> nuevo = new NodoLista<T>(dato);
			actual.cambiarSiguiente(nuevo);
			agregado = true;
			longitud++;
		}
		return agregado;
	}

	public T buscar(T dato) 
	{
		T elemento = null;
		NodoLista<T> actual = primero;

		while(actual!=null&&actual.darElemento().compareTo(dato)!=0)
		{
			actual = actual.darSiguiente();
		}
		if(actual!=null)
		{
			elemento = actual.darElemento();
		}
		return elemento;
	}

	public Object[] darArreglo() 
	{
		Object[] arreglo = new Object[longitud];
		NodoLista<T> actual = primero;
		for (int i = 0; i < arreglo.length&&actual!=null; i++) 
		{
			arreglo[i]=actual;
			actual = actual.darSiguiente();
		}
		return arreglo;
	}

	public int darLongitud() 
	{
		return longitud;
	}

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
				NodoLista<T> actual = primero;
				NodoLista<T> anterior = null;
				
				while(actual!=null&&actual.darElemento().compareTo(elemento)!=0)
				{
					anterior = actual;
					actual = actual.darSiguiente();
				}
				
				anterior.cambiarSiguiente(actual.darSiguiente());
				longitud--;	
			}
		}
		
		return elemento;
	}
	
	public T darPrimero()
	{
		return primero.darElemento();
	}
	
	public T darUltimo()
	{
		NodoLista<T> actual = primero;
		
		while(actual!=null&&actual.darSiguiente()!=null)
		{
			actual = actual.darSiguiente();
		}
		
		return actual.darElemento();
	}

	public Iterator<T> iterator() 
	{
		return new IteratorLista<T>(primero);
	}

}
