package model.logic;
import model.logic.Comparendo;

public class UbicacionGeografica implements Comparable<UbicacionGeografica> 
{
	/**
	 * Atributo de la longitud.
	 */
	private double longitud;

	/**
	 * Atributo de la latitud.
	 */
	private double latitud;

	/**
	 * M�todo constructor que inicializa la longitud y latitud con los par�metros ingresados.
	 * @param pLongitud Par�metro de la longitud.
	 * @param pLatitud Par�metro de la latitud.
	 */
	public UbicacionGeografica(double pLongitud, double pLatitud) 
	{
		longitud=pLongitud;
		latitud=pLatitud;
	}

	/**
	 * M�todo que retorna la longitud
	 * @return Longitud.
	 */
	public double darLongitud()
	{
		return longitud;
	}

	/**
	 * M�todo que retorna la longitud
	 * @return Longitud.
	 */
	public double darLatidud()
	{
		return latitud;
	}

	/**
	 * Comparador nativo de la clase.
	 */
	@Override
	public int compareTo(UbicacionGeografica o) 
	{
		return 0;
	}
}
