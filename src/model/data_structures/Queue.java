package model.data_structures;

public class Queue<T extends Comparable<T>> implements IQueue<T> 
{

	
	private IListaEncadenada<T> elementos;	
	
	public Queue()
	{
		elementos = new ListaEncadenada<T>();
	}
	
	public void enqueue(T elemento) 
	{
		elementos.agregarFinal(elemento);
	}

	public T dequeue() 
	{
		T elemento = elementos.eliminarPrimero();
		
		return elemento;
	}

	public boolean isEmpty() 
	{
		return elementos.darPrimero()==null;
	}

	public int size() 
	{
		return elementos.darLongitud();
	}

	public T pick() 
	{
		NodoLista<T> elemento = elementos.darPrimero();

		return elemento!=null?elemento.darElemento():null;	
	}

}
