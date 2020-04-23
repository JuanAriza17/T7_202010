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
						view.printMessage("\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+Modelo.MAX_DATOS+"), se imprimieron solo "+numComparendos+".\n");
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
						view.printMessage("Por favor ingrese la fecha menor en el siguiente formato: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						String dato1=lector.nextLine();
						SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						Date fecha1=format1.parse(dato1);
						view.printMessage("Por favor ingrese la fecha mayor en el siguiente formato: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						String dato2=lector.nextLine();
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
					String dete=lector.nextLine();
					view.printMessage("Por favor ingrese el vehículo.");
					String vehiculo = lector.nextLine();
					view.printMessage("Por favor ingrese el tipo de servicio.");
					String servicio = lector.nextLine();
					view.printMessage("Por favor ingrese la localidad.");
					String localidad = lector.nextLine();
					
					if(vehiculo.equalsIgnoreCase("automovil")||vehiculo.equalsIgnoreCase("automóvil"))
						vehiculo = "AUTOMÃ“VIL";
					if(servicio.equalsIgnoreCase("público")||servicio.equalsIgnoreCase("público"))
						servicio = "PÃºblico";
					if(localidad.equalsIgnoreCase("antonio nariño"))
						localidad = "ANTONIO NARIÃ‘O";
					
					Iterator<Comparendo> iteratorDeteVehi = modelo.darComparendosPorDeteccionVehiculoLocalidad(dete, vehiculo, servicio, localidad);
					if(iteratorDeteVehi!=null)
					{
						if(numComparendos>Modelo.MAX_DATOS)
						{
							view.printIterator(iteratorDeteVehi, Modelo.MAX_DATOS);
							view.printMessage("\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+Modelo.MAX_DATOS+"), se imprimieron solo "+numComparendos+".\n");
						}
						else
						{
							view.printIterator(iteratorDeteVehi,numComparendos);
							view.printMessage("\nSe imprimieron los "+numComparendos+" comparendos.\n");
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
						view.printMessage("Por favor ingrese la latitud inicial.");
						double latitud1=Double.parseDouble(lector.nextLine());
						view.printMessage("Por favor ingrese la latitud final.");
						double latitud2 = Double.parseDouble(lector.nextLine());
						view.printMessage("Por favor ingrese el vehiculo.");
						String vehi = lector.nextLine();
						
						if(vehi.equalsIgnoreCase("automovil")||vehi.equalsIgnoreCase("automóvil"))
							vehi = "AUTOMÃ“VIL";
						
						view.printMessage(modelo.darComparendosEnRangoLatitudYVehiculo(latitud1, latitud2, vehi));
						
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
