package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.IGrafoNoDirigido;
import model.logic.EstacionDePolicia;
import model.logic.Maps;
import model.logic.Modelo;
import model.logic.UbicacionGeografica;
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
	public final static String RUTA_COMPARENDOS = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";
	
	/**
	 * Constante con la ruta del archivo que guarda los vertices del grafo.
	 */
	public final static String RUTA_VERTICES="./data/bogota_vertices.txt";
	
	/**
	 * Constante con la ruta del archivo que guarda los arcos del grafo.
	 */
	public final static String RUTA_ARCOS="./data/bogota_arcos.txt";
	
	/**
	 * Constante con la ruta del archivo que guarda los datos de las estaciones de policía.
	 */
	public final static String RUTA_ESTACIONES="./data/estacionpolicia.geojson";

	/**
	 * Constante de impresion.
	 */
	private static final String RUTA_IMPRESION= "./data/grafo.json";
	
	/**
	 * Constante de número de impresión en comparendo de consola.
	 */
	public final static int numImpresiones=20;

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
		boolean est = false;
		boolean fin = false;
		boolean json = false;
		boolean cargadoJson = false;

		while( !fin ){
			view.printMenu();
			try
			{
				int option = Integer.parseInt(lector.nextLine());				
				switch(option){
				case 0:
					if(!cargado)
					{
						view.printMessage("--------- \nCargando ubicaciones en mapa...");
						try
						{
							modelo.cargarVertices(RUTA_VERTICES);
							modelo.cargarArcos(RUTA_ARCOS);
							cargado = true;
							view.printMessage("Ubicaciones cargadas en el mapa.\n");
							IGrafoNoDirigido<Integer, UbicacionGeografica>grafoTXT=modelo.darGrafoTXT();
							view.printMessage("Número de vértices: "+grafoTXT.V());
							view.printMessage("Número de arcos: "+grafoTXT.E()+"\n");
						}
						catch(FileNotFoundException e)
						{
							view.printMessage("No se encontró el archivo.\n");
						}
						catch(Exception e)
						{
							view.printMessage("Error al cargar las ubicaciones.\n");
						}
					}
					else
					{
						view.printMessage("Ya se han cargado las ubicaciones en el mapa.\n");
					}

					break;

				case 1:
					if(!est)
					{
						view.printMessage("---------\n");
						try 
						{
							modelo.cargarEstacionPolicia(RUTA_ESTACIONES);
							Iterator<EstacionDePolicia> estaciones= modelo.darListaEstaciones().iterator();
							view.printIterator(estaciones, modelo.darListaEstaciones().darLongitud());
							view.printMessage("Cantidad de estaciones de policía: "+modelo.darListaEstaciones().darLongitud()+"\n");
							est=true;
						} 
						catch (FileNotFoundException e) 
						{
							view.printMessage("No se encontró el archivo.\n");
						} 
						catch (Exception e) 
						{
							view.printMessage("Error al cargar las estaciones.\n");
						} 					
					}
					else
					{
						view.printMessage("Ya se han cargado las estaciones en la lista.\n");
					}
					
					break;
					
				case 2:
					view.printMessage("---------\n"); 
					if(cargado)
					{
						try 
						{
							modelo.imprimirJSON(RUTA_IMPRESION);
							json = true;
						} 
						catch (Exception e) 
						{
							view.printMessage("Error al imprimir el grafo.\n");
						} 
					}
					else
					{
						view.printMessage("Por favor cargue el grafo.");
					}
					break;
				
				case 3:
					view.printMessage("---------\n"); 
					if(json)
					{
						if(!cargadoJson)
						{
							try 
							{
								modelo.cargarJSON(RUTA_IMPRESION);
								IGrafoNoDirigido<Integer, UbicacionGeografica>grafoJSON=modelo.darGrafoJSON();
								view.printMessage("Número de vértices: "+grafoJSON.V());
								view.printMessage("Número de arcos: "+grafoJSON.E()+"\n");
								cargadoJson = true;
								
							} 
							catch (Exception e) 
							{
								view.printMessage("Error al cargar el grafo.\n");
								e.printStackTrace();
							} 
						}
						else
						{
							view.printMessage("Ya cargo el grafo del JSON");
						}
					}
					else
					{
						view.printMessage("Por favor cree el archivo JSON.");
					}
					break;
					
				case 4:
					if(cargadoJson)
					{
						modelo.generarMapa();
					}
					else
					{
						view.printMessage("Por favor cargue el archivo JSON.");
					}
					break;
					
				case 5:
					if(cargadoJson)
					{
						if(est)
						{
							modelo.generarMapaConEstaciones();
						}
						else
						{
							view.printMessage("Por favor cargue las estaciones.");
						}
					}
					else
					{
						view.printMessage("Por favor cargue el archivo JSON.");
					}
					break;
					
				case 6:
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
