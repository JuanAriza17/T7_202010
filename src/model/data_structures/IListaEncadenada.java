package model.data_structures;

import java.util.Comparator;

public interface IListaEncadenada<T extends Comparable<T>> extends Iterable<T> 
{
	boolean agregar(T dato);
		
	T buscar(T dato);
	
	Object[] darArreglo();
	
	int darLongitud();
	
	T eliminar(T dato);
	
	T darPrimero();
	
	T darUltimo();
}
