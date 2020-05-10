package model.data_structures;

public class Arco <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Arco<K,V>>
{
	/**
	 * Costo del arco actual.
	 */
	private double costo;
	
	/**
	 * V�rtice de origen.
	 */
	private Vertice<K,V> verticeOrigen;
	
	/**
	 * V�rtice de destino.
	 */
	private Vertice<K,V> verticeDestino;
	
	/**
	 * M�todo constructor que inicializa los atributos con los valores que llegan por par�metro.
	 * @param orig V�rtice de origen que llega por par�metro.
	 * @param dest V�rtice de destino que llega por par�metro.
	 * @param costoP Costo del arco.
	 */
	public Arco (Vertice<K,V> orig, Vertice<K,V> dest, double costoP)
	{
		verticeOrigen=orig;
		verticeDestino=dest;
		costo=costoP;
	}
	
	/**
	 * M�todo que retorna el v�rtice de origen.
	 * @return V�rtice de origen.
	 */
	public Vertice<K,V> darOrigen()
	{
		return verticeOrigen;
	}
	
	/**
	 * M�todo que retorna el v�rtice de destino.
	 * @return V�rtice de destino.
	 */
	public Vertice<K,V> darDestino()
	{
		return verticeDestino;
	}
	
	/**
	 * M�todo que retorna el costo del arco.
	 * @return Costo del arco.
	 */
	public double darCosto()
	{
		return costo;
	}

	/**
	 * M�todo compareTo de arcos.
	 * @return 
	 */
	@Override
	public int compareTo(Arco<K, V> arg0) 
	{
		return (arg0.darOrigen().compareTo(this.darOrigen())+arg0.darDestino().compareTo(this.darDestino()));
	}
	
	/**
	 * M�todo que cambia el costo del arco actual.
	 * @param pCosto Costo nuevo del arco.
	 */
	public void cambiarCosto(double pCosto)
	{
		costo=pCosto;
	}
	
		
	
}
