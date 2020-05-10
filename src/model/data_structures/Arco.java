package model.data_structures;

public class Arco <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Arco<K,V>>
{
	/**
	 * Costo del arco actual.
	 */
	private double costo;
	
	/**
	 * Vértice de origen.
	 */
	private Vertice<K,V> verticeOrigen;
	
	/**
	 * Vértice de destino.
	 */
	private Vertice<K,V> verticeDestino;
	
	/**
	 * Método constructor que inicializa los atributos con los valores que llegan por parámetro.
	 * @param orig Vértice de origen que llega por parámetro.
	 * @param dest Vértice de destino que llega por parámetro.
	 * @param costoP Costo del arco.
	 */
	public Arco (Vertice<K,V> orig, Vertice<K,V> dest, double costoP)
	{
		verticeOrigen=orig;
		verticeDestino=dest;
		costo=costoP;
	}
	
	/**
	 * Método que retorna el vértice de origen.
	 * @return Vértice de origen.
	 */
	public Vertice<K,V> darOrigen()
	{
		return verticeOrigen;
	}
	
	/**
	 * Método que retorna el vértice de destino.
	 * @return Vértice de destino.
	 */
	public Vertice<K,V> darDestino()
	{
		return verticeDestino;
	}
	
	/**
	 * Método que retorna el costo del arco.
	 * @return Costo del arco.
	 */
	public double darCosto()
	{
		return costo;
	}

	/**
	 * Método compareTo de arcos.
	 * @return 
	 */
	@Override
	public int compareTo(Arco<K, V> arg0) 
	{
		return (arg0.darOrigen().compareTo(this.darOrigen())+arg0.darDestino().compareTo(this.darDestino()));
	}
	
	/**
	 * Método que cambia el costo del arco actual.
	 * @param pCosto Costo nuevo del arco.
	 */
	public void cambiarCosto(double pCosto)
	{
		costo=pCosto;
	}
	
		
	
}
