package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.IListaEncadenada;
import model.data_structures.IMaxColaCP;
import model.data_structures.IMaxHeapCP;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;


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
	 * Heap de comparendos.
	 */
	private IMaxHeapCP<Comparendo>heap;
	
	/**
	 * Cola de prioridad de comparendos. 
	 */
	private IMaxColaCP<Comparendo>cola;
	
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
		listaComparendos = new ListaEncadenada<Comparendo>();
		heap=new MaxHeapCP<Comparendo>(1);
		cola=new MaxColaCP<Comparendo>();
		muestras = new Comparendo[5];
		copia = new Comparable[5];
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
	 * Agregar dato al inicio.
	 * @param dato Comparendo que llega por parámetro.
	 */
	public void agregarInicio(Comparendo dato)
	{	
		listaComparendos.agregarInicio(dato);
	}
	
	/**
	 * Agrega los comparendos al heap.
	 * @param dato Comparendo que llega por parámetro.
	 */
	public void agregarHeap(Comparendo dato)
	{
		heap.agregar(dato);
	}
	
	/**
	 * Método que retorna el heap de comparendos.
	 * @return Heap de comparendos.
	 */
	public IMaxHeapCP<Comparendo> darHeap()
	{
		return heap;
	}
	
	/**
	 * Método que retorna la cola de comparendos.
	 * @return Cola de comparendos
	 */
	public IMaxColaCP<Comparendo> darCola()
	{
		return cola;
	}
	
	
	
	/**
	 * Agrega los comparendos al heap.
	 * @param dato Comparendo que llega por parámetro.
	 */
	public void agregarCola(Comparendo dato)
	{
		cola.agregar(dato);
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
	 * Elimina el último dato
	 * @return dato eliminado
	 */
	public Comparendo eliminarUltimo()
	{
		return listaComparendos.eliminarUltimo();
	}
	
	/**
	 * Elimina el primer dato
	 * @return dato eliminado
	 */
	public Comparendo eliminarPrimero()
	{
		return listaComparendos.eliminarPrimero();
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
	 * Carga la muestra en la cola
	 */
	public void cargarCola()
	{
		for (int i = 0; i < muestras.length; ++i) 
		{
			cola.agregar(muestras[i]);
		}
	}
	
	/**
	 * Carga la muestra en el heap
	 */
	public void cargarHeap()
	{
		heap = new MaxHeapCP<Comparendo>(muestras.length);
		for (int i = 0; i < muestras.length; ++i)
		{
			heap.agregar(muestras[i]);
			
		}
		
	}
	
	public IListaEncadenada<Comparendo> colaComparendosMasAlNorte(int n,String vehiculos)
	{
		IListaEncadenada<Comparendo> lista= new ListaEncadenada<Comparendo>();
		String[] veh = vehiculos.split(",");
		int i =0;
		
		while(i<n)
		{
			Comparendo c = cola.sacarMax();
			if(c==null)
				break;
			
			boolean esta = false;
			
			for (int j = 0; j < veh.length&&!esta; j++)
			{
				String v= c.darVehiculo();
				
				if(v.equalsIgnoreCase(veh[j].trim()))
					esta = true;
			}
			if(esta)
			{
				lista.agregarFinal(c);
				++i;
			}

		}
		
		return lista;
	}
	
	public IListaEncadenada<Comparendo> heapComparendosMasAlNorte(int n,String vehiculos)
	{
		IListaEncadenada<Comparendo> lista= new ListaEncadenada<Comparendo>();
		String[] veh = vehiculos.split(",");

		int i =0;
		while(i<n)
		{
			Comparendo c = heap.sacarMax();
			if(c==null)
				break;
			
			boolean esta = false;
			
			for (int j = 0; j < veh.length&&!esta; j++)
			{
				String v= c.darVehiculo();
				
				if(v.equalsIgnoreCase(veh[j].trim()))
					esta = true;
			}
			if(esta)
			{
				lista.agregarFinal(c);
				++i;
			}
		}
		
		return lista;
	}
	
	
	
	
	
	public String darMayor()
	{
		double valor=0;
		String mayor="";
		for(int i=1; i<heap.darNumElementos(); ++i)
		{
			Comparendo elemento=(Comparendo) heap.darArreglo()[i];
			if(elemento.darLatitud()>valor)
			{
				valor=elemento.darLatitud();
				mayor=elemento.toString();
			}
		}
		return mayor;
	}
	
	/**
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException 
	{
		 File archivo = new File(ruta);
		 
		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
		 JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		 
		 JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  
		 
		 listaComparendos = new ListaEncadenada<Comparendo>();
		 
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
		 }
	}
}
