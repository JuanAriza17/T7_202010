package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.IListaEncadenada;
import model.data_structures.ListaEncadenada;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo { 
	/**
	 * Atributos del modelo del mundo
	 */
	private IListaEncadenada<Comparendo> listaComparendos;
		
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
	}

	public IListaEncadenada<Comparendo> darLista()
	{
		return listaComparendos;
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darLongitud()
	{
		return listaComparendos.darLongitud();
	}

	/**
	 * Agregar dato al inicio
	 * @param dato
	 */
	public void agregarInicio(Comparendo dato)
	{	
		listaComparendos.agregarInicio(dato);
	}
	
	/**
	 * Agregar dato al final
	 * @param dato
	 */
	public void agregarFinal(Comparendo dato)
	{
		listaComparendos.agregarFinal(dato);
	}
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Comparendo dato)
	{
		listaComparendos.agregar(dato);
	}
	
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Comparendo buscar(Comparendo dato)
	{
		return listaComparendos.buscar(dato);
	}
	
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminar(Comparendo dato)
	{
		return listaComparendos.eliminar(dato);
	}
	
	/**
	 * Da el primer comparendo de la lista
	 * @return primer dato
	 */
	public Comparendo darPrimerComparendo()
	{
		return listaComparendos.darPrimero();
	}
	
	/**
	 * Da el ultimo comparendo de la lista
	 * @return ultimo dato
	 */
	public Comparendo darUltimoComparendo()
	{
		return listaComparendos.darUltimo();
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
			
			agregarFinal(comparendo);
		 }
	}

}
