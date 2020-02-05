package model.data_structures;

public class NodoLista<T>
{
	private NodoLista<T> siguiente;
	
	private T elemento;
	
	public NodoLista(T dato)
	{
		elemento = dato;
		siguiente = null;
	}
	
	public void cambiarSiguiente(NodoLista<T> sig)
	{
		siguiente = sig;
	}
	
	public T darElemento()
	{
		return elemento;
	}
	
	public NodoLista<T> darSiguiente()
	{
		return siguiente;
	}
}
