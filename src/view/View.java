package view;

import java.util.Iterator;

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
			System.out.println("0. (Requerimiento 00) Cargar Comparendos en la Lista.");
			System.out.println("1. (Requerimiento 01) Mostrar los  N comparendos que ocurrieron más al norte con MaxColaCP.");
			System.out.println("2. (Requerimiento 02) Mostrar los  N comparendos que ocurrieron más al norte con MaxHeapCP.");
			System.out.println("3. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printUltimosYPrimeros(Comparable[] a)
		{
			System.out.println("Primeros: ");
			for (int i = 0; i < 10; i++) 
			{
				System.out.println(a[i].toString());
			}
			System.out.println("Últimos: ");
			for (int i = a.length-11; i < a.length; i++) 
			{
				System.out.println(a[i].toString());
			}
		}
}
