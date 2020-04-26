package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.data_structures.IQueue;
import model.data_structures.IRedBlackBST;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;

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
	 * Constante de comparendos m�ximos que pueden ser revisados a diario.
	 */
	public final static int MAX_COMPARENDOS_DIARIO = 1500;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida.
	 * @post: Inicializa la lista de comparendos vac�a.
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
	}

	/**
     * M�todo que se encarga de solucionar el requerimiento 1A
     * @param m n�mero de comparendos que se quiere imprimir.
     * @return Los m comparendos con mayor prioridad en una cola de prioridad por gravedad.
     */
    public String darMComparendosConMayorGravedad(int m)
    {
        //INICIALIZACI�N DEL HEAP DE COMPARENDOS CON PRIORIDAD POR INFRACCI�N.
        Iterator<Comparendo>iterator=listaComparendos.iterator();
        Comparendo.ComparadorXTipoServicio compServi = new Comparendo.ComparadorXTipoServicio();
        IMaxHeapCP<Comparendo> heapInfraccion=new MaxHeapCP<Comparendo>(527656, compServi);
        while(iterator.hasNext())
        {
            heapInfraccion.agregar(iterator.next());
        }
        Comparendo.ComparadorXInfraccion compInfra=new Comparendo.ComparadorXInfraccion();
        int j=0;
        Comparable[]arreglo=new Comparable[m];
        while(!heapInfraccion.esVacia() && j<m)
        {
            arreglo[j]=heapInfraccion.sacarMax();
            ++j;
        }
        Ordenamientos.mergeSort(arreglo, compInfra);
        //IMPRESI�N:
        String retorno="";
        int max = m>MAX_DATOS?MAX_DATOS:m;
        for(int i=0; i<max;++i)
        {
            Comparendo actual=(Comparendo) arreglo[i];
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
		//INICIALIZACI�N DE LA TABLA DE HASH (SEPARATE CHAINING) CON LLAVES CON DIA+MES.
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo>hashDiasSemana=new HashSeparateChaining<String, Comparendo>(7);
		String llave = diaSemana+mes; //LLAVE: DIA+MES.
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
		//INICIALIZACI�N DEL �RBOL RED-BLACK.
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IRedBlackBST<String, Comparendo>redBlackFechas= new RedBlackBST<String, Comparendo>();

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			String key=parser.format(actual.darFecha())+actual.darId(); //LLAVE CONFORMADO POR DATE+OBJECTID
			redBlackFechas.put(key, actual);
		}
		//IMPRESI�N:
		String respuesta="";
		Iterator<Comparendo> iterador=redBlackFechas.valuesInRange(parser.format(fecha1)+1, parser.format(fecha2)+listaComparendos.darLongitud()); 
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
		respuesta+="Se analizaron un total de "+redBlackFechas.size()+" comparendos.\n";
		return respuesta;
	}


	/**
	 * M�todo que se encarga de solucionar el requerimiento 1B
	 * @param m n�mero de comparendos que se quiere imprimir.
	 * @return Los m comparendos con mayor prioridad en una cola de prioridad por la cercan�a a la estaci�n.
	 */ 
	public String darMComparendosMasCercaEstacion(int m)
	{
		//INICIALIZACI�N DEL HEAP DE COMPARENDOS CON PRIORIDAD POR DISTANCIA A LA ESTACI�N DE POLIC�A.
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		Comparendo.ComparadorXDistanciaAscendente compDist = new Comparendo.ComparadorXDistanciaAscendente();
		IMaxHeapCP<Comparendo> heapDistancia=new MaxHeapCP<Comparendo>(500,compDist);
		while(iterator.hasNext())
		{
			heapDistancia.agregar(iterator.next());
		}
		//IMPRESI�N:
		String mensaje = "";
		int max = m>MAX_DATOS?MAX_DATOS:m;


		for (int i=0;i<max; ++i) 
		{
			Comparendo c = heapDistancia.sacarMax();
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
		//INICIALIZACI�N DE LA TABLA DE HASH (SEPARATE CHAINING) CON LLAVES CON DETECCI�N+VEH�CULO+SERVICIO+LOCALIDAD.
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo> hashDeteVehiServiLoc=new HashSeparateChaining<String, Comparendo>(7);
		String llave = deteccion + vehiculo + servicio + localidad;

		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			hashDeteVehiServiLoc.putInSet(actual.darLlaveDeteccionVehiculoServicioLocalidad(), actual); //LLAVE: DETECCI�N+VEH�CULO+SERVICIO+LOCALIDAD.
		}
		//ORDENAMIENTO POR MERGE SEG�N FECHA:
		IListaEncadenada<Comparendo> lista = hashDeteVehiServiLoc.darListaValores(llave);
		Comparendo.ComparadorXFecha comp = new Comparendo.ComparadorXFecha();
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
		//INICIALIZACI�N DEL �RBOL RED-BLACK.
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IRedBlackBST<String, Comparendo>redBlackLatitud=new RedBlackBST<String, Comparendo>();
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			redBlackLatitud.put(actual.darLatitud()+"-"+actual.darId(), actual); //LLAVE: LATITUD+"-"+OBJECTID
		}
		//CASO EN CASO DE QUE LA LATITUD 1>LATITUD2. SE ARREGLA PROBLEMAS EN EL COMPARETO.
		if(latitud1>latitud2)
		{
			return "La latitud inicial debe ser menor que la latitud final";
		}
		if(latitud2>9)
		{
			latitud2=9;
		}
		//IMPRESI�N:
		Iterator<Comparendo> it = redBlackLatitud.valuesInRange(latitud1+"-"+1, latitud2+"-"+listaComparendos.darLongitud()); 
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
		mensaje+="Se analizaron un total de "+redBlackLatitud.size()+" comparendos.\n";

		return mensaje;
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 1C
	 * @return Una tabla ASCII de todos los comparendos seg�n un rango de fechas. 
	 */
	public String generarASCII(int pRango)
	{
		//INCIALIZACI�N DE LA TABLA DE ASCII.
		String tabla="Rango de fechas		| Comparendos durante el a�o\n"
				+"----------------------------------------------------\n";
		//CONTADORES PARA IMPRESI�N DE INFORMACI�N AL FINAL DEL M�TODO.
		int numTotalComparendos=0;
		int numTotalAstericos=0;
		int numRed=0;
		int valorSimbolo=pRango*300/5; //N�MERO DE ASTERISCOS DE IMPRESI�N.
		try 
		{
			//INICIALIZACI�N DEL �RBOL RED-BLACK.
			Calendar calendario = Calendar.getInstance();
			Iterator<Comparendo>iterator=listaComparendos.iterator();
			IRedBlackBST<String, Comparendo>redBlackAscii=new RedBlackBST<String, Comparendo>();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			while(iterator.hasNext())
			{
				Comparendo actual=iterator.next();
				String key=parser.format(actual.darFecha())+actual.darId(); //LLAVE: DATE+OBJECTID.
				redBlackAscii.put(key, actual);
				++numRed;
			}
			int repeticiones=365%(pRango)==0?365/(pRango):(365/(pRango))+1; //N�MERO DE INTERVALOS A LO LARGO DEL A�O.
			Date inicial=parser.parse("2018-01-01T00:00:00.000Z");
			for(int i=0; i<repeticiones;++i)
			{
				//FECHA FINAL DE UN INTERVALO.
				calendario.setTime(darFechaNDias(inicial,pRango-1,23,59,59));
				Date fin=calendario.get(Calendar.YEAR)==2018?darFechaNDias(inicial,pRango-1,23,59,59):parser.parse("2018-12-31T23:59:59.000Z"); 

				//N�MERO DE VALORES EN EL RANGO. SE OBTIENE A PARTIR DE UN M�TODO CREADO EN EL �RBOL RED-BLACK PARA ESTE M�TODO Y DISMINUIR COMPLEJIDAD.
				int numeroValoresEnRango=redBlackAscii.darNumValuesInRange(parser.format(inicial)+1, parser.format(fin)+listaComparendos.darLongitud());
				numTotalComparendos+=numeroValoresEnRango;
				tabla+=imprimirFormatoFecha(inicial)+"-"+imprimirFormatoFecha(fin)+"   |";
				int numAsteriscos=numeroValoresEnRango%valorSimbolo==0?numeroValoresEnRango/valorSimbolo:(numeroValoresEnRango/valorSimbolo)+1;
				numTotalAstericos+=numAsteriscos;

				//IMPRESI�N:
				for(int j=0;j<numAsteriscos;++j)
				{
					tabla+="*";
				}
				tabla+=" "+numeroValoresEnRango+"\n";
				inicial=darFechaNDias(fin,0,0,0,1); //SE UBICA LA SIGUIENTE FECHA INICIAL.
			}	
		} 
		catch (ParseException e) 
		{

		} 
		//IMPRESI�N DE DATOS:
		tabla+="\nCada * representa "+valorSimbolo+" comparendos (o fracci�n de los mismos).\n"
				+ "Se imprimieron un total de: "+numTotalAstericos+" asteriscos.\n"
				+ "El n�mero total de comparendos analizados fue de: "+numTotalComparendos+".\n"
				+ "N�mero de comparendos en �rbol RedBlack: "+numRed+".\n"
				+ "Total comparendos: "+listaComparendos.darLongitud()+".\n";
		return tabla;

	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 2C
	 * @return Una tabla ASCII con el n�mero de comparendos procesados por d�a y los que est�n en espera.
	 * Adem�s, retorna una tabla con el costo del comparendo y el tiempo de espera.
	 * @throws ParseException 
	 */
	public String costoTiempoEsperaHoyEnDia() throws ParseException
	{
		//INICIALIZACI�N DE LA TABLA DE HASH (SEPARATE CHAINING).
		String tabla="Fecha       |Comparendos procesados              ***\n"
				+"            |Comparendos que est�n en espera     ###\n"
				+"----------------------------------------------------\n";
		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo> hash=new HashSeparateChaining<String, Comparendo>(7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String llave = "";
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			llave = sdf.format(actual.darFecha());
			hash.putInSet(llave, actual); //LLAVE: FECHA.
		}

		//INICIALIZACI�N DEL QUEUE DE COMPARENDOS.
		Date fecha = sdf.parse("2018/01/01");
		IQueue<Comparendo> cola = new Queue<Comparendo>();

		//CANTIDAD ABSURDA DE CONTADORES PARA MOSTRAR INFORMACI�N AL FINAL DEL M�TODO.
		int valorSimbolo = 300;
		int costoTotal = 0;
		int min400 = 999;
		int min40 = 999;
		int min4 = 999;
		int max400 = 0;
		int max40 = 0;
		int max4 = 0;
		int suma400 = 0;
		int suma40 = 0;
		int suma4 = 0;
		int contador400 = 0;
		int contador40 = 0;
		int contador4 = 0;
		int promedio400=0;
		int promedio40=0;
		int promedio4=0;

		for (int i = 0; i <hash.darNumPares(); i++) 
		{	

			String f = sdf.format(fecha);
			Iterator<Comparendo> it = hash.getSet(f);

			//SE A�ADE COMPARENDO A LA COLA.
			int procesados = 0;
			while(it.hasNext())
			{
				cola.enqueue(it.next());
			}

			while(procesados<1500&&!cola.isEmpty())
			{
				Comparendo c = cola.dequeue();
				Date f1 = sdf.parse(sdf.format(c.darFecha()));

				//DIFERENCIA ENTRE DOS FECHAS QUE DA EL N�MERO DE RETRASO. FORO:
				//https://www.lawebdelprogramador.com/codigo/Java/3676-Diferencia-de-dias-entre-dos-fechas.html
				int diasRetrasado= (int) ((fecha.getTime()-f1.getTime())/86400000); 
				int precio = c.darPrecio();
				costoTotal+=precio*diasRetrasado;

				//CONDICIONALES SEG�N PRECIO/COSTO DE COMPARENDO.
				if(diasRetrasado!=0)
				{
					if(precio==400)
					{
						min400 = (diasRetrasado<min400)?diasRetrasado:min400;
						max400 = (diasRetrasado>max400)?diasRetrasado:max400;
						suma400+=diasRetrasado;
						++contador400;

					}
					else if(precio==40)
					{
						min40 = (diasRetrasado<min40)?diasRetrasado:min40;
						max40 = (diasRetrasado>max40)?diasRetrasado:max40;
						suma40+=diasRetrasado;
						++contador40;
					}
					else
					{
						min4= (diasRetrasado<min4)?diasRetrasado:min4;
						max4 = (diasRetrasado>max4)?diasRetrasado:max4;
						suma4+=diasRetrasado;
						++contador4;
					}
				}
				++procesados;
			}

			tabla+=f+"  |";
			int enEspera = cola.size(); //N�MERO DE COMPARENDOS EN ESPERA (COMPARENDOS QUE QUEDARON EN LA COLA).

			//IMPRESI�N.
			int asteriscos = procesados/valorSimbolo + (procesados%valorSimbolo==0?0:1);
			int numerales = enEspera/valorSimbolo + (enEspera%valorSimbolo==0?0:1);

			for (int k = 0; k < asteriscos; ++k) 
			{
				tabla+="*";
			}
			tabla+=procesados+"\n            |";
			for (int j = 0; j< numerales; ++j) 
			{
				tabla+="#";
			}
			tabla+=enEspera+"\n";
			fecha = darFechaNDias(fecha, 1,0,0,0); //SE SUMA UN D�A A LA FECHA ACTUAL.
		}
		//CONDICIONALES QUE COMPRUEBAN LOS CONTADORES.
		if(contador4==0)
		{
			promedio4=0;
			min4=0;
			max4=0;
		}
		else
			promedio4=suma4/contador4;


		if(contador40==0)
		{
			promedio40=0;
			min40=0;
			max40=0;
		}
		else
			promedio40=suma40/contador40;

		if(contador400==0)
		{
			promedio400=0;
			min400=0;
			max400=0;
		}
		else
			promedio400=suma400/contador400;

		//IMPRESI�N FINAL DE LOS DATOS:
		tabla+="\nCada * y # representa "+valorSimbolo+" Comparendos"
				+ "\nCosto Total: "+costoTotal+
				"\nN�mero de comparendos de 400$ en espera: "+contador400+
				"\nN�mero de comparendos de 40$ en espera: "+contador40+
				"\nN�mero de comparendos de 4$ en espera: "+contador4+"\n\n";

		tabla+=" Costo diario   | Tiempo m�nimo de | Tiempo promedio de | Tiempo m�ximo de \n"
				+" del comparendo |   espera (d�as)  |    espera (d�as)   |  espera (d�as)\n"
				+"     $400       |"+min400+"                 |"+promedio400+"                   |"+max400+"              \n"
				+"     $40        |"+min40+"                 |"+promedio40+"                   |"+max40+"              \n"
				+"     $4         |"+min4+"                 |"+promedio4+"                   |"+max4+"              \n";

		return tabla;
	}

	/**
	 * M�todo que se encarga de solucionar el requerimiento 3C
	 * @return Una tabla ASCII con el n�mero de comparendos procesados por d�a y los que est�n en espera.
	 * Adem�s, retorna una tabla con el costo del comparendo y el tiempo de espera. Todo esto seg�n el nuevo sistema
	 * @throws ParseException 
	 */
	public String costoTiempoEsperaNuevoSistema() throws ParseException
	{
		//INICIALIZACI�N DE LA TABLA DE HASH (SEPARATE CHAINING).
		String tabla="Fecha       |Comparendos procesados              ***\n"
				+"            |Comparendos que est�n en espera     ###\n"
				+"----------------------------------------------------\n";

		Iterator<Comparendo>iterator=listaComparendos.iterator();
		IHashTable<String, Comparendo> hash=new HashSeparateChaining<String, Comparendo>(7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String llave = "";
		while(iterator.hasNext())
		{
			Comparendo actual=iterator.next();
			llave = sdf.format(actual.darFecha());
			hash.putInSet(llave, actual); //LLAVE: FECHA.
		}

		//INICIALIZACI�N DEL QUEUE DE COMPARENDOS.
		Date fecha = sdf.parse("2018/01/01");
		IMaxHeapCP<Comparendo> heap = new MaxHeapCP<Comparendo>(5000,new Comparendo.ComparadorXPrecio());

		//CANTIDAD ABSURDA DE CONTADORES PARA MOSTRAR INFORMACI�N AL FINAL DEL M�TODO.
		int valorSimbolo = 300;
		int costoTotal = 0;
		int min400 = 999;
		int min40 = 999;
		int min4 = 999;
		int max400 = 0;
		int max40 = 0;
		int max4 = 0;
		int suma400 = 0;
		int suma40 = 0;
		int suma4 = 0;
		int contador400 = 0;
		int contador40 = 0;
		int contador4 = 0;
		int promedio400=0;
		int promedio40=0;
		int promedio4=0;

		for (int i = 0; i <hash.darNumPares(); i++) 
		{
			//SE A�ADE COMPARENDO AL HEAP..
			String f = sdf.format(fecha);
			Iterator<Comparendo> it = hash.getSet(f);

			int procesados = 0;
			while(it.hasNext())
			{
				heap.agregar(it.next());
			}

			while(procesados<1500&&!heap.esVacia())
			{
				Comparendo c = heap.sacarMax();
				Date f1 = sdf.parse(sdf.format(c.darFecha()));
				//DIFERENCIA ENTRE DOS FECHAS QUE DA EL N�MERO DE RETRASO. FORO:
				//https://www.lawebdelprogramador.com/codigo/Java/3676-Diferencia-de-dias-entre-dos-fechas.html
				int diasRetrasado= (int) ((fecha.getTime()-f1.getTime())/86400000); 
				int precio = c.darPrecio();
				costoTotal+=precio*diasRetrasado;

				//CONDICIONALES SEG�N PRECIO/COSTO DE COMPARENDO.
				if(diasRetrasado!=0)
				{
					if(precio==400)
					{
						min400 = (diasRetrasado<min400)?diasRetrasado:min400;
						max400 = (diasRetrasado>max400)?diasRetrasado:max400;
						suma400+=diasRetrasado;
						++contador400;

					}
					else if(precio==40)
					{
						min40 = (diasRetrasado<min40)?diasRetrasado:min40;
						max40 = (diasRetrasado>max40)?diasRetrasado:max40;
						suma40+=diasRetrasado;
						++contador40;
					}
					else
					{
						min4= (c.darPrecio()==4&&diasRetrasado<min4)?diasRetrasado:min4;
						max4 = (diasRetrasado>max4)?diasRetrasado:max4;
						suma4+=diasRetrasado;
						++contador4;
					}
				}
				++procesados;
			}

			tabla+=f+"  |";
			int enEspera = heap.darNumElementos(); //N�MERO DE COMPARENDOS EN ESPERA (COMPARENDOS QUE QUEDARON EN EL HEAP).

			int asteriscos = procesados/valorSimbolo + (procesados%valorSimbolo==0?0:1);
			int numerales = enEspera/valorSimbolo + (enEspera%valorSimbolo==0?0:1);

			for (int k = 0; k < asteriscos; ++k) 
			{
				tabla+="*";
			}
			tabla+=procesados+"\n            |";
			for (int j = 0; j< numerales; ++j) 
			{
				tabla+="#";
			}
			tabla+=enEspera+"\n";
			fecha = darFechaNDias(fecha, 1,0,0,0); //SE SUMA UN D�A A LA FECHA ACTUAL.
		}

		//CONDICIONALES QUE COMPRUEBAN LOS CONTADORES.
		if(contador4==0)
		{
			promedio4=0;
			min4=0;
			max4=0;
		}
		else
			promedio4=suma4/contador4;

		if(contador40==0)
		{
			promedio40=0;
			min40=0;
			max40=0;
		}
		else
			promedio4=suma40/contador40;

		if(contador400==0)
		{
			promedio400=0;
			min400=0;
			max400=0;
		}
		else
			promedio4=suma400/contador400;

		//IMPRESI�N FINAL DE LOS DATOS:
		tabla+="\nCada * y # representa "+valorSimbolo+" Comparendos"
				+ "\nCosto Total: "+costoTotal+
				"\nN�mero de comparendos de 400$ en espera: "+contador400+
				"\nN�mero de comparendos de 40$ en espera: "+contador40+
				"\nN�mero de comparendos de 4$ en espera: "+contador4+"\n\n";

		tabla+=" Costo diario   | Tiempo m�nimo de | Tiempo promedio de | Tiempo m�ximo de \n"
				+" del comparendo |   espera (d�as)  |    espera (d�as)   |  espera (d�as)\n"
				+"     $400       |"+min400+"                 |"+promedio400+"                   |"+max400+"              \n"
				+"     $40        |"+min40+"                 |"+promedio40+"                   |"+max40+"              \n"
				+"     $4         |"+min4+"                 |"+promedio4+"                   |"+max4+"              \n";

		return tabla;
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

			int precio = (descripcion.contains("SERA INMOVILIZADO")||descripcion.contains("SER� INMOVILIZADO"))?400:descripcion.contains("LICENCIA")?40:4;
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas, medioDete,precio);
			agregarFinal(comparendo);
		}
	}

	//M�TODO AUXILIARES:
	/**
	 * M�todo que cambiar el formato de fecha de YYYY/MM/DD-HH:MM:ss a yyyy-MM-dd'T'HH:mm:ss.SSS'Z' para cumplir con el requerimiento 3A. 
	 * @param pFecha Fecha que ser� modificada.
	 * @return Fecha modificada.
	 */
	public String transformarFormatoFecha(String pFecha) throws Exception
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
	 * M�todo que retorna la fecha al cabo de N d�as (tambi�n se pueden modificar los minutos y segundos). Este m�todo fue basado en el foro: https://mkyong.com/java/java-how-to-add-days-to-current-date/.
	 * @param inicial. Fecha inicial que ingresa por par�metro.
	 * @param num. Rango de fecha.
	 * @return Fecha en N d�as.
	 */
	public Date darFechaNDias(Date inicial, int pDays, int pHoras, int pMinutos, int pSegundos)
	{
		LocalDateTime localDateTime = inicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime=localDateTime.plusDays(pDays).plusHours(pHoras).plusMinutes(pMinutos).plusSeconds(pSegundos); //SUMA A LA FECHA ACTUAL LOS PAR�METROS.
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * M�todo que retorna en formato de una fecha en "YYYY-MM-DD".
	 * @param pDate. Fecha que ingresa por par�metro. 
	 * @return String con el formato de fecha "YYYY-MM-DD".
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
