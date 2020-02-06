package model.logic;

public class Comparendo implements Comparable<Comparendo>
{
	/**
	 * ID comparendo
	 */
	private int id;
	
	/**
	 * Fecha comparendo
	 */
	private String fecha;

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
	public Comparendo(int pId, String pFecha, String pVehiculo, String pServicio, String pInfraccion, String pDescripcion, String pLocalidad, double[] pCoordenadas)
	{
		id = pId;
		fecha = pFecha;
		vehiculo = pVehiculo;
		servicio = pServicio;
		infraccion = pInfraccion;
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
	public String darFecha() {
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

	@Override
	/**
	 * Compara los comparendos por codigo
	 */
	public int compareTo(Comparendo o) {
		return id-o.darId();
	}
	
	@Override
	/**
	 * Da la informacion del comparendo
	 */
	public String toString()
	{
		return " ID del Comparendo: "+ id+"\n Fecha Comparendo: "+fecha+"\n Vehículo Comparendo: "+vehiculo+
				 "\n Servicio del Vehículo: "+servicio+"\n Código Infracción: "+infraccion+"\n Descripción Infracción: "+des_infrac+"\n Localidad: "+localidad+
				 "\n Coordenadas [Longitud,Latitud]: "+"["+coordenadas[0]+","+coordenadas[1]+"]"+"\n";
	}

	
}
