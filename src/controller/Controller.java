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
		IQueue<Comparendo> cola = null;
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
					cola = modelo.darColaInfracciones();
					
					if(cola==null)
					{
						view.printMessage("La cola está vacía");
					}
					else
					{
						view.printMessage("Hay un total de "+cola.size()+" comparendos seguidos con la infracción "+ cola.peek().darInfraccion()+"\n");	
						view.printMessage(cola.darLista()+"\n");
					}
					
					view.printMessage("Numero actual de comparendos en la cola " + modelo.darTamanoCola() + "\n---------");						
					break;
				
				case 3:
					view.printMessage("--------- \n ");
					view.printMessage("Ingrese el comparendo que desee buscar: ");
					String inf = lector.next().trim();
					view.printMessage("Ingrese la cantidad de comparendos que quiere: ");
					int valor = lector.nextInt(); 
					
					cola = modelo.darColaNUltimos(valor, inf);
					
					if(cola==null)
					{
						view.printMessage("La pila está vacía");
					}
					else
					{
						if(cola.size()==valor)
						{
							view.printMessage("Se encontraron los  "+cola.size()+" comparendos con la infracción "+ cola.peek().darInfraccion()+"\n");	
						}
						else if(cola.size()==0)
						{
							view.printMessage("No se encontraron comparendos con la infracción "+inf);
						}
						else
						{
							view.printMessage("Solamente se encontraron "+cola.size()+" comparendos con la infraccion "+inf);
						}
						view.printMessage(cola.darLista()+"\n");
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
