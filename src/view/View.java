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
			System.out.println("1. Cargar Comparendos en la Lista.");
			System.out.println("2. Buscar Comparendos por ID");
			System.out.println("3. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			Iterator<Comparendo> lista = modelo.darLista().iterator();
			
			while(lista.hasNext())
			{
				System.out.println(lista.next().darId());
			}
		}
}
