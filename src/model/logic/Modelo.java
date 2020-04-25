package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.HashSeparateChaining;
import model.data_structures.IHashTable;
import model.data_structures.IListaEncadenada;
import model.data_structures.IMaxHeapCP;
import model.data_structures.IRedBlackBST;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.RedBlackBST;
import model.logic.Comparendo.ComparadorXFecha;


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
	public final static int MAX_DATOS = 20;

	/**
	 * Tabla de Hash de comparendos con llave de medio de deteccion
	 */
	private IHashTable<String, Comparendo> hashDeteVehiServiLoc;

	/**
	 * �rbol RedBlack de comparendos por latitud.
	 */
	private IRedBlackBST<Double, Comparendo>redBlackLatitud;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vac�a.
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
		hashDeteVehiServiLoc = new HashSeparateChaining<String, Comparendo>(7);
		redBlackLatitud = new RedBlackBST<Double, Comparendo>();
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 1A
	 * @param m n�mero de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por gravedad.
	 */
	public String darMComparendosConMayorGravedad(int m)
	{
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IMaxHeapCP<Comparendo> heapInfraccion=new MaxHeapCP<Comparendo>(527656);
		Comparendo.ComparadorXInfraccion compInfra = new Comparendo.ComparadorXInfraccion();
		while(iterator.hasNext())
		{
			heapInfraccion.agregar(iterator.next(), compInfra);
		}
		String retorno="";
		int max = m>MAX_DATOS?MAX_DATOS:m;
		for(int i=0; i<max;++i)
		{
			Comparendo actual=heapInfraccion.sacarMax(compInfra);
			retorno+=actual.toString()+"\n";
		}

		retorno+=m>MAX_DATOS?"\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+m+"), se imprimieron solo "+Modelo.MAX_DATOS+"\n":"\nSe imprimieron los "+m+" comparendos.\n";

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
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo>hashDiasSemana=new HashSeparateChaining<String, Comparendo>(7);
		String llave = diaSemana+mes;
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			hashDiasSemana.putInSet(actual.darLlaveDiaSemana(), actual);
		}
		return hashDiasSemana.getSet(llave);
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
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IRedBlackBST<Date, Comparendo>redBlackFechas= new RedBlackBST<Date, Comparendo>();
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			redBlackFechas.put(actual.darFecha(), actual);
		}
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
		
		respuesta+=(Modelo.MAX_DATOS==i)?"\nSe imprimieron "+Modelo.MAX_DATOS+ " comparendos. El n�mero m�ximo permitido.\n":"\nSe imprimieron "+i+ " comparendos.\n";
		
		return respuesta;
	}


	/**
	 * M�todo que se encarga de solucionar el requerimiento 1B
	 * @param m n�mero de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por la cercan�a a la estaci�n.
	 */ 
	public String darMComparendosMasCercaEstacion(int m)
	{
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IMaxHeapCP<Comparendo> heapDistancia=new MaxHeapCP<Comparendo>(527656);
		Comparendo.ComparadorXDistanciaAscendente compInfra = new Comparendo.ComparadorXDistanciaAscendente();
		while(iterator.hasNext())
		{
			heapDistancia.agregar(iterator.next(), compInfra);
		}
		
		String mensaje = "";
		int max = m>MAX_DATOS?MAX_DATOS:m;

		
		for (int i=0;i<max; ++i) 
		{
			Comparendo c = heapDistancia.sacarMax(compInfra);
			mensaje+=c.toString()+" LONGITUD:"+ c.darLongitud()+" LATITUD: "+c.darLatitud()+ " DISTANCIA: "+c.darDistanciaEstacion()+"\n";
		}


		mensaje+=m>MAX_DATOS?"\nDebido a que se quiso imprimir una cantidad de comparendos mayor a la permitida ("+m+"), se imprimieron solo "+Modelo.MAX_DATOS+".\n":"\nSe imprimieron los "+m+" comparendos.\n";

		return mensaje;
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 2B
	 * @param deteccion medio de deteccion de los comparendos
	 * @param servicio tipo de servicio de los comparendos
	 * @param localidad localidad de los comparendos
	 * @return Iterator con los comparendos que tienen como llave (deteccion-servicio-localidad)
	 */
	public Comparable[] darComparendosPorDeteccionVehiculoLocalidad(String deteccion, String vehiculo, String servicio, String localidad)
	{
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo> hashDeteVehiServiLoc=new HashSeparateChaining<String, Comparendo>(7);
		String llave = deteccion + vehiculo + servicio + localidad;
		
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			hashDeteVehiServiLoc.putInSet(actual.darLlaveDeteccionVehiculoServicioLocalidad(), actual);
		}
				
		Iterator<Comparendo> it = hashDeteVehiServiLoc.getSet(llave);
		if(it==null)
			return null;

		ListaEncadenada<Comparendo> lista = new ListaEncadenada<Comparendo>();
		Comparendo.ComparadorXFecha comp = new Comparendo.ComparadorXFecha();

		while(it.hasNext())
			lista.agregarFinal(it.next());

		Comparable[] arreglo = lista.darArreglo();
		Ordenamientos.mergeSort(arreglo, comp);

		return arreglo;
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
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IRedBlackBST<Double, Comparendo>redBlackLatitud=new RedBlackBST<Double, Comparendo>();
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			redBlackLatitud.put(actual.darLatitud(), actual);
		}
		Iterator<Comparendo> it = redBlackLatitud.valuesInRange(latitud1, latitud2);
		String mensaje = "";

		int i =0;
		while(it.hasNext()&& i<MAX_DATOS)
		{
			Comparendo c = it.next();

			if(c.darVehiculo().equalsIgnoreCase(vehiculo))
			{
				mensaje += c.toString()+" LATITUD: "+c.darLatitud()+"\n";
				++i;
			}
		}
		mensaje+=(Modelo.MAX_DATOS==i)?"\nSe imprimieron "+Modelo.MAX_DATOS+ " comparendos. El n�mero m�ximo permitido.\n":"\nSe imprimieron "+i+ " comparendos.\n";

		return mensaje;
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 1C
	 * @return Una tabla ASCII de todos los comparendos seg�n un rango de fechas. 
	 */
	public String generarASCII(int pRango)
	{
		String tabla="Rango de fechas		| Comparendos durante el a�o\n"
					+"----------------------------------------------------\n";
		int numTotalComparendos=0;
		int numTotalAstericos=0;
		int numeroCompa=0;
		int num=0;
		try 
		{
			pRango=pRango-1;
			Iterator<Comparendo>iterator=listaComparendos.iterator();
			IRedBlackBST<Date, Comparendo>redBlackAscii=new RedBlackBST<Date, Comparendo>();
			while(iterator.hasNext())
			{
				Comparendo actual=iterator.next();
				Date fecha=actual.darFecha();
				redBlackAscii.put(actual.darFecha(), actual);
				++num;
			}
			numeroCompa=redBlackAscii.size();
			int repeticiones=365/(pRango+1)==0?365/(pRango+1):(365/(pRango+1))+1;
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date inicial=parser.parse("2018-01-01T00:00:00.000Z");
			for(int i=0; i<repeticiones;++i)
			{
				Date fin=darFechaNDias(inicial,pRango).getYear()+1900==2018?darFechaNDias(inicial, pRango):parser.parse("2018-12-31T23:59:59.000Z");
				Iterator<Comparendo>iteratorRango=redBlackAscii.valuesInRange(inicial, fin);
				int numeroValoresEnRango=redBlackAscii.darNumValuesInRange();
				numTotalComparendos+=numeroValoresEnRango;
				tabla+=imprimirFormatoFecha(inicial)+"-"+imprimirFormatoFecha(fin)+"   |";
				int numAsteriscos=numeroValoresEnRango/200==0?numeroValoresEnRango/200:(redBlackAscii.darNumValuesInRange()/200)+1;
				numTotalAstericos+=numAsteriscos;
				
				for(int j=0;j<numAsteriscos;++j)
				{
					tabla+="*";
				}
				tabla+="\n";
				LocalDateTime localDateTime = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				localDateTime=localDateTime.plusSeconds(1);
				inicial=Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
			}	
			
		} 
		catch (ParseException e) 
		{
			
		} 
		tabla+="\nCada * representa "+200+" comparendos (o fracci�n de los mismos).\n"
				+ "Se imprimieron un total de: "+numTotalAstericos+".\n"
				+ "El n�mero total de comparendos analizados fue de: "+numTotalComparendos+".\n"
				+ "Te�ricamente se deber�a obtener: "+numeroCompa+".\n "+num+"\n";
		return tabla;
		
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
	 * @throws UnsupportedEncodingException 
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException, UnsupportedEncodingException 
	{
		File archivo = new File(ruta);

		listaComparendos = new ListaEncadenada<Comparendo>();
		hashDeteVehiServiLoc = new HashSeparateChaining<String, Comparendo>(7);
		redBlackLatitud = new RedBlackBST<Double, Comparendo>();

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

			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas, medioDete);
			agregarFinal(comparendo);
		}
	}

	//M�TODO AUXILIARES:
	/**
	 * M�todo que cambiar el formato de fecha de YYYY/MM/DD-HH:MM:ss a yyyy-MM-dd'T'HH:mm:ss.SSS'Z' para cumplir con el requerimiento 3A. 
	 * @param pFecha Fecha que ser� modificada.
	 * @return Fecha modificada.
	 */
	public String transformarFormatoFecha(String pFecha)
	{
		String fecha="";
		String[] arreglo=pFecha.split("/");
		String temp=arreglo[2];
		fecha+=arreglo[0]+"-"+arreglo[1]+"-";
		arreglo=temp.split("-");
		fecha+=arreglo[0]+"T"+arreglo[1]+".000Z";
		return fecha;
	}
	
	/**
	 * M�todo que retorna la fecha al cabo de N d�as. Este m�todo fue basado en el foro: https://mkyong.com/java/java-how-to-add-days-to-current-date/.
	 * @param inicial. Fecha inicial que ingresa por par�metro.
	 * @param num. Rango de fecha.
	 * @return Fecha en N d�as.
	 */
	public Date darFechaNDias(Date inicial, int num)
	{
		LocalDateTime localDateTime = inicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime=localDateTime.plusDays(num).plusHours(23).plusMinutes(59).plusSeconds(59);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * M�todo que retorna en formato de una fecha en "YYYY-MM-DD"-
	 * @param pDate. Fecha que ingresa por par�metro. 
	 * @return
	 */
	public String imprimirFormatoFecha(Date pDate)
	{
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String strDate = formato.format(pDate);
        String[] arr=strDate.split(" ");
        String[] cambiarFormato=arr[0].split("-");
        return cambiarFormato[0]+"/"+cambiarFormato[1]+"/"+cambiarFormato[2];
	}
}
