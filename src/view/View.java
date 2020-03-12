package view;

import java.util.Iterator;

import model.data_structures.IListaEncadenada;
import model.logic.Comparendo;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("0. Cargar Comparendos en la Lista.");
			System.out.println("1. (Requerimiento 00) Cargar N comparendos aleatorios a la MaxColaCP y a la MaxHeapCP");
			System.out.println("2. (Requerimiento 01) Mostrar los  N comparendos que ocurrieron más al norte con MaxColaCP.");
			System.out.println("3. (Requerimiento 02) Mostrar los  N comparendos que ocurrieron más al norte con MaxHeapCP.");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printLista(IListaEncadenada<Comparendo> lista)
		{
			while(lista.darLongitud()>0)
			{
				System.out.println(lista.eliminarPrimero().toString());
			}	
		}
}
