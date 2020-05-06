package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.data_structures.IGrafoNoDirigido;
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
	public final static String RUTA_ESTACIONES="./data/estacionpolicia.geojson.json";

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
		boolean fin = false;
		int numComparendos = 0;

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
							IGrafoNoDirigido<Integer, UbicacionGeografica>grafo=modelo.darGrafo();
							view.printMessage("Número de vértices: "+grafo.V());
							view.printMessage("Número de arcos: "+grafo.E());
						}
						catch(FileNotFoundException e)
						{
							
						}
						catch(Exception e)
						{
								

						}
					}
					else
					{
						view.printMessage("Ya se han cargado las ubicaciones en el mapa.");
					}

					break;

				case 1:
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
