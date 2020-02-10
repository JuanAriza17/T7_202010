package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.IListaEncadenada;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ListaEncadenada;
import model.data_structures.Queue;
import model.data_structures.Stack;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo { 
	/**
	 * Atributos del modelo del mundo
	 */
	private IStack<Comparendo> stackComparendos;
	
	private IQueue<Comparendo> queueComparendos;
		
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		stackComparendos = new Stack<Comparendo>();
		queueComparendos = new Queue<Comparendo>();
	}

	public IStack<Comparendo> darStack()
	{
		return stackComparendos;
	}
	
	public IQueue<Comparendo> darQueue()
	{
		return queueComparendos;
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoPila()
	{
		return stackComparendos.size();
	}
	
	public int darTamanoCola()
	{
		return queueComparendos.size();
	}
	
	/**
	 * Requerimiento de agregar datos a la pila
	 * @param dato
	 */
	public void agregarStack(Comparendo dato)
	{
		stackComparendos.push(dato);
	}
	
	/**
	 * Requerimiento de agregar datos a la cola
	 * @param dato
	 */
	public void agregarQueue(Comparendo dato)
	{
		queueComparendos.enqueue(dato);
	}
	
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminarStack()
	{
		return stackComparendos.pop();
	}
	
	public Comparendo eliminarQueue()
	{
		return queueComparendos.dequeue();
	}
	
	/**
	 * Da el primer comparendo de la lista
	 * @return primer dato
	 */
	public Comparendo darPrimerComparendo()
	{
		return queueComparendos.pick();
	}
	
	/**
	 * Da el ultimo comparendo de la lista
	 * @return ultimo dato
	 */
	public Comparendo darUltimoComparendo()
	{
		return stackComparendos.pick();
	}
	
	public IQueue<Comparendo> darColaInfracciones()
	{
		
		if(queueComparendos.isEmpty())
		{
			return null;
		}
		IQueue<Comparendo> cola = null;
		IQueue<Comparendo> temporal = new Queue<Comparendo>();
		Comparendo comparendo = queueComparendos.pick();
		String inf = comparendo.darInfraccion();
		int tamano = 0;
		
		while(!queueComparendos.isEmpty())
		{
			Comparendo c = queueComparendos.dequeue();	
			if(c!=null)
			{
				if(c.darInfraccion().equals(inf))
				{
					temporal.enqueue(c);
				}
				else
				{
					inf = c.darInfraccion();
					temporal = new Queue<Comparendo>();
					temporal.enqueue(c);
				}
			}	
			if(temporal.size()>tamano)
			{
				cola = temporal;
				tamano = temporal.size();
			}
		}
		return cola;
	}
	
	public IStack<Comparendo> darPilaInfracciones()
	{
		if(stackComparendos.isEmpty())
		{
			return null;
		}
		IStack<Comparendo> pila = null;
		IStack<Comparendo> temporal = new Stack<Comparendo>();
		Comparendo comparendo = stackComparendos.pick();
		String inf = comparendo.darInfraccion();
		int tamano = 0;
		
		while(!stackComparendos.isEmpty())
		{
			Comparendo c = stackComparendos.pop();			
			if(c!=null)
			{
				if(c.darInfraccion().equals(inf))
				{
					temporal.push(c);
				}
				else
				{
					inf = c.darInfraccion();
					temporal = new Stack<Comparendo>();
					temporal.push(c);
				}
			}
			
			if(temporal.size()>tamano)
			{
				pila = temporal;
				tamano = temporal.size();
			}
		}
		return pila;
	}

	/**
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException
	{
		 File archivo = new File(ruta);
		 
		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
		 JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		 
		 JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  
		 
		 for (JsonElement e: arregloComparendos) 	
		 {
			
			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();
			
			int id = propiedades.get("OBJECTID").getAsInt();
			String fecha = propiedades.get("FECHA_HORA").getAsString();
			String vehiculo = propiedades.get("CLASE_VEHI").getAsString();
			String servicio = propiedades.get("TIPO_SERVI").getAsString();
			String infraccion = propiedades.get("INFRACCION").getAsString();
			String descripcion = propiedades.get("DES_INFRAC").getAsString();
			String localidad = propiedades.get("LOCALIDAD").getAsString();

			JsonObject geometria = e.getAsJsonObject().get("geometry").getAsJsonObject();
			JsonArray coords = geometria.get("coordinates").getAsJsonArray();
			double[] coordenadas = new double[2];
			
			for (int j = 0; j < coordenadas.length; j++) 
			{
				coordenadas[j]=coords.get(j).getAsDouble();
			}
						
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas);
			
			agregarQueue(comparendo);
			agregarStack(comparendo);
		 }
	}

}
