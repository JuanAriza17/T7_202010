package model.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	/**
	 * Constante de longitud de la coordenada de la estaci�n de polic�a.
	 */
	private final static double LONGITUD_ESTACION = -74.078122;
	
	/**
	 * Constante de latitud de la coordenada de la estaci�n de polic�a.
	 */
	private final static double LATITUD_ESTACION = 4.647586;
	
	/**
	 * Constante del radio de la tierra.
	 */
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
	
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
	 * Infracci�n comparendo
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
	 * Da el medio de detecci�n.
	 * @return medio de deteccion
	 */
	public String darMedioDete()
	{
		return medioDete;
	}
	
	/**
	 * M�todo que retorna una llave conformada por el d�a de la semana y el mes.
	 * @return Llave com d�a de la semana y mes.
	 */
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
	
	/**
	 * M�todo que retorna una llave con base en el medio detecci�n, veh�culo, servicio y localidad.
	 * @return Llave con medio detecci�n, veh�culo, servicio y localidad.
	 */
	public String darLlaveDeteccionVehiculoServicioLocalidad()
	{	
		String llave = medioDete+vehiculo+servicio+localidad;
		return llave.toLowerCase();
	}
	
	/**
	 * M�todo que retorna la distancia a la estaci�n.
	 * @return  distancia a la estaci�n de polic�a.
	 */
	public double darDistanciaEstacion()
	{
		return distanceHaversine(LATITUD_ESTACION, LONGITUD_ESTACION, darLatitud(), darLongitud());
	}

	/**
	 * Compara los comparendos por latitud.
	 */
	public int compareTo(Comparendo o) {

		return id - o.darId();
	}
	
	/**
	 * Clase que implementa comparador de comparendo para la distancia.
	 * @author ASUS Juan Ariza y Sergio Zona.
	 *
	 */
	public static class ComparadorXDistanciaAscendente implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2)
		{
			return c2.darDistanciaEstacion()>c1.darDistanciaEstacion()?1:c2.darDistanciaEstacion()<c1.darDistanciaEstacion()?-1:0;
		}
	}
	
	/**
	 * Clase que implementa comparador de comparendo para la infracci�n.
	 * @author ASUS Juan Ariza y Sergio Zona.
	 *
	 */
	public static class ComparadorXInfraccion implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2)
		{
			return c1.darInfraccion().compareTo(c2.darInfraccion());
		}
	}
	
	/**
	 * Clase que implementa comparador de comparendo para la fecha.
	 * @author ASUS Juan Ariza y Sergio Zona.
	 *
	 */
	public static class ComparadorXFecha implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2)
		{
			return c1.darFecha().compareTo(c2.darFecha());
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
	
	
	//ACLARACI�N: Los siguientes dos m�todos fueron sacados del repositorio de "Haversine" a modo de recomendaci�n del dise�o.
	//LINK: https://github.com/jasonwinn/haversine/blob/master/Haversine.java.
	/**
	 * M�todo que retorna la distancia entre dos coordenadas con base en el diametro de la tierra.
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
     * M�todo auxiliar del que calcula la distancia.
     * @param val Valor.
     * @return Retorna formula presentada.
     */
    public double haversin(double val) 
    {
        return Math.pow(Math.sin(val / 2), 2);
    }
	
}
