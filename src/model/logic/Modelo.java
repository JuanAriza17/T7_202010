package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import model.data_structures.IMaxHeapCP;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.NodoLista;


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
	 * Arreglo con las muestras
	 */
	private Comparendo[] muestras;
	
	/**
	 * Arreglo que representa una copia de la lista.
	 */
	private Comparable[] copia;
	
	/**
	 * N�mero de datos en caso de ser necesario imprimir n�meros muy grandes.
	 */
	private final static int MAX_DATOS = 20;
	
	/**
	 * Heap de comparendos
	 */
	private IMaxHeapCP<Comparendo> heap;
	
	private IHashTable<String, Comparendo> hash;
	
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vac�a.
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
		heap=new MaxHeapCP<Comparendo>(600000);
		hash=new HashSeparateChaining<String, Comparendo>(6);
	}
	
	/**
	 * M�todo que se encarga de solucionar el requerimiento 1A
	 * @param m n�mero de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por gravedad.
	 */
	public String darMComparendosConMayorGravedad(int m)
	{
		String retorno="";
		for(int i=0; i<m;++i)
		{
			Comparendo actual=heap.sacarMax();
			retorno+=actual.toString()+"\n";
		}
		return retorno;
	}


	/**
	 * M�todo que se encarga de solucionar el requerimiento 2A
	 * @param mes N�mero del mes que se quiere buscar los comparendos
	 * @param diaSemana Inicial del d�a de la semana que se quiere buscar los comparendos
	 * @return Iterator con los comparendo que corresponden a la llave (mes-diaSemana). 
	 */
	public Iterator<Comparendo> darComparendosPorMesYDiaSemana(int mes, String diaSemana)
	{
		return hash.getSet(diaSemana);
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 3A
	 * @param fecha1 fecha inicial del rango de tiempo.
	 * @param fecha2 fecha final del rango de tiempo 
	 * @param localidad Localidad en la que se quieren buscar los comparendos
	 * @return Comparendos en el periodo de tiempo y localidad dada.
	 */
	public String darComparendosEnRangoDeFechaYLocalidad(Date fecha1, Date fecha2, String localidad)
	{
		return null;
	}


	/**
	 * M�todo que se encarga de solucionar el requerimiento 1B
	 * @param m n�mero de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por la cercan�a a la estaci�n.
	 */ 
	public IMaxHeapCP<Comparendo> darMComparendosMasCercaEstacion(int m)
	{
		return null;
	}
	
	/**
	 * M�todo que se encarga de solucionar el requerimiento 2B
	 * @param deteccion medio de deteccion de los comparendos
	 * @param servicio tipo de servicio de los comparendos
	 * @param localidad localidad de los comparendos
	 * @return Iterator con los comparendos que tienen como llave (deteccion-servicio-localidad)
	 */
	public Iterator<Comparendo> darComparendosPorDeteccionVehiculoLocalidad(String deteccion, String servicio, String localidad)
	{
		return null;
	}


	/**
	 * M�todo que se encarga de solucionar el requerimiento 3B
	 * @param latitud1 Latitud inicial del rango
	 * @param latitud2 Latitud final del rango
	 * @param vehiculo Vehiculo Tipo de vehiculos del que se quiere buscar comparendos
	 * @return Comparendos en el rango de latitud y vehiculo particular dado.
	 */
	public String darComparendosEnRangoLatitudYVehiculo(double latitud1, double latitud2, String vehiculo)
	{
		return null;
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 1C
	 * @return Una tabla ASCII de todos los comparendos seg�n un rango de fechas. 
	 */
	public String generarASCII()
	{
		return null;
	}
	
	/**
	 * M�todo que se encarga de solucionar el requerimiento 2C
	 * @return Una tabla ASCII con el n�mero de comparendos procesados por d�a y los que est�n en espera.
	 * Adem�s, retorna una tabla con el costo del comparendo y el tiempo de espera.
	 */
	public String costoTiempoEsperaHoyEnDia()
	{
		return null;
	}
	
	/**
	 * M�todo que se encarga de solucionar el requerimiento 3C
	 * @return Una tabla ASCII con el n�mero de comparendos procesados por d�a y los que est�n en espera.
	 * Adem�s, retorna una tabla con el costo del comparendo y el tiempo de espera. Todo esto seg�n el nuevo sistema
	 */
	public String costoTiempoEsperaNuevoSistema()
	{
		return null;
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
	 * M�todo que retorna la lista de comparendos.
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
	 * @param dato Comparendo que llega por par�metro.
	 */
	public void agregarFinal(Comparendo dato)
	{
		listaComparendos.agregarFinal(dato);
	}
	
	/**
	 * Requerimiento de agregar dato.
	 * @param dato Comparendo que llega por par�metro.
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
	 * M�todo que retorna el arreglo de elementos. Dicho arreglo retornado ser� comparable.
	 * @return Arreglo de elementos que es comparable.
	 */
	public void copiarComparendos()
	{		
		copia = listaComparendos.darArreglo();
	}
	
	/**
	 * M�todo que genera una muestra de datos aleatorios de la lista
	 * @param n tama�o de la muestra.
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
	 * M�todo que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException 
	{
		 File archivo = new File(ruta);
		 
		 listaComparendos = new ListaEncadenada<Comparendo>();
		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
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

			JsonObject geometria = e.getAsJsonObject().get("geometry").getAsJsonObject();
			JsonArray coords = geometria.get("coordinates").getAsJsonArray();
			double[] coordenadas = new double[2];
			for (int j = 0; j < coordenadas.length; j++) 
			{
				coordenadas[j]=coords.get(j).getAsDouble();
			}
			
			//(L, M, I, J, V, S, D)
			int diaSemana= calendario.get(Calendar.DAY_OF_WEEK)-1;
			String dia=(1==diaSemana)?"L":(2==diaSemana)?"M":(3==diaSemana)?"I":(4==diaSemana)?"J":(5==diaSemana)?"V":(6==diaSemana)?"S":"D";;
			
			
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas);
			heap.agregar(comparendo);
			hash.putInSet(dia, comparendo);
			agregarFinal(comparendo);
		 }
	}
}
