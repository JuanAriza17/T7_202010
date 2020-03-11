package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.IListaEncadenada;
import model.data_structures.IMaxColaCP;
import model.data_structures.IQueue;
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
					IMaxColaCP cola=modelo.darColaPrioridad();
					IMaxColaCP colaCopia=cola;
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar:\n ");
					int valor= Integer.parseInt(lector.next());
					int valorCopia=valor;
					boolean excedeTamanoCola=false;
					int contador=0;
					if(valor>cola.darNumElementos())
					{
						valor=cola.darNumElementos();
						excedeTamanoCola=true;
					}
					for(int i=0; i<valor;++i)
					{
						view.printMessage(cola.sacarMax().toString());
						contador++;
					}
					if(excedeTamanoCola==true)
					{
						view.printMessage("\nNo hay suficientes comparendos en la cola, se imprimieron "+contador+ " comparendos cuando se solicitaron "+valorCopia+".\n");
					}
					else
					{
						view.printMessage("\nSe imprimieron "+valor+ " comparendos.\n"); 
					}
					view.printMessage("\nNOTA: Los últimos comparendos fueron los que tuvieron mayor prioridad en la cola.\n");
					cola=colaCopia;
					break;

				case 2:
					view.printMessage("--------- \n ");
					IArregloDinamico<Comparendo>heap=modelo.darHeap().darArreglo();
					IArregloDinamico<Comparendo>auxiliar=new ArregloDinamico(550000);

					if(heap.darTamano()==0)
					{
						view.printMessage("Por favor inicialice la lista.\n");
						break;
					}
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar:\n ");
					int valor2= Integer.parseInt(lector.next());
					int valor2Copia=valor2;

					for(int i=0; i<heap.darTamano();++i)
					{
						Comparendo actual=(Comparendo) modelo.darHeap().sacarMax();
						auxiliar.agregar(actual);
					}
					
					boolean excedeTamano=false;
					if(valor2>heap.darTamano()-1)
					{
						valor2=heap.darTamano()-1;
						excedeTamano=true;
					}
					for(int i=valor2; i>0; --i)
					{
						Comparendo actual= (Comparendo) modelo.darHeap().darArreglo().darElemento(i);
						view.printMessage(actual.toString());
					}
					if(excedeTamano==true)
					{
						view.printMessage("\nNo hay suficientes comparendos en la cola, se imprimieron "+heap.darTamano()+ " comparendos cuando se solicitaron "+valor2Copia+".\n");
					}
					
					view.printMessage("\n");
					view.printMessage(modelo.darMayor()+"\n");
					view.printMessage("\n");
					System.out.println(modelo.darHeap().darArreglo().darElemento(1)+"\n");
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
				view.printMessage("Por favor ingrese un número.\n");
			}


		}
	}	
}
