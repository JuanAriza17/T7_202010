package controller;

import java.io.FileNotFoundException;
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
		boolean fin = false;
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		int id = 0;
		int valor = 0;
		boolean primeraCopiaCola=false;
		IMaxColaCP<Comparendo> colaCopia;

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
					
					try
					{
						if(modelo.darLongitud()!=0)
						{
							view.printMessage("\nIngrese el número de comparendos aleatorios que desea agregar a la MaxColaCP y a la MaxHeapCP:");
							int n = Integer.parseInt(lector.next());
							modelo.generarMuestra(n);
							startTime = System.currentTimeMillis();
							modelo.cargarHeap();
							endTime = System.currentTimeMillis();
							
							duration = endTime-startTime;
							
							String mHeap = "Tiempo de carga en MaxHeapCP: "+duration+" milisegundos";

							startTime = System.currentTimeMillis();
							modelo.cargarCola();
							endTime = System.currentTimeMillis();
							
							duration = endTime-startTime;
							String mCola = "Tiempo de carga en MaxColaCP: "+duration+" milisegundos";

							
							if(modelo.darHeap().darNumElementos()<n)
							{
								System.out.println("La muestra fue demasiado grande por lo que solo se cargaron "+modelo.darHeap().darNumElementos()+" comparendos.");
							}
							else
							{
								System.out.println("Se cargaron "+n+" comparendos a la cola y al heap");
							}
							
							view.printMessage(mCola+"\n"+mHeap+"\n");
						}
						else
							view.printMessage("No ha inicializado la lista.");
					}
					catch(NumberFormatException e)
					{
						view.printMessage("Ingrese un número válido.");
					}
					
					break;

				case 2:
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar:\n ");
					valor= Integer.parseInt(lector.next());
					view.printMessage("Ingrese los vehiculos que le interesan, de la forma Vehiculo1,Vehiculo2,..,Vehiculon");
					String vehiculos = lector.next();
					
					try
					{
						if(modelo.darCola().darNumElementos()!=0)
						{
							startTime = System.currentTimeMillis();
							IListaEncadenada<Comparendo> lista=modelo.colaComparendosMasAlNorte(valor,vehiculos);
							endTime = System.currentTimeMillis();
							
							duration=endTime-startTime;
							
							int numero = lista.darLongitud();
									
							
							view.printLista(lista);
							view.printMessage("\n\n Tiempo: "+duration+" milisegundos.");
							if(numero<valor )
								view.printMessage("\nNo hay suficientes comparendos en la cola, se imprimieron "+lista.darLongitud()+ " comparendos cuando se solicitaron "+valor+".\n");
							else
								view.printMessage("\nSe imprimieron "+valor+ " comparendos.\n"); 
					
							view.printMessage("\nNOTA: Los últimos comparendos fueron los que tuvieron menor prioridad en la cola.\n");

						}
						else
							view.printMessage("No ha inicializado la MaxColaCP ni el MaxHeapCP, por favor ejecute 1.");

					}
					catch(NumberFormatException e)
					{
						view.printMessage("Ingrese un número válido.");
					}
					break;

				case 3:
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar:\n ");
					valor= Integer.parseInt(lector.next());
					view.printMessage("Ingrese los vehiculos que le interesan, de la forma: Vehiculo1,Vehiculo2,..,VehiculoN.\nTenga cuidado de no dejar espacios entre los tipos de vehículos.\n");
					String vehiculos1 = lector.next();
					
					try
					{
						if(modelo.darHeap().darNumElementos()!=0)
						{
							startTime = System.currentTimeMillis();
							IListaEncadenada<Comparendo> lista=modelo.heapComparendosMasAlNorte(valor,vehiculos1);
							endTime = System.currentTimeMillis();
							int numero = lista.darLongitud();
							
							duration = endTime-startTime;
							view.printLista(lista);
							view.printMessage("\n\n Tiempo: "+duration+" milisegundos.");

							if(numero<valor)
							{
								view.printMessage("\nNo hay suficientes comparendos en la cola, se imprimieron "+lista.darLongitud()+ " comparendos cuando se solicitaron "+valor+".\n");
							}
							else
							{
								view.printMessage("\nSe imprimieron "+valor+ " comparendos.\n"); 
							}
							view.printMessage("\nNOTA: Los últimos comparendos fueron los que tuvieron menor prioridad en la cola.\n");
						}
						else
							view.printMessage("No ha inicializado la MaxColaCP ni el MaxHeapCP, por favor ejecute 1.");
							
					}
					catch(NumberFormatException e)
					{
						view.printMessage("Ingrese un número válido.");
					}
					break;

				case 4: 
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
				view.printMessage("Por favor ingrese un número.\n");
			}


		}
	}	
}
