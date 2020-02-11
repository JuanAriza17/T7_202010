package model.data_structures;

public interface IQueue<T extends Comparable<T>> 
{
    void enqueue(T elemento);
	
	T dequeue();
	
	boolean isEmpty();
	
	int size();
	
	T peek();
	
	String darLista();
	

}
