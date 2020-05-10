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
	public final static String RUTA_COMPARENDOS = "./data/Comparendos_DEI_2018_Bogot�_D.C.geojson";
	
	/**
	 * Constante con la ruta del archivo que guarda los vertices del grafo.
	 */
	public final static String RUTA_VERTICES="./data/bogota_vertices.txt";
	
	/**
	 * Constante con la ruta del archivo que guarda los arcos del grafo.
	 */
	public final static String RUTA_ARCOS="./data/bogota_arcos.txt";
	
	/**
	 * Constante con la ruta del archivo que guarda los datos de las estaciones de polic�a.
	 */
	public final static String RUTA_ESTACIONES="./data/estacionpolicia.geojson";

	/**
	 * Constante de impresion.
	 */
	private static final String RUTA_IMPRESION= "./data/grafo.json";
	
	/**
	 * Constante de n�mero de impresi�n en comparendo de consola.
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
							view.printMessage("N�mero de v�rtices: "+grafoTXT.V());
							view.printMessage("N�mero de arcos: "+grafoTXT.E()+"\n");
						}
						catch(FileNotFoundException e)
						{
							view.printMessage("No se encontr� el archivo.\n");
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
							view.printMessage("Cantidad de estaciones de polic�a: "+modelo.darListaEstaciones().darLongitud()+"\n");
							est=true;
						} 
						catch (FileNotFoundException e) 
						{
							view.printMessage("No se encontr� el archivo.\n");
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
							view.printMessage("Se imprimi� con �xito el archivo JSON.\n Por favor revise el directorio de destino: ./data/grafo.json\n");
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
								view.printMessage("N�mero de v�rtices: "+grafoJSON.V());
								view.printMessage("N�mero de arcos: "+grafoJSON.E()+"\n");
								cargadoJson = true;
								view.printMessage("Se carg� correctamente el grafo del archivo JSON.\n");
								
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
						view.printMessage("Los arcos entre v�rtices est�n representados con l�neas de color negro.");
						view.printMessage("Los v�rtices de grafo est�n representados con circulos de color negro.\n");
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
							view.printMessage("Las estaciones de polic�a est�n representadas con circulos de color rojo.");
							view.printMessage("Los arcos entre v�rtices est�n representados con l�neas de color negro.");
							view.printMessage("Los v�rtices de grafo est�n representados con circulos de color negro.");
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
				
				case 9:
					//Carga particular que acorta m�todos (solo para test, no disponible para usuario).
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
							view.printMessage("N�mero de v�rtices: "+grafoTXT.V());
							view.printMessage("N�mero de arcos: "+grafoTXT.E()+"\n");
						}
						catch(FileNotFoundException e)
						{
							view.printMessage("No se encontr� el archivo.\n");
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
					
					if(!est)
					{
						view.printMessage("---------\n");
						try 
						{
							modelo.cargarEstacionPolicia(RUTA_ESTACIONES);
							Iterator<EstacionDePolicia> estaciones= modelo.darListaEstaciones().iterator();
							view.printIterator(estaciones, modelo.darListaEstaciones().darLongitud());
							view.printMessage("Cantidad de estaciones de polic�a: "+modelo.darListaEstaciones().darLongitud()+"\n");
							est=true;
						} 
						catch (FileNotFoundException e) 
						{
							view.printMessage("No se encontr� el archivo.\n");
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
					
					view.printMessage("---------\n"); 
					if(cargado)
					{
						try 
						{
							modelo.imprimirJSON(RUTA_IMPRESION);
							json = true;
							view.printMessage("Se imprimi� con �xito el archivo JSON.\nPor favor revise el directorio de destino: ./data/grafo.json\n");
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
					
					view.printMessage("---------\n"); 
					if(json)
					{
						if(!cargadoJson)
						{
							try 
							{
								modelo.cargarJSON(RUTA_IMPRESION);
								IGrafoNoDirigido<Integer, UbicacionGeografica>grafoJSON=modelo.darGrafoJSON();
								view.printMessage("N�mero de v�rtices: "+grafoJSON.V());
								view.printMessage("N�mero de arcos: "+grafoJSON.E()+"\n");
								cargadoJson = true;
								view.printMessage("Se carg� correctamente el grafo del archivo JSON.\n");
								
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
					
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
				}
			}
			catch(NumberFormatException e)
			{
				view.printMessage("Por favor ingrese un n�mero.\n");
			}


		}
		view.printMessage("--------- \nGracias! Vuelva pronto!");
	}	
}
