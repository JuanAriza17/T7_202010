package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.IArregloDinamico;
import model.data_structures.IQueue;
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
	public final static String RUTA = "./data/comparendos_dei_2018.geojson";


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
		Comparable[] c = null;
		boolean fin = false;
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		int id = 0;

		while( !fin ){
			view.printMenu();
			try
			{
				int option = Integer.parseInt(lector.next());				
				switch(option){
				case 0:
					view.printMessage("--------- \nCargando lista de comparendos");
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
					catch(ParseException e)
					{
						view.printMessage(e.getMessage());
					}
					view.printMessage("\n---------\n" + "Número actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
					break;

				case 1:
					view.printMessage("--------- \n ");
					view.printMessage("No se ha implementado el requerimiento.\n");
					break;

				case 2:
					view.printMessage("--------- \n ");
					IArregloDinamico<Comparendo>heap=modelo.darHeap().darArreglo();
					if(heap.darTamano()==0)
					{
						view.printMessage("Por favor inicialice la lista.\n");
						break;
					}
					for(int i=0; i<heap.darTamano();++i)
					{
						Comparendo actual=heap.darElemento(i);
						view.printMessage(actual.toString());
					}
					view.printMessage("\n");
					break;

				case 3: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;

				default: 
					view.printMessage("--------- \n Opción Invalida !! \n---------");
					break;
				}
			}
			catch(NumberFormatException e)
			{
				view.printMessage("Por favor ingrese un número");
			}


		}
	}	
}
