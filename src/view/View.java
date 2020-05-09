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
	 * Men� de presentaci�n para el usuario.
	 */
	public void printMenu()
	{
		System.out.println("0. (Requerimiento 00) Cargar ubicaciones en el grafo.");
		System.out.println("1. (Requerimiento 1A) Cargar estaciones de polic�a.");
		System.out.println("2. (Requerimiento 1A) Imprimir grafo.");
		System.out.println("3. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	/**
	 * M�todo que imprime un mensaje gen�rico
	 * @param mensaje Mensaje ingresado.
	 */
	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	/**
	 * M�todo que imprime el toString() de la lista de comparendos ingresada por par�metro.
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
	 * M�todo que imprime el toString() del iterator de comparendos ingresado por par�metro.
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
	 * @param arg Arreglo que ingresa por par�metro.
	 * @param max Valor m�ximo de impresi�n.
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

