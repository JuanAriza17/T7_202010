package model.data_structures;

public interface IStack<T extends Comparable<T>> 
{
	void push(T elemento);
	
	T pop();
	
	boolean isEmpty();
	
	int size();
	
	T peek();
	
	String darLista();
}
