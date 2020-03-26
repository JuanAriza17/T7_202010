package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JPopupMenu.Separator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.HashLinearProbing;
import model.data_structures.HashSeparateChaining;
import model.data_structures.IHashTable;
import model.data_structures.IListaEncadenada;
import model.data_structures.ListaEncadenada;


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
	 * Linear Probing de comparendos
	 */
	private IHashTable<String, Comparendo> linearProbing;
	
	/**
	 * Separate Chaining de coomparendos
	 */
	private IHashTable<String, Comparendo> separateChaining;

	/**
	 * Arreglo con las muestras
	 */
	private Comparendo[] muestras;
	
	private Comparable[] copia;
	
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vacía.
	 */
	public Modelo()
	{
		linearProbing = new HashLinearProbing<String, Comparendo>(5);
		separateChaining = new HashSeparateChaining<String, Comparendo>(5);
		listaComparendos = new ListaEncadenada<Comparendo>();
	}

	
	
	/**
	 * Método que retorna la cantidad de rehash realizados en el hash
	 * @return número rehash
	 */
	public int numeroRehashesLinear()
	{
		return linearProbing.darNumeroRehashes();
	}
	
	/**
	 * Método que retorna la cantidad de rehash realizados en el hash
	 * @return número rehash
	 */
	public int numeroRehashesSeparate()
	{
		return separateChaining.darNumeroRehashes();
	}
	
	/**
	 * Da el tamaño del arreglo del hash
	 * @return tamaño arreglo linear
	 */
	public int tamanoLinear()
	{
		return linearProbing.darTamano();
	}
	
	/**
	 * Da el tamaño del arreglo del hash
	 * @return tamaño arreglo separate
	 */
	public int tamanoSeparate()
	{
		return separateChaining.darTamano();
	}
	
	/**
	 * Da el número de tuplas del hash
	 * @return número de tuplas del linear
	 */
	public int numeroTuplasLinear()
	{
		return linearProbing.darNumPares();
	}
	
	/**
	 * Da el número de tuplas del hash
	 * @return número de tuplas del separate
	 */
	public int numeroTuplasSeparate()
	{
		return separateChaining.darNumPares();
	}
	
	/**
	 * Método que agrega en el hash linear
	 * @param dato comparendo a agregar
	 */
	public void agregarLinear(Comparendo dato)
	{
		linearProbing.putInSet(dato.darLlave(), dato);
	}
	
	/**
	 * Método que agrega en el hash separate
	 * @param dato comparendo a agregar
	 */
	public void agregarSeparate(Comparendo dato)
	{
		separateChaining.putInSet(dato.darLlave(), dato);
	}
	
	/**
	 * Método que resuelve el requirimiento 1
	 * @param fecha fecha de los comparendos
	 * @param vehi vehiculo de los comparendos
	 * @param infra infraccion de los comparendos
	 * @return Mensaje con los comparendos correspondientes a la llave.
	 */
	public String buscarComparendosDadaFechaLinear(String fecha, String vehi, String infra)
	{
		String key = ""+fecha+vehi+infra;
		
		if(!linearProbing.contains(key.toLowerCase()))
			return null;
		
		Iterator<Comparendo> consulta = linearProbing.getSet(key);
		String mensaje = "";
		ListaEncadenada<Comparendo> lista = new ListaEncadenada<Comparendo>();
		
		while(consulta.hasNext())
		{
			Comparendo c = consulta.next();
			lista.agregarFinal(c);
		}
		
		Comparable[] arreglo = lista.darArreglo();
		Ordenamientos.mergeSort(arreglo);
		
		for (int i = 0; i < arreglo.length; i++) 
		{
			mensaje+=arreglo[i].toString()+"\n";
		}
		mensaje+="\nSe encontraron "+arreglo.length+" comparendos.\n";
		return mensaje;
	}
	
	/**
	 * Método que resuelve el requirimiento 2
	 * @param fecha fecha de los comparendos
	 * @param vehi vehiculo de los comparendos
	 * @param infra infraccion de los comparendos
	 * @return Mensaje con los comparendos correspondientes a la llave.
	 */
	public String buscarComparendosDadaFechaSeparate(String fecha, String vehi, String infra)
	{
		String key = fecha+vehi+infra;
		
		if(!separateChaining.contains(key))
			return null;
		
		Iterator<Comparendo> consulta = linearProbing.getSet(key);
		String mensaje = "";
		ListaEncadenada<Comparendo> lista = new ListaEncadenada<Comparendo>();
		
		while(consulta.hasNext())
		{
			Comparendo c = consulta.next();
			lista.agregarFinal(c);
		}
		
		Comparable[] arreglo = lista.darArreglo();
		Ordenamientos.mergeSort(arreglo);
		
		for (int i = 0; i < arreglo.length; i++) 
		{
			mensaje+=arreglo[i].toString()+"\n";
		}
		mensaje+="\nSe encontraron "+arreglo.length+" comparendos.\n";
		return mensaje;
	}
	
	/**
	 * Método que se encarga del requerimiento 3
	 * @return tabla de desempeño
	 */
	public String pruebaDesempeño()
	{
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		String mensaje ="                                  Linear Probing          Separate Chaining";

		generarMuestra(8000);
		Comparendo[] comparendos = new Comparendo[10000];
		long[] tiempos = new long[10000];
		
		for (int i = 0; i < comparendos.length; i++)
		{
			if(i<8000)
				comparendos[i]=muestras[i];
			else
				comparendos[i]=new Comparendo(527655+1+i, new Date(2020, i%12+1, i%31+1), (i%5==0)?"motocicleta":(i%3==0)?"bicicicleta":(i%2==0)?"automóvil":"camioneta", "", "x0"+i%10, "", "",null);
			
		}
		
		Iterator<Comparendo> it = null;
		long minL = 100000000;
		long maxL = 0;
		long total=0;
		for (int i = 0; i < tiempos.length; i++) 
		{
			startTime = System.currentTimeMillis();
			it = linearProbing.getSet(comparendos[i].darLlave());
			endTime = System.currentTimeMillis();
			duration = endTime-startTime;
			tiempos[i]=duration;
			
			if(duration>maxL)
				maxL = duration;
			else if(duration<minL)
				minL=duration;
			
			total+=duration;
		}
		long promL = total/tiempos.length;
		long minS = 100000000;
		long maxS = 0;
		for (int i = 0; i < tiempos.length; i++) 
		{
			startTime = System.currentTimeMillis();
			it = separateChaining.getSet(comparendos[i].darLlave());
			endTime = System.currentTimeMillis();
			duration = endTime-startTime;
			tiempos[i]=duration;
			
			if(duration>maxS)
				maxS = duration;
			else if(duration<minS)
				minS=duration;
			
			total+=duration;
		}
		long promS = total/tiempos.length;
		
		mensaje+="\nTiempo mínimo de get(...)         "+minL+"                       "+minS+
				 "\nTiempo promedio de get(...)       "+promL+"                       "+promS+
				 "\nTiempo máximo de get(...)         "+maxL+"                       "+maxS+"\n";
		
		
		return mensaje;
	}
	
	/**
	 * Método que retorna la lista de comparendos.
	 * @return Lista de comaparendos.
	 */
	public IListaEncadenada<Comparendo> darLista()
	{
		return listaComparendos;
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
	 * @param dato Comparendo que llega por parámetro.
	 */
	public void agregarFinal(Comparendo dato)
	{
		listaComparendos.agregarFinal(dato);
	}
	
	/**
	 * Requerimiento de agregar dato.
	 * @param dato Comparendo que llega por parámetro.
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
	 * Método que retorna el arreglo de elementos. Dicho arreglo retornado será comparable.
	 * @return Arreglo de elementos que es comparable.
	 */
	public void copiarComparendos()
	{		
		copia = listaComparendos.darArreglo();
	}
	
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
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException 
	{
		 File archivo = new File(ruta);
		 
		 linearProbing = new HashLinearProbing<String, Comparendo>(5);
		 separateChaining = new HashSeparateChaining<String, Comparendo>(5);
		 listaComparendos = new ListaEncadenada<Comparendo>();
		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
		 JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		 
		 JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  
		 		 
		 SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		 		
		 
		 for (JsonElement e: arregloComparendos) 	
		 {
			
			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();
			
			int id = propiedades.get("OBJECTID").getAsInt();
			String f = propiedades.get("FECHA_HORA").getAsString();
			Date fecha = parser.parse(f);
			String vehiculo = propiedades.get("CLASE_VEHICULO").getAsString();
			String servicio = propiedades.get("TIPO_SERVICIO").getAsString();
			String infraccion = propiedades.get("INFRACCION").getAsString();
			String descripcion = propiedades.get("DES_INFRACCION").getAsString();
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
			agregarLinear(comparendo);
			agregarSeparate(comparendo);
		 }
	}
}
