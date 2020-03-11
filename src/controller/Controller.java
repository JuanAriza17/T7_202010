package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.IListaEncadenada;
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
	public final static String RUTA = "./data/comparendos_dei_2018_small.geojson";


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
					IQueue<Comparendo>cola=modelo.darCola().darListaCola();
					view.printMessage("Por favor ingrese el número de comparendos que desea visualizar:\n ");
					int valor= Integer.parseInt(lector.next());
					int contador=0;
					NodoLista<Comparendo>nodo=cola.darElementos().darPrimero();
					while(nodo!=null && contador<valor)
					{
						view.printMessage(nodo.darElemento().toString());
						contador++;
						nodo=nodo.darSiguiente();
					}
					if(contador<valor)
					{
						view.printMessage("\nNo hay suficientes comparendos en la cola, se imprimieron "+contador+ " cuando se solicitaron "+valor+".\n");
					}
					view.printMessage("\n");

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

					int valor2= Integer.parseInt(lector.next());

					for(int i=0; i<heap.darTamano();++i)
					{
						Comparendo actual=(Comparendo) modelo.darHeap().sacarMax();
						auxiliar.agregar(actual);
					}

					for(int i=valor2; i>0; --i)
					{
						Comparendo actual= (Comparendo) modelo.darHeap().darArreglo().darElemento(i);
						view.printMessage(actual.toString());
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
