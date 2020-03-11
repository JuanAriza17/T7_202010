package model.logic;

import java.util.Comparator;
import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
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
	public Comparendo(int pId, Date pFecha, String pVehiculo, String pServicio, String pInfraccion, String pDescripcion, String pLocalidad, double[] pCoordenadas)
	{
		id = pId;
		fecha = pFecha;
		vehiculo = pVehiculo;
		servicio = pServicio;
		infraccion = pInfraccion;
		des_infrac = pDescripcion;
		localidad = pLocalidad;
		coordenadas = pCoordenadas;
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
	 * Da las coordenadas del com
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
	 * Compara los comparendos por latitud.
	 */
	public int compareTo(Comparendo o) {

		int compare = 0;

		if(coordenadas!=null && o.darCoordenadas()!=null)
		{
			compare = (darLatitud()>o.darLatitud())?1:(darLatitud()<o.darLatitud())?-1:0;
		}

		return compare;
	}

	@Override
	/**
	 * Da la informacion del comparendo
	 */
	public String toString()
	{
		return  id+", "+fecha+", "+infraccion+", "+vehiculo+", "+servicio+", "+localidad +", "+"LATITUD: "+Double.toString(this.darLatitud())+".";
	}

	public static class ComparadorXLatitud implements Comparator<Comparendo>{

		public int compare(Comparendo c1, Comparendo c2){
			return (c1.darLatitud()>c2.darLatitud())?1:(c1.darLatitud()<c2.darLatitud())?-1:0;
		}
	}


}
