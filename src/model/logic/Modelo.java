package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
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
import model.data_structures.IRedBlackBST;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.NodoLista;
import model.data_structures.RedBlackBST;
import model.logic.Comparendo.ComparadorXDistanciaAscendente;


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
	 * Número de datos en caso de ser necesario imprimir números muy grandes.
	 */
	public final static int MAX_DATOS = 20;
	
	/**
	 * Heap de comparendos por infracción.
	 */
	private IMaxHeapCP<Comparendo> heapInfraccion;
	
	/**
	 * Heap de comparendos por distancia a la estación.
	 */
	private IMaxHeapCP<Comparendo> heapDistancia;
	
	/**
	 * Tabla de Hash de comparendos con llave de dias de la semana.
	 */
	private IHashTable<String, Comparendo> hashDiasSemana;

	/**
	 * Tabla de Hash de comparendos con llave de medio de deteccion
	 */
	private IHashTable<String, Comparendo> hashDeteVehiServiLoc;
	
	/**
	 * Árbol RedBlack de comparendos por fechas.
	 */
	private IRedBlackBST<Date, Comparendo>redBlackFechas;
	
	/**
	 * Árbol RedBlack de comparendos por latitud.
	 */
	private IRedBlackBST<Double, Comparendo>redBlackLatitud;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vacía.
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
		heapInfraccion=new MaxHeapCP<Comparendo>(527656);
		hashDiasSemana=new HashSeparateChaining<String, Comparendo>(7);
		redBlackFechas= new RedBlackBST<Date, Comparendo>();
		heapDistancia = new MaxHeapCP<Comparendo>(527656);
		hashDeteVehiServiLoc = new HashSeparateChaining<String, Comparendo>(7);
		redBlackLatitud = new RedBlackBST<Double, Comparendo>();
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 1A
	 * @param m número de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por gravedad.
	 */
	public String darMComparendosConMayorGravedad(int m)
	{
		Comparendo.ComparadorXInfraccion compInfra = new Comparendo.ComparadorXInfraccion();
		String retorno="";
		int max = m>MAX_DATOS?MAX_DATOS:m;
		for(int i=0; i<max;++i)
		{
			Comparendo actual=heapInfraccion.sacarMax(compInfra);
			retorno+=actual.toString()+"\n";
		}

		retorno+=m>MAX_DATOS?"\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+Modelo.MAX_DATOS+"), se imprimieron solo "+m+"\n":"\nSe imprimieron los "+m+" comparendos.\n";

		return retorno;
	}


	/**
	 * Método que se encarga de solucionar el requerimiento 2A
	 * @param mes Número del mes que se quiere buscar los comparendos
	 * @param diaSemana Inicial del día de la semana que se quiere buscar los comparendos
	 * @return Iterator con los comparendo que corresponden a la llave (mes-diaSemana). 
	 */
	public Iterator<Comparendo> darComparendosPorMesYDiaSemana(int mes, String diaSemana)
	{
		String llave = diaSemana+mes;
		return hashDiasSemana.getSet(llave);
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 3A
	 * @param fecha1 fecha inicial del rango de tiempo.
	 * @param fecha2 fecha final del rango de tiempo 
	 * @param localidad Localidad en la que se quieren buscar los comparendos
	 * @return Comparendos en el periodo de tiempo y localidad dada.
	 */
	public String darComparendosEnRangoDeFechaYLocalidad(Date fecha1, Date fecha2, String localidad)
	{
		String respuesta="";
		Iterator<Comparendo> iterador=redBlackFechas.valuesInRange(fecha1, fecha2); 
		int i =0;
		while(iterador.hasNext()&&i<MAX_DATOS)
		{
			Comparendo actual=iterador.next();
			if(localidad.equalsIgnoreCase(actual.darLocalidad()))
			{
				respuesta+=actual.toString()+"\n";
				++i;
			}
		}
		return respuesta;
	}


	/**
	 * Método que se encarga de solucionar el requerimiento 1B
	 * @param m número de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por la cercanía a la estación.
	 */ 
	public String darMComparendosMasCercaEstacion(int m)
	{
		String mensaje = "";
		int max = m>MAX_DATOS?MAX_DATOS:m;

		Comparendo.ComparadorXDistanciaAscendente comp = new Comparendo.ComparadorXDistanciaAscendente();
		for (int i=0;i<max; ++i) 
		{
			Comparendo c = heapDistancia.sacarMax(comp);
			mensaje+=c.toString()+" LONGITUD:"+ c.darLongitud()+" LATITUD: "+c.darLatitud()+ " DISTANCIA: "+c.darDistanciaEstacion()+"\n";
		}
		

		mensaje+=m>MAX_DATOS?"\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+Modelo.MAX_DATOS+"), se imprimieron solo "+m+".\n":"\nSe imprimieron los "+m+" comparendos.\n";

		return mensaje;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 2B
	 * @param deteccion medio de deteccion de los comparendos
	 * @param servicio tipo de servicio de los comparendos
	 * @param localidad localidad de los comparendos
	 * @return Iterator con los comparendos que tienen como llave (deteccion-servicio-localidad)
	 */
	public Iterator<Comparendo> darComparendosPorDeteccionVehiculoLocalidad(String deteccion, String vehiculo, String servicio, String localidad)
	{
		String llave = deteccion + vehiculo + servicio + localidad;
		return hashDeteVehiServiLoc.getSet(llave.toLowerCase());
	}


	/**
	 * Método que se encarga de solucionar el requerimiento 3B
	 * @param latitud1 Latitud inicial del rango
	 * @param latitud2 Latitud final del rango
	 * @param vehiculo Vehiculo Tipo de vehiculos del que se quiere buscar comparendos
	 * @return Comparendos en el rango de latitud y vehiculo particular dado.
	 */
	public String darComparendosEnRangoLatitudYVehiculo(double latitud1, double latitud2, String vehiculo)
	{
		Iterator<Comparendo> it = redBlackLatitud.valuesInRange(latitud1, latitud2);
		String mensaje = "";
		
		int i =0;
		while(it.hasNext()&& i<20)
		{
			Comparendo c = it.next();
			
			if(c.darVehiculo().equalsIgnoreCase(vehiculo))
			{
				mensaje += c.toString()+" LATITUD: "+c.darLatitud()+"\n";
				++i;
			}
		}
		
		return mensaje;
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 1C
	 * @return Una tabla ASCII de todos los comparendos según un rango de fechas. 
	 */
	public String generarASCII()
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 2C
	 * @return Una tabla ASCII con el número de comparendos procesados por día y los que están en espera.
	 * Además, retorna una tabla con el costo del comparendo y el tiempo de espera.
	 */
	public String costoTiempoEsperaHoyEnDia()
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 3C
	 * @return Una tabla ASCII con el número de comparendos procesados por día y los que están en espera.
	 * Además, retorna una tabla con el costo del comparendo y el tiempo de espera. Todo esto según el nuevo sistema
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
	
	/**
	 * Método que genera una muestra de datos aleatorios de la lista
	 * @param n tamaño de la muestra.
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
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException 
	{
		 File archivo = new File(ruta);
		 
		 listaComparendos = new ListaEncadenada<Comparendo>();
		 heapInfraccion=new MaxHeapCP<Comparendo>(527656);
	 	 hashDiasSemana=new HashSeparateChaining<String, Comparendo>(7);
	  	 redBlackFechas= new RedBlackBST<Date, Comparendo>();
		 heapDistancia = new MaxHeapCP<Comparendo>(527656);
	 	 hashDeteVehiServiLoc = new HashSeparateChaining<String, Comparendo>(7);
	 	 redBlackLatitud = new RedBlackBST<Double, Comparendo>();
	 	 		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
		 JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		 
		 JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  
		 		 
		 SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		 //SimpleDateFormat parserRedBlack= new SimpleDateFormat("YYYY/MM/DD-HH:mm:ss");
		 Calendar calendario= Calendar.getInstance();
		 
		 Comparendo.ComparadorXInfraccion compInfra = new Comparendo.ComparadorXInfraccion();
		 Comparendo.ComparadorXDistanciaAscendente compLat = new Comparendo.ComparadorXDistanciaAscendente();

		 for (JsonElement e: arregloComparendos) 	
		 {
			
			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();
			
			int id = propiedades.get("OBJECTID").getAsInt();
			String f = propiedades.get("FECHA_HORA").getAsString();
			//String transformado=transformarFormatoFecha(f);
			Date fecha = parser.parse(f);
			//Date fechaRedBlack=parserRedBlack.parse(transformado);
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
			
			//(L, M, I, J, V, S, D)
			
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas, medioDete);
			//Comparendo comparendoRedBlack=new Comparendo(id, fechaRedBlack, vehiculo, servicio, infraccion, descripcion, localidad, coordenadas);
			heapInfraccion.agregar(comparendo, compInfra);
			heapDistancia.agregar(comparendo, compLat);
			hashDiasSemana.putInSet(comparendo.darLlaveDiaSemana(), comparendo);
			hashDeteVehiServiLoc.putInSet(comparendo.darLlaveDeteccionVehiculoServicioLocalidad(), comparendo);
			redBlackFechas.put(fecha, comparendo);
			redBlackLatitud.put(comparendo.darLatitud(), comparendo);
			agregarFinal(comparendo);
		}
	}
	
	/**
	 * Método que cambiar el formato de fecha de yyyy-MM-dd'T'HH:mm:ss.SSS'Z' a YYYY/MM/DD-HH:MM:ss para cumplir con el requerimiento 3A. 
	 * @param pFecha Fecha que será modificada.
	 * @return Fecha modificada.
	 */
	public String transformarFormatoFecha(String pFecha)
	{
		String fecha="";
		String[] arreglo=pFecha.split("-");
		String temp=arreglo[2];
		fecha+=arreglo[0]+"/"+arreglo[1]+"/";
		arreglo=temp.split("T");
		fecha+=arreglo[0];
		temp=arreglo[1];
		temp=temp.replaceAll(".000Z", "");
		fecha+="-"+temp;
		return fecha;
	}
}
