package model.logic;

public class EstacionDePolicia implements Comparable<EstacionDePolicia>
{
	/**
	 * Nombre de la estación de policía.
	 */
	private String nombre;
	
	/**
	 * OBJECTID de la estación de policía.
	 */
	private int id;
	
	/**
	 * Latitud de la estación de policía.
	 */
	private double latitud;
	
	/**
	 * Longitud de la estación de policía.
	 */
	private double longitud;
	
	/**
	 * Dirección de la estación de policía.
	 */
	private String direccion;
	
	/**
	 * Teléfono de la estación de policía.
	 */
	private int telefono;
	
	/**
	 * Método constructor de la clase que iguala los atributos por los valores que llegan por parámetro.
	 * @param pNombre Nombre de la estación de policía.
	 * @param pId OBJECTID de la estación de policía.
	 * @param pLatitud Latitud de la estación de policía.
	 * @param pLongitud Longitud de la estación de policía.
	 * @param pDireccion Dirección de la estación de policía.
	 * @param pTelefono Teléfono de la estación de policía.
	 */
	public EstacionDePolicia(String pNombre, int pId, double pLatitud, double pLongitud, String pDireccion, int pTelefono) 
	{
		nombre=pNombre;
		id=pId;
		latitud=pLatitud;
		longitud=pLongitud;
		direccion=pDireccion;
		telefono=pTelefono;
	}
	
	/**
	 * Método que retorna el nombre de la estación de policía.
	 * @return Nombre de la estación de policía.
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Método que retorna el OBJECTID de la estación de policía.
	 * @return OBJECTID de la estación de policía.
	 */
	public int darId()
	{
		return id;
	}
	
	/**
	 * Método que retorna la latitud de la estación de policía.
	 * @return La latitud de la estación de policía.
	 */
	public double darLatitud()
	{
		return latitud;
	}
	
	/**
	 * Método que retorna la longitud de la estación de policía.
	 * @return La longitud de la estación de policía.
	 */
	public double darLongitud()
	{
		return longitud;
	}
	
	/**
	 * Método que retorna la dirección de la estación de policía.
	 * @return La dirección de la estación de policía.
	 */
	public String darDireccion()
	{
		return direccion;
	}
	
	/**
	 * Método que retorna el teléfono de la estación de policía.
	 * @return El teléfono de la estación de policía.
	 */
	public int darTelefono()
	{
		return telefono;
	}
	
	/**
	 * Método compareTo nativo de la clase.
	 * @return
	 */
	@Override
	public int compareTo(EstacionDePolicia o) 
	{
		return 0;
	}
	
	/**
	 * Método que modifica el método toString()
	 * @return Estaciones en formato.
	 */
	public String toString() 
	{
		return "ID: "+id+" NOMBRE: "+nombre+" DIRECCION: "+direccion+ " TELEFONO: "+telefono+ " LONGITUD: "+longitud+ " LATITUD: "+latitud;
	}
	

}
