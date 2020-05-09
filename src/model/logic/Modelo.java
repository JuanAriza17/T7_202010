package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.GrafoNoDirigido;
import model.data_structures.IGrafoNoDirigido;
import model.data_structures.IListaEncadenada;
import model.data_structures.ListaEncadenada;
import model.data_structures.Vertice;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo { 

	/**
	 * Atributos del modelo del mundo.
	 */
	private IListaEncadenada<Comparendo> listaComparendos;

	/**
	 * Atributos del modelo del mundo.
	 */
	private IListaEncadenada<EstacionDePolicia> listaEstaciones;

	/**
	 * Arreglo con las muestras
	 */
	private Comparendo[] muestras;

	/**
	 * Arreglo que representa una copia de la lista.
	 */
	private Comparable[] copia;

	/**
	 * Constante del radio de la tierra.
	 */
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	/**
	 * Grafo de valores.
	 */
	private IGrafoNoDirigido<Integer, UbicacionGeografica> grafo;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vacÃ­a.
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
		listaEstaciones = new ListaEncadenada<EstacionDePolicia>();
		grafo=new GrafoNoDirigido<Integer, UbicacionGeografica>(228046);
	}

	/**
	 * Da el comparendo con el mayor ID
	 * @return Comparendo con mayor ID
	 */
	public Comparendo darMayorId()
	{
		Comparendo comp = null;
		int id = 0;

		for(Comparendo c:listaComparendos)
		{			
			if(c.darId()>id)
			{
				comp = c;
				id = c.darId();
			}
		}
		return comp;
	}

	/**
	 * Método que retorna la lista de comparendos.
	 * @return Lista de comaparendos.
	 */
	public IListaEncadenada<Comparendo> darListaComparendos()
	{
		return listaComparendos;
	}

	/**
	 * Método que retorna la lista de estaciones.
	 * @return Lista de estaciones.
	 */
	public IListaEncadenada<EstacionDePolicia> darListaEstaciones()
	{
		return listaEstaciones;
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo .
	 * @return numero de elementos presentes en el modelo.
	 */
	public int darLongitud()
	{
		return listaComparendos.darLongitud();
	}

	/**
	 * Agregar dato al final.
	 * @param dato Comparendo que llega por parÃ¡metro.
	 */
	public void agregarFinal(Comparendo dato)
	{
		listaComparendos.agregarFinal(dato);
	}

	/**
	 * Requerimiento de agregar dato.
	 * @param dato Comparendo que llega por parÃ¡metro.
	 */
	public void agregar(Comparendo dato)
	{
		listaComparendos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato.
	 * @param dato Dato a buscar.
	 * @return dato Comparendo encontrado.
	 */
	public Comparendo buscar(Comparendo dato)
	{
		return listaComparendos.buscar(dato);
	}

	/**
	 * Elimina un dato.
	 * @param dato Dato a eliminar.
	 * @return dato Comparendo eliminado.
	 */
	public Comparendo eliminar(Comparendo dato)
	{
		return listaComparendos.eliminar(dato);
	}

	/**
	 * MÃ©todo que retorna el arreglo de elementos. Dicho arreglo retornado serÃ¡ comparable.
	 * @return Arreglo de elementos que es comparable.
	 */
	public void copiarComparendos()
	{		
		copia = listaComparendos.darArreglo();
	}

	/**
	 * MÃ©todo que genera una muestra de datos aleatorios de la lista
	 * @param n tamaÃ±o de la muestra.
	 */
	public void generarMuestra(int n)
	{
		copiarComparendos();
		muestras = new Comparendo[n];
		Ordenamientos.shuffle(copia);

		int i =0;
		while(i<n)
		{
			Comparendo c = (Comparendo) copia[i];
			muestras[i]=c;
			++i;
		}

	}

	/**
	 * Da el primer comparendo de la lista
	 * @return primer dato
	 */
	public Comparendo darPrimerComparendo()
	{
		return listaComparendos.darPrimero().darElemento();
	}

	/**
	 * Da el ultimo comparendo de la lista
	 * @return ultimo dato
	 */
	public Comparendo darUltimoComparendo()
	{
		return listaComparendos.darUltimo().darElemento();
	}

	/**
	 * Método que retorna el mapa del grafo.
	 * @return Retorno del mapa del grafo.
	 */
	public IGrafoNoDirigido<Integer, UbicacionGeografica> darGrafo()
	{
		return grafo;
	}


	/**
	 * MÃ©todo que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 * @throws UnsupportedEncodingException 
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException, UnsupportedEncodingException 
	{
		File archivo = new File(ruta);

		listaComparendos = new ListaEncadenada<Comparendo>();

		JsonReader lector = new JsonReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();

		JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar calendario= Calendar.getInstance();

		for (JsonElement e: arregloComparendos) 	
		{

			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();

			int id = propiedades.get("OBJECTID").getAsInt();
			String f = propiedades.get("FECHA_HORA").getAsString();
			Date fecha = parser.parse(f);
			calendario.setTime(fecha);
			String vehiculo = propiedades.get("CLASE_VEHICULO").getAsString();
			String servicio = propiedades.get("TIPO_SERVICIO").getAsString();
			String infraccion = propiedades.get("INFRACCION").getAsString();
			String descripcion = propiedades.get("DES_INFRACCION").getAsString();
			String localidad = propiedades.get("LOCALIDAD").getAsString();
			String medioDete = propiedades.get("MEDIO_DETECCION").getAsString();

			JsonObject geometria = e.getAsJsonObject().get("geometry").getAsJsonObject();
			JsonArray coords = geometria.get("coordinates").getAsJsonArray();
			double[] coordenadas = new double[2];
			for (int j = 0; j < coordenadas.length; j++) 
			{
				coordenadas[j]=coords.get(j).getAsDouble();
			}

			int precio = (descripcion.contains("SERA INMOVILIZADO")||descripcion.contains("SERÃ� INMOVILIZADO"))?400:descripcion.contains("LICENCIA")?40:4;
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas, medioDete,precio);
			agregarFinal(comparendo);
		}
	}

	/**
	 * Método que lee el archivo de los vertices para cargarlos en el grafo.	
	 * @param ruta Ruta del archivo de vertices
	 * @throws ParseException Si no se puede parsear.
	 * @throws IOException Si hay error en la lectura del archivo.
	 */
	public void cargarVertices(String pRuta) throws ParseException, IOException 
	{
		File archivo=new File(pRuta);
		FileReader fr=new FileReader(archivo);
		BufferedReader br=new BufferedReader(fr);
		String linea=br.readLine();
		while(linea!=null)
		{
			String[] vertice=linea.split(",");
			int objectId=Integer.parseInt(vertice[0].trim());
			double longitud=Double.parseDouble(vertice[1].trim());
			double latitud=Double.parseDouble(vertice[2].trim());
			grafo.addVertex(objectId, new UbicacionGeografica(longitud,latitud));
			linea=br.readLine();
		}
		br.close();
		fr.close();
	}

	/**
	 * Método que lee el archivo de los arcos para cargarlos en el grafo.	
	 * @param ruta Ruta del archivo de arcos.
	 * @throws ParseException Si no se puede parsear.
	 * @throws IOException Si hay error en la lectura del archivo.
	 */
	public void cargarArcos(String pRuta) throws ParseException, IOException 
	{
		File archivo=new File(pRuta);
		FileReader fr=new FileReader(archivo);
		BufferedReader br=new BufferedReader(fr);
		String linea=br.readLine();
		while(linea.contains("#"))
		{
			linea=br.readLine();
		}
		while(linea!=null)
		{
			String[] arcos=linea.split(" ");
			int idVerticeInit=Integer.parseInt(arcos[0].trim());
			UbicacionGeografica geo1=grafo.getInfoVertex(idVerticeInit);
			for(int i=1; i<arcos.length;++i)
			{
				int idVerticeFin=Integer.parseInt(arcos[i].trim());
				UbicacionGeografica geo2=grafo.getInfoVertex(idVerticeFin);
				grafo.addEdge(idVerticeInit, idVerticeFin, distanceHaversine(geo1.darLatidud(), geo1.darLongitud(), geo2.darLatidud(), geo2.darLongitud()));
			}
			linea=br.readLine();
		}
		br.close();
		fr.close();
	}

	/**
	 * Método que carga del archivo JSON los datos de la estación de policía.
	 */
	public void cargarEstacionPolicia(String pRutaEstacion) throws FileNotFoundException, ParseException, UnsupportedEncodingException 
	{
		File archivo = new File(pRutaEstacion);
		listaComparendos = new ListaEncadenada<Comparendo>();
		JsonReader lector = new JsonReader(new InputStreamReader(new FileInputStream(pRutaEstacion), "UTF-8"));
		JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		JsonArray arregloEstaciones = obj.get("features").getAsJsonArray();  

		for (JsonElement e: arregloEstaciones) 	
		{
			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();
			String nombre = propiedades.get("EPONOMBRE").getAsString();
			int id = propiedades.get("OBJECTID").getAsInt();
			double latitud = propiedades.get("EPOLATITUD").getAsDouble();
			double longitud = propiedades.get("EPOLONGITU").getAsDouble();
			String direccion = propiedades.get("EPODIR_SITIO").getAsString();
			int telefono= propiedades.get("EPOTELEFON").getAsInt();

			EstacionDePolicia estacion = new EstacionDePolicia(nombre, id, latitud, longitud, direccion, telefono);
			listaEstaciones.agregarFinal(estacion);
		}

	}

	//ACLARACIÓN PRELIMINAR: El siguiente código fue realizado con base en el código de la página http://javainutil.blogspot.com/2013/03/java-escribir-un-json.html
	/**
	 * Método que imprime los valores de las estaciones cargados en la lista de estaciones.
	 * pre: Se inicializa la lista de estaciones.
	 * @param pRutaImpresion Ruta en donde se va a imprimir el archivo JSON.
	 */
	public void imprimirJSONEstaciones(String pRutaImpresion)
	{
		try 
		{
			File archivo= new File(pRutaImpresion);
			Iterator<Integer>vertices=grafo.darTabla().keys();
			FileWriter file = new FileWriter(archivo);
			JsonArray general = new JsonArray();
			JsonArray features= new JsonArray();
			
			while(vertices.hasNext())
			{
				int llave=vertices.next();
				Vertice<Integer,UbicacionGeografica>actual=grafo.darTabla().getSet(llave).next();
				
				JsonObject obj = new JsonObject();
				obj.addProperty("LATVERTICE", actual.darInfo().darLatidud());
				obj.addProperty("LONGVERTICE", actual.darInfo().darLongitud());
				file.write(obj.toString());
				
			}
			file.flush();
			file.close();		

		} 
		catch (IOException e) 
		{
			
		}

	}

	//ACLARACIÓN: Los siguientes dos métodos fueron sacados del repositorio de "Haversine" a modo de recomendación del diseño.
	//LINK: https://github.com/jasonwinn/haversine/blob/master/Haversine.java.
	/**
	 * Método que retorna la distancia entre dos coordenadas con base en el diametro de la tierra.
	 * @param startLat Latitud inicial.
	 * @param startLong Longitud inicial.
	 * @param endLat Latitud final.
	 * @param endLong Longitud final.
	 * @return Distancia entre las dos coordenadas.
	 */
	public double distanceHaversine(double startLat, double startLong,double endLat, double endLong) 
	{
		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	/**
	 * Método auxiliar del que calcula la distancia.
	 * @param val Valor.
	 * @return Retorna formula presentada.
	 */
	public double haversin(double val) 
	{
		return Math.pow(Math.sin(val / 2), 2);
	}
}
