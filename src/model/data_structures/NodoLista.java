package model.data_structures;

public class NodoLista<T>
{
	private NodoLista<T> siguiente;
	
	private NodoLista<T> anterior;
	
	private T elemento;
	
	public NodoLista(T dato)
	{
		elemento = dato;
		siguiente = null;
		anterior = null;
	}
	
	public void cambiarSiguiente(NodoLista<T> sig)
	{
		siguiente = sig;
	}
	
	public void cambiarAnterior(NodoLista<T> ant)
	{
		anterior = ant;
	}
	
	public T darElemento()
	{
		return elemento;
	}
	
	public NodoLista<T> darSiguiente()
	{
		return siguiente;
	}
	
	public NodoLista<T> darAnterior()
	{
		return anterior;
	}
}
