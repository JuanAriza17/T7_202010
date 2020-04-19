package controller;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
	public final static String RUTA = "./data/Comparendos_DEI_2018_Bogot�_D.C.geojson";
	
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
							view.printMessage("Ocurri� un error cargando los comparendos");
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
					view.printMessage("Por favor ingrese el n�mero de comparendos que desea visualizar.");
					int numComparendosMayorGravedadInfraccion=Integer.parseInt(lector.nextLine());
					String mayorGravedadInfraccion=modelo.darMComparendosConMayorGravedad(numComparendosMayorGravedadInfraccion);
					view.printMessage(mayorGravedadInfraccion);
					break;
				
				case 2:
					if(cargado==false)
					{
						view.printMessage("Por favor inicialice la lista de comparendos.\n");
						break;
					}
					view.printMessage("--------- \n ");
					view.printMessage("Por favor ingrese el n�mero de comparendos que desea visualizar.");
					int numComparendosMesDia=Integer.parseInt(lector.nextLine());
					view.printMessage("Por favor ingrese el d�a de la semana con el siguiente formato: (L, M, I, J, V, S, D).");
					String dia=lector.nextLine();
					view.printMessage("Por favor ingrese el n�mero del mes: (1-12).");
					int mes=Integer.parseInt(lector.nextLine());
					Iterator<Comparendo> iteratorMesDia=modelo.darComparendosPorMesYDiaSemana(mes,dia);
					while(iteratorMesDia.hasNext()&&numComparendosMesDia>0)
					{
						Comparendo actual=iteratorMesDia.next();
						if((actual.darFecha().getMonth()+1)==mes)
						{
							view.printMessage(actual.toString());
							--numComparendosMesDia;
						}
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
						view.printMessage("Formato inv�lido de la fecha.\n");
					}
					break;
					
				case 4:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementado el requerimiento");						
					break;
				
				case 5:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementado el requerimiento");						
					break;
					
				case 6:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementado el requerimiento");						
					break;
				
				case 7:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementda el requerimiento");						
					break;
				
				case 8:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementado el requerimiento");						
					break;
				
				case 9:
					view.printMessage("--------- \n ");
					
					view.printMessage("A�n no se ha implementda el requerimiento");						
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
				view.printMessage("Por favor ingrese un n�mero.\n");
			}


		}
		view.printMessage("--------- \nGracias! Vuelva pronto!");
	}	
}
