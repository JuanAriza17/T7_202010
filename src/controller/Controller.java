package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

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
						view.printMessage("--------- \nCargando lista de comparendos");
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
						catch(Exception e)
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
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar.");
					numComparendos=Integer.parseInt(lector.nextLine());
					String mayorGravedadInfraccion=modelo.darMComparendosConMayorGravedad(numComparendos);
					view.printMessage(mayorGravedadInfraccion);
					break;
				
				case 2:
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar.");
					numComparendos=Integer.parseInt(lector.nextLine());
					view.printMessage("Por favor ingrese el día de la semana con el siguiente formato: (L, M, I, J, V, S, D).");
					String dia=lector.nextLine();
					view.printMessage("Por favor ingrese el número del mes: (1-12).");
					int mes=Integer.parseInt(lector.nextLine());
					Iterator<Comparendo> iteratorMesDia=modelo.darComparendosPorMesYDiaSemana(mes,dia);
					
					if(numComparendos>Modelo.MAX_DATOS)
					{
						view.printIterator(iteratorMesDia, Modelo.MAX_DATOS);
						view.printMessage("\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+numComparendos+"), se imprimieron solo "+Modelo.MAX_DATOS+".\n");
					}
					else
					{
						view.printIterator(iteratorMesDia,numComparendos);
						view.printMessage("\nSe imprimieron los "+numComparendos+" comparendos.\n");
					}
					view.printMessage("\n");
					break;
					
				case 3:
					try 
					{
						if(cargado==false)
						{
							view.printMessage("Por favor inicialice la lista de comparendos.\n");
							break;
						}
						view.printMessage("--------- \n ");
						view.printMessage("Por favor ingrese la fecha menor en el siguiente formato: YYYY/MM/DD-HH:MM:ss. (i.e) 2018/01/01-00:00:00");
						String dato1=modelo.transformarFormatoFecha(lector.nextLine());
						SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						Date fecha1=format1.parse(dato1);
						view.printMessage("Por favor ingrese la fecha mayor en el siguiente formato: YYYY/MM/DD-HH:MM:ss. (i.e) 2018/12/31-11:59:59");
						String dato2=modelo.transformarFormatoFecha(lector.nextLine());
						SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						Date fecha2=format2.parse(dato2);
						view.printMessage("Por favor ingrese la localidad:");
						String localidad=lector.nextLine().trim();
						String retorno=modelo.darComparendosEnRangoDeFechaYLocalidad(fecha1, fecha2, localidad);
						view.printMessage(retorno);
					} 
					catch (ParseException e) 
					{
						view.printMessage("Formato inválido de la fecha.\n");
					}
					break;
					
				case 4:
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar.");
					numComparendos=Integer.parseInt(lector.nextLine());
					String menorDistancia=modelo.darMComparendosMasCercaEstacion(numComparendos);
					view.printMessage(menorDistancia);
					break;
				
				case 5:
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar.");
					numComparendos=Integer.parseInt(lector.nextLine());
					view.printMessage("Por favor ingrese el medio de detección.");
					String dete=lector.nextLine().toLowerCase();
					view.printMessage("Por favor ingrese el vehículo (Tenga buena ortografía).");
					String vehiculo = lector.nextLine().toLowerCase();
					view.printMessage("Por favor ingrese el tipo de servicio (Tenga buena ortografía).");
					String servicio = lector.nextLine().toLowerCase();
					view.printMessage("Por favor ingrese la localidad.");
					String localidad = lector.nextLine().toLowerCase();
					
					Comparable[] arregloDeteVehi = modelo.darComparendosPorDeteccionVehiculoLocalidad(dete, vehiculo, servicio, localidad);
					if(arregloDeteVehi!=null)
					{
						if(numComparendos>Modelo.MAX_DATOS)
						{
							view.printArreglo(arregloDeteVehi, Modelo.MAX_DATOS);
							view.printMessage("\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+numComparendos+"), se imprimieron solo "+Modelo.MAX_DATOS +" ordenados por fecha de menor a mayor.\n");
						}
						else
						{
							view.printArreglo(arregloDeteVehi,numComparendos);
							view.printMessage("\nSe imprimieron los "+numComparendos+" comparendos ordenados por fecha de menor a mayor. \n");
						}
					}
					else
					{
						view.printMessage("No se encontraron comparendos con los parámetros dados.");
					}
					view.printMessage("\n");
					break;
					
				case 6:
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					
					try
					{
						view.printMessage("--------- \n ");
						view.printMessage("Por favor ingrese el rango de latitud de la forma [latitud_inicial,latitud_final].");
						String rango=lector.nextLine();
						view.printMessage("Por favor ingrese el vehiculo.");
						String vehi = lector.nextLine();
						
						String latitudes[] = rango.replace('[', ',').replace(']', ',').split(",");
						
						view.printMessage(modelo.darComparendosEnRangoLatitudYVehiculo(Double.parseDouble(latitudes[1]), Double.parseDouble(latitudes[2]), vehi));
						
						view.printMessage("\n");	
					}
					catch(Exception e)
					{
						view.printMessage("Ingrese una latitud válida.\n");
					}
								
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
