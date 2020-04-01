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
	    
		public void printMenu()
		{
			System.out.println("0. (Requerimiento 00) Cargar Comparendos en la Lista.");
			System.out.println("1. (Requerimiento 1A) Dar los M comparendos con mayor gravedad.");
			System.out.println("2. (Requerimiento 2A) Dar los comparendos en un mes y día de la semana dados.");
			System.out.println("3. (Requerimiento 3A) Dar comparendos que tienen una fecha-hora en un rango y son de una localidad dada.");
			System.out.println("4. (Requerimiento 1B) Dar los M comparendos más cercanos a la estación de policía.");
			System.out.println("5. (Requerimiento 2B) Dar los comparendos con medio de detección, clase de vehículo, tipo de servicio y localidad dados.");
			System.out.println("6. (Requerimiento 3B) Dar los comparendos que tienen una latitud en un rango dado y que involucraron un tipo de vehículo particular dado.");
			System.out.println("7. (Requerimiento 1C) Generar tabla ASCII de los datos");
			System.out.println("8. (Requerimiento 2C) Generar el costo de los tiempos de espera hoy en día.");
			System.out.println("9. (Requerimiento 3C) Generar el costo de los tiempos de espera según el nuevo sistema.");
            System.out.println("10. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printLista(IListaEncadenada<Comparendo> lista)
		{
			while(lista.darLongitud()>0)
			{
				System.out.println(lista.eliminarPrimero().toString());
			}	
		}
}
