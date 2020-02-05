package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	public String infoComparendoId(int id)
	{
		String mensaje = "";
		
		Comparendo c = buscar(new Comparendo(id,"","","","","","",null));
		if(c!=null)
		{
			String f = c.darFecha();
			String v = c.darVehiculo();
			String s = c.darServicio();
			String i = c.darInfraccion();
			String d = c.darDescripcion();
			String l = c.darLocalidad();
			
			double[] coords = c.darCoordenadas();
			double x = coords[0];
			double y = coords[1];
			double z = coords[2];
			
			mensaje ="ID del Comparendo: "+ id+"\n Fecha Comparendo: "+f+"\n Vehículo Comparendo: "+v+
					 "\n Servicio del Vehículo: "+s+"\n Código Infracción: "+i+"\n Localidad: "+l+
					 "\n Coordenadas: "+"["+x+","+y+","+z+"]"+"\n";

		}
		else
		{
			mensaje = "El Comparendo con el ID "+id+" NO se encuentra en la lista";
		}
		
		return mensaje;
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
	
	public Comparendo darPrimerComparendo()
	{
		return listaComparendos.darPrimero();
	}
	
	public Comparendo darUltimoComparendo()
	{
		return listaComparendos.darUltimo();
	}

	public void cargarComparendos(String ruta) throws FileNotFoundException
	{
		 File archivo = new File(ruta);
		 
		 JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(archivo)));
		 JsonObject objGson = JsonParser.parseReader(reader).getAsJsonObject();
		 
		 JsonArray arregloComparendos = objGson.get("features").getAsJsonArray();  
		 
		 for (JsonElement e:arregloComparendos) 
		 {
			JsonObject objeto = e.getAsJsonObject();
			
			JsonObject propiedades = objeto.get("properties").getAsJsonObject();
			
			int id = propiedades.get("OBJECTID").getAsInt();
			String fecha = propiedades.get("FECHA_HORA").getAsString();
			String vehiculo = propiedades.get("CLASE_VEHI").getAsString();
			String servicio = propiedades.get("TIPO_SERVI").getAsString();
			String infraccion = propiedades.get("INFRACCION").getAsString();
			String descripcion = propiedades.get("DES_INFRAC").getAsString();
			String localidad = propiedades.get("LOCALIDAD").getAsString();

			objeto = objeto.get("geometry").getAsJsonObject();
			JsonArray coords = objeto.get("coordinates").getAsJsonArray();
			double[] coordenadas = new double[3];
			
			for (int i = 0; i < coordenadas.length; i++) 
			{
				coordenadas[i]=coords.get(i).getAsDouble();
			}
						
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas);
			
			listaComparendos.agregar(comparendo);
		 }
	}

}
