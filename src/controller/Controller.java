package controller;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.IListaEncadenada;
import model.data_structures.IMaxColaCP;
import model.data_structures.IQueue;
import model.data_structures.MaxColaCP;
import model.data_structures.NodoLista;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/**
	 * Atributo del modelo.
	 */
	private Modelo modelo;

	/**
	 * Atributo de la vista.
	 */
	private View view;

	/**
	 * Constante con la ruta del archivo que guarda los comparnedos.
	 */
	public final static String RUTA = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";


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
		lector.useDelimiter("\n");
		boolean cargado = false;
		boolean fin = false;


		while( !fin ){
			view.printMenu();
			try
			{
				int option = Integer.parseInt(lector.nextLine());				
				switch(option){
				case 0:
					if(!cargado)
					{
						view.printMessage("--------- \nCargando lista de comparendos");
						view.printMessage("--------- \nCargando lista de comparendos en la lista ");
						try
						{
							modelo.cargarComparendos(RUTA);
							cargado = true;
							view.printMessage("Comparendos cargados a la lista");
							view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
							view.printMessage("Comparendo con mayor ID: \n"+modelo.darMayorId().toString()+"\n");
							cargado = true;
						}
						catch(FileNotFoundException e)
						{
							view.printMessage("No se pudo crear la lista porque no existe el archivo de comparendos");
							view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
						}
						catch(ParseException e)
						{
							view.printMessage("Ocurrió un error cargando los comparendos");
							view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	

						}
					}
					else
					{
						view.printMessage("La lista de comparendos ya ha sido cargada.");
					}

					break;
					
				case 1:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
				
				case 2:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
					
				case 3:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
					
				case 4:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
				
				case 5:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
					
				case 6:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
				
				case 7:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementda el requerimiento");						
					break;
				
				case 8:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementado el requerimiento");						
					break;
				
				case 9:
					view.printMessage("--------- \n ");
					
					view.printMessage("Aún no se ha implementda el requerimiento");						
					break;
					
				case 10: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
				}
			}
			catch(NumberFormatException e)
			{
				view.printMessage("Por favor ingrese un número.\n");
			}


		}
		view.printMessage("--------- \nGracias! Vuelva pronto!");
	}	
}
