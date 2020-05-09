package model.logic;

public class EstacionDePolicia implements Comparable<EstacionDePolicia>
{
	/**
	 * Nombre de la estaci�n de polic�a.
	 */
	private String nombre;
	
	/**
	 * OBJECTID de la estaci�n de polic�a.
	 */
	private int id;
	
	/**
	 * Latitud de la estaci�n de polic�a.
	 */
	private double latitud;
	
	/**
	 * Longitud de la estaci�n de polic�a.
	 */
	private double longitud;
	
	/**
	 * Direcci�n de la estaci�n de polic�a.
	 */
	private String direccion;
	
	/**
	 * Tel�fono de la estaci�n de polic�a.
	 */
	private int telefono;
	
	/**
	 * M�todo constructor de la clase que iguala los atributos por los valores que llegan por par�metro.
	 * @param pNombre Nombre de la estaci�n de polic�a.
	 * @param pId OBJECTID de la estaci�n de polic�a.
	 * @param pLatitud Latitud de la estaci�n de polic�a.
	 * @param pLongitud Longitud de la estaci�n de polic�a.
	 * @param pDireccion Direcci�n de la estaci�n de polic�a.
	 * @param pTelefono Tel�fono de la estaci�n de polic�a.
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
	 * M�todo que retorna el nombre de la estaci�n de polic�a.
	 * @return Nombre de la estaci�n de polic�a.
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * M�todo que retorna el OBJECTID de la estaci�n de polic�a.
	 * @return OBJECTID de la estaci�n de polic�a.
	 */
	public int darId()
	{
		return id;
	}
	
	/**
	 * M�todo que retorna la latitud de la estaci�n de polic�a.
	 * @return La latitud de la estaci�n de polic�a.
	 */
	public double darLatitud()
	{
		return latitud;
	}
	
	/**
	 * M�todo que retorna la longitud de la estaci�n de polic�a.
	 * @return La longitud de la estaci�n de polic�a.
	 */
	public double darLongitud()
	{
		return longitud;
	}
	
	/**
	 * M�todo que retorna la direcci�n de la estaci�n de polic�a.
	 * @return La direcci�n de la estaci�n de polic�a.
	 */
	public String darDireccion()
	{
		return direccion;
	}
	
	/**
	 * M�todo que retorna el tel�fono de la estaci�n de polic�a.
	 * @return El tel�fono de la estaci�n de polic�a.
	 */
	public int darTelefono()
	{
		return telefono;
	}
	
	/**
	 * M�todo compareTo nativo de la clase.
	 * @return
	 */
	@Override
	public int compareTo(EstacionDePolicia o) 
	{
		return 0;
	}
	
	/**
	 * M�todo que modifica el m�todo toString()
	 * @return Estaciones en formato.
	 */
	public String toString() 
	{
		return "ID: "+id+" NOMBRE: "+nombre+" DIRECCION: "+direccion+ " TELEFONO: "+telefono+ " LONGITUD: "+longitud+ " LATITUD: "+latitud;
	}
	

}
