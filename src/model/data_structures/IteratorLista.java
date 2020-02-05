package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorLista<T extends Comparable<T>> implements Iterator<T> 
{
	private NodoLista<T> proximo;
	
	private NodoLista<T> ant_prox;
	
	private NodoLista<T> ant_ant;
	
	public IteratorLista(NodoLista<T> primero)
	{
		proximo = primero;
		ant_prox = null;
		ant_ant = null;
	}
	
	public boolean hasNext() 
	{
		return proximo!=null;
	}

	public T next() 
	{
		if(proximo==null)
			throw new NoSuchElementException("No hay próximo");
		
		T elemento = proximo.darElemento();
		
		proximo = proximo.darSiguiente();
		
		return elemento;
	}
	
	public void remove()
	{
		NodoLista<T> prox_prox = null;
		
		if(ant_ant!=null)
		{
			if(proximo!=null)
			{
				prox_prox = proximo.darSiguiente();
			}
			ant_ant.cambiarSiguiente(proximo);
			
			ant_prox = proximo;
			
			proximo = prox_prox;
			
		}
	}

}
