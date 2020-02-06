package controller;

import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.util.Scanner;

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
		Comparendo c = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();				
			switch(option){
				case 1:
					view.printMessage("--------- \nCargando lista de comparendos en  la lista ");
				    try
				    {
				    	modelo.cargarComparendos(RUTA);
				    	view.printMessage("Lista de comparendos creada");
				    	view.printMessage("PRIMERO: \n"+modelo.darPrimerComparendo().toString());
				    	view.printMessage("ÚLTIMO: \n"+modelo.darUltimoComparendo().toString());
				    }
				    catch(FileNotFoundException e)
				    {
				    	view.printMessage("No se pudo crear la lista porque no existe el archivo de comparendos");
				    }
				    view.printMessage("Numero actual de comparendos " + modelo.darLongitud() + "\n---------");						
				    break;

				case 2:
					view.printMessage("--------- \nDar ID del comparendo a buscar: ");
					id = lector.nextInt();
					c =  modelo.buscar(new Comparendo(id,"","","","","","",null));
					if ( c != null)
					{
						view.printMessage("Comparendo Encontrado: \n"+ c.toString());
					}
					else
					{
						view.printMessage("Comparendo NO encontrado");
					}
					view.printMessage("Numero actual de comparendos " + modelo.darLongitud() + "\n---------");						
					break;
					
				case 3: 
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
