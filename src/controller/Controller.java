package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	public final static String RUTA = "./data/comparendos_dei_2018_small.geojson";

	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		int id = 0;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();				
			switch(option){
				case 1:
					view.printMessage("--------- \nCargando lista de comparendos en la pila y en la cola ");
				    try
				    {
				    	modelo.cargarComparendos(RUTA);
				    	view.printMessage("Pila y Cola de comparendos creada");
				    	view.printMessage("PRIMERO: \n"+modelo.darPrimerComparendo().toString());
				    	view.printMessage("ÚLTIMO: \n"+modelo.darUltimoComparendo().toString());
				    }
				    catch(FileNotFoundException e)
				    {
				    	view.printMessage("No se pudo crear la lista porque no existe el archivo de comparendos");
				    }
				    view.printMessage("\n---------\n" + "Numero actual de comparendos en la pila " + modelo.darTamanoPila()+"\n");	
				    view.printMessage("Numero actual de comparendos en la cola " + modelo.darTamanoCola() + "\n---------");	
				    break;

				case 2:
					view.printMessage("--------- \n ");
					IQueue<Comparendo> cola = modelo.darColaInfracciones();
					
					if(cola==null)
					{
						view.printMessage("La cola está vacía");
					}
					else
					{
						view.printMessage("Hay un total de "+cola.size()+" comparendos seguidos con la infracción "+ cola.pick().darInfraccion()+"\n");	
						while(!cola.isEmpty())
						{
							view.printMessage(cola.dequeue().toString()+"\n");
						}
					}
					
					view.printMessage("Numero actual de comparendos en la cola " + modelo.darTamanoCola() + "\n---------");						
					break;
				
				case 3:
					view.printMessage("--------- \n ");
					IStack<Comparendo> pila = modelo.darPilaInfracciones();
					
					if(pila==null)
					{
						view.printMessage("La pila está vacía");
					}
					else
					{
						view.printMessage("Hay un total de "+pila.size()+" comparendos seguidos con la infracción "+ pila.pick().darInfraccion()+"\n");	
						while(!pila.isEmpty())
						{
							view.printMessage(pila.pop().toString()+"\n");
						}
					}
					view.printMessage("Numero actual de comparendos en la pila " + modelo.darTamanoPila() + "\n---------");
					break;
				
				
				
					
				case 4: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
