package model.data_structures;

public class Stack<T extends Comparable<T>> implements IStack<T>
{
	
		
	private IListaEncadenada<T> elementos;	
	
	public Stack()
	{
		elementos = new ListaEncadenada<T>();
	}
	
	public void push(T elemento) 
	{
		elementos.agregarFinal(elemento);
	}

	public T pop() 
	{
		T elemento = elementos.eliminarUltimo();
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

	public T peek() 
	{
		NodoLista<T> elemento = elementos.darUltimo();

		return elemento!=null?elemento.darElemento():null;	
	}
	
	public String darLista()
	{
		String mensaje="";
		
		for (int i = 0; i < elementos.darLongitud(); i++)
		{
			mensaje+=elementos.darElemento(i).toString()+"\n";
		}
		
		return mensaje;
	}

}
