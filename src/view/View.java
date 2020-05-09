package view;

import java.util.Iterator;

import model.data_structures.IListaEncadenada;
import model.logic.Comparendo;
import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	/**
	 * Menú de presentación para el usuario.
	 */
	public void printMenu()
	{
		System.out.println("0. Cargar ubicaciones en el grafo.");
		System.out.println("1. Cargar estaciones de policía.");
		System.out.println("2. Imprimir archivo JSON con el grafo.");
		System.out.println("3. Cargar archivo JSON con el grafo.");
		System.out.println("4. Generar mapa del grafo.");
		System.out.println("5. Generar mapa del grafo con estaciones de policía.");
		System.out.println("6. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	/**
	 * Método que imprime un mensaje genérico
	 * @param mensaje Mensaje ingresado.
	 */
	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	/**
	 * Método que imprime el toString() de la lista de comparendos ingresada por parámetro.
	 * @param lista Lista de comparendos.
	 */
	public void printLista(IListaEncadenada<Comparendo> lista)
	{
		while(lista.darLongitud()>0)
		{
			System.out.println(lista.eliminarPrimero().toString());
		}	
	}

	/**
	 * Método que imprime el toString() del iterator de comparendos ingresado por parámetro.
	 * @param lista Lista de comparendos.
	 */
	public void printIterator(Iterator iterator, int max)
	{
		while(iterator.hasNext()&&max>0)
		{
			System.out.println(iterator.next().toString());
			--max;
		}
		System.out.println("\n");
	}

	/**
	 * Imprimir arreglo.
	 * @param arg Arreglo que ingresa por parámetro.
	 * @param max Valor máximo de impresión.
	 */
	public void printArreglo(Object[] arg, int max)
	{
		for (int i = 0; i < arg.length&&i<max; ++i) 
		{
			System.out.println(arg[i].toString());
		}
		System.out.println("\n");
	}
}

