package model.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	
	private final static double LONGITUD_ESTACION = -74.078122;
	
	private final static double LATITUD_ESTACION = 4.647586;
	
	/**
	 * ID comparendo
	 */
	private int id;

	/**
	 * Fecha comparendo
	 */
	private Date fecha;

	/**
	 * Vehiculo comparedo
	 */
	private String vehiculo;

	/**
	 * Servicio comparendo
	 */
	private String servicio;

	/**
	 * Infracción comparendo
	 */
	private String infraccion;

	/**
	 * Descripcion infraccion
	 */
	private String des_infrac;

	/**
	 * Localidad comparendo
	 */
	private String localidad;

	/**
	 * Coordenadas comparendo
	 */
	private double[] coordenadas;
	
	/**
	 * Medio de deteccion
	 */
	private String medioDete;

	/**
	 * Metodo constructor para crear un comparendo
	 * @param pId ID comparendo
	 * @param pFecha Fecha comparendo
	 * @param pVehiculo Vehiculo comparendo
	 * @param pServicio servicio comparendo
	 * @param pInfraccion infraccion comparendo
	 * @param pDescripcion descripcion comparendo
	 * @param pLocalidad localidad comparendo
	 * @param pCoordenadas coordenadas comparendo
	 */
	public Comparendo(int pId, Date pFecha, String pVehiculo, String pServicio, String pInfraccion, String pDescripcion, String pLocalidad, double[] pCoordenadas, String pDete)
	{
		id = pId;
		fecha = pFecha;
		vehiculo = pVehiculo;
		servicio = pServicio;
		infraccion = pInfraccion;
		des_infrac = pDescripcion;
		localidad = pLocalidad;
		coordenadas = pCoordenadas;
		medioDete = pDete;
	}

	/**
	 * Da el ID del comparendo
	 * @return ID comparendo
	 */
	public int darId() {
		return id;
	}

	/**
	 * Da la fecha del comparendo
	 * @return Fecha comparendo
	 */
	public Date darFecha() {
		return fecha;
	}

	/**
	 * Da el vehiculo del comparendo
	 * @return Vehiculo comparendo
	 */
	public String darVehiculo() {
		return vehiculo;
	}

	/**
	 * Da el servicio del comparendo
	 * @return servicio comparendo
	 */
	public String darServicio() {
		return servicio;
	}

	/**
	 * Da la infraccion del comparendo
	 * @return infraccion comparendo
	 */
	public String darInfraccion() {
		return infraccion;
	}

	/**
	 * Da descripcion del comparendo
	 * @return descripcion comparendo
	 */
	public String darDescripcion() {
		return des_infrac;
	}

	/**
	 * Da la localidad del comparendo
	 * @return localidad comparendo
	 */
	public String darLocalidad() {
		return localidad;
	}

	/**
	 * Da las coordenadas del comparendo
	 * @return
	 */
	public double[] darCoordenadas() {
		return coordenadas;
	}

	/**
	 * Da la longitud de la coordenada
	 * @return longitud
	 */
	public double darLongitud()
	{
		return coordenadas[0];
	}
	
	/**
	 * Da latitud de la coordenada
	 * @return latitud
	 */
	public double darLatitud()
	{
		return coordenadas[1];
	}
	
	/**
	 * Da el medio de detección.
	 * @return medio de deteccion
	 */
	public String darMedioDete()
	{
		return medioDete;
	}
	
	public String darLlaveDiaSemana()
	{
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		
		int diaSemana= calendario.get(Calendar.DAY_OF_WEEK)-1;
		int mes = calendario.get(Calendar.MONTH)+1;
		String dia=(1==diaSemana)?"L":(2==diaSemana)?"M":(3==diaSemana)?"I":(4==diaSemana)?"J":(5==diaSemana)?"V":(6==diaSemana)?"S":"D";
		String llave = dia+mes;
		
		return llave;
	}
	
	public String darLlaveDeteccionVehiculoServicioLocalidad()
	{	
		String llave = medioDete+vehiculo+servicio+localidad;
		return llave.toLowerCase();
	}
	
	/**
	 * Método que retorna la distancia a la estación.
	 * @return  distancia a la estación de policía.
	 */
	public double darDistanciaEstacion()
	{
		return Haversine.distance(LATITUD_ESTACION, LONGITUD_ESTACION, darLatitud(), darLongitud());
	}

	/**
	 * Compara los comparendos por latitud.
	 */
	public int compareTo(Comparendo o) {

		return id - o.darId();
	}
	
	
	public static class ComparadorXDistanciaAscendente implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2)
		{
			return c2.darDistanciaEstacion()>c1.darDistanciaEstacion()?1:c2.darDistanciaEstacion()<c1.darDistanciaEstacion()?-1:0;
		}
	}
	
	public static class ComparadorXInfraccion implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2)
		{
			return c1.darInfraccion().compareTo(c2.darInfraccion());
		}
	}


	@Override
	/**
	 * Da la informacion del comparendo
	 */
	public String toString()
	{
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		String f = "";
		if(fecha!=null)
			f=parser.format(fecha);
		else
			f="00";
		
		return "OBJECTID: "+id+", FECHA Y HORA: "+f+", INFRACCION: "+infraccion+",  CLASE VEHICULO: "+vehiculo+", TIPO SERVICIO: "+servicio+", LOCALIDAD: "+localidad;
	}


}
