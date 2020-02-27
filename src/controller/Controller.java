package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

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
				case 1:
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

				case 2:
					view.printMessage("--------- \n ");
					c = modelo.copiarComparendos();
					startTime = System.currentTimeMillis();
					modelo.shellSort(c);
					endTime = System.currentTimeMillis();
					duration = endTime-startTime;

					view.printMessage("Tiempo de ordenamiento: "+duration+ " milisegundos\n");
					view.printUltimosYPrimeros(c);
					view.printMessage("Número actual de comparendos en la lista " + modelo.darLongitud() + "\n---------");						
					break;

				case 3:
					view.printMessage("--------- \n ");
					c = modelo.copiarComparendos();
					startTime = System.currentTimeMillis();
					modelo.mergeSort(c);
					endTime = System.currentTimeMillis();
					duration = endTime-startTime;

					view.printMessage("Tiempo de ordenamiento: "+duration+ " milisegundos\n");
					view.printUltimosYPrimeros(c);
					view.printMessage("Número actual de comparendos en la lista " + modelo.darLongitud() + "\n---------");
					break;

				case 4:
					view.printMessage("--------- \n ");
					c = modelo.copiarComparendos();
					startTime = System.currentTimeMillis();
					modelo.quickSort(c);
					endTime = System.currentTimeMillis();
					duration = endTime-startTime;

					view.printMessage("Tiempo de ordenamiento: "+duration+ " milisegundos\n");
					view.printUltimosYPrimeros(c);
					view.printMessage("Número actual de comparendos en la lista " + modelo.darLongitud() + "\n---------");
					break;


				case 5: 
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
