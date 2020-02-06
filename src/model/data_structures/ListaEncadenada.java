package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class ListaEncadenada<T extends Comparable<T>> implements IListaEncadenada<T>
{
	private int longitud;
	
	private NodoLista<T> primero;
	
	private NodoLista<T> actual;
	
	private NodoLista<T> ultimo;

	
	public ListaEncadenada()
	{
		longitud = 0;
		primero = null;
		actual = null;
	}
	
	public void agregarFinal(T dato)
	{
		NodoLista<T> nuevo = new NodoLista<T>(dato);

		if(primero!=null)
		{
			ultimo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
			longitud++;
		}
		else
		{
			primero = nuevo;
			ultimo = primero;
			longitud++;
		}
	}

	public void agregar(T dato)
	{
		if(primero == null)
		{
			primero = new NodoLista<T>(dato);
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
			longitud++;
		}
	}

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
			primero = nuevo;
			longitud++;
		}
		
	}
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
				NodoLista<T> act = primero;
				NodoLista<T> anterior = null;
				
				while(act!=null&&act.darElemento().compareTo(elemento)!=0)
				{
					anterior = act;
					act = act.darSiguiente();
				}
				
				anterior.cambiarSiguiente(act.darSiguiente());
				if(ultimo==actual)
					ultimo = anterior;
				longitud--;	
			}
		}
		
		return elemento;
	}
	
	public T darPrimero()
	{
		T elemento = null;
		if(primero!=null)
		{
			elemento = primero.darElemento();
		}
		return elemento;
	}
	
	public T darUltimo()
	{
		T elemento = null;

		if(primero!=null)
		{
			NodoLista<T> act = primero;
			
			while(act!=null&&act.darSiguiente()!=null)
			{
				act = act.darSiguiente();
			}
			
			elemento = act.darElemento();
		}
		
		
		return elemento;
	}
	
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
	
	public T elementoActual()
	{
		return actual!=null?actual.darElemento():null;
	}
	
	public void iniciarRecorrido()
	{
		actual = primero;
	}
	
	public void avanzarActual()
	{
		if(actual!=null)
		{
			actual = actual.darSiguiente();
		}
	}
	public void retrocederActual()
	{
		NodoLista<T> ant = primero;
		
		while(ant!=null&&ant.darSiguiente()!=actual)
		{
			ant= ant.darSiguiente();
		}
		
		actual = ant;
		
	}

	public Iterator<T> iterator() 
	{
		return new IteratorLista<T>(primero);
	}

}
