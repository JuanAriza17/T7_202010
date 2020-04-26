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
		System.out.println("0. (Requerimiento 00) Cargar Comparendos en la Lista.");
		System.out.println("1. (Requerimiento 1A) Dar los M comparendos con mayor gravedad.");
		System.out.println("2. (Requerimiento 2A) Dar los comparendos en un mes y d�a de la semana dados.");
		System.out.println("3. (Requerimiento 3A) Dar comparendos que tienen una fecha-hora en un rango y son de una localidad dada.");
		System.out.println("4. (Requerimiento 1B) Dar los M comparendos m�s cercanos a la estaci�n de polic�a.");
		System.out.println("5. (Requerimiento 2B) Dar los comparendos con medio de detecci�n, clase de veh�culo, tipo de servicio y localidad dados.");
		System.out.println("6. (Requerimiento 3B) Dar los comparendos que tienen una latitud en un rango dado y que involucraron un tipo de veh�culo particular dado.");
		System.out.println("7. (Requerimiento 1C) Generar tabla ASCII de los datos");
		System.out.println("8. (Requerimiento 2C) Generar el costo de los tiempos de espera hoy en d�a.");
		System.out.println("9. (Requerimiento 3C) Generar el costo de los tiempos de espera seg�n el nuevo sistema.");
		System.out.println("10. Exit");
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
	public void printIterator(Iterator<Comparendo> iterator, int max)
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

