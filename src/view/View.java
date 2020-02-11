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
			System.out.println("1. Cargar Comparendos en la Pila y la Cola.");
			System.out.println("2. Dar una cola con la mayor cantidad de comparendos con la misma infracción seguida.");
			System.out.println("3. Dar una cola con una cantidad dada de comparendos que concuerden con la infracción dada.");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			
			while(!modelo.darStack().isEmpty())
			{
				System.out.println(modelo.eliminarStack().toString()+"\n");
			}
		}
}
