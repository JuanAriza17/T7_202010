package model.data_structures;

public class Camino <K extends Comparable<K>, V extends Comparable<V>> 
{
	/**
	 * Atributo del costo del camino.
	 */
	private double costo;
	
	/**
	 * Atributo de las distancia del camino.
	 */
	private int distancia;
	
	/**
	 * Vértice de origen del camino.
	 */
	private Vertice<K,V> verticeOrigen;
	
	/**
	 * Vértice de destino del camino.
	 */
	private Vertice<K,V> verticeDestino;
	
	/**
	 * Lista encadenada de arcos que maneja la secuencia.
	 */
	private IListaEncadenada<Arco<K,V>> secuencia;
	
	/**
	 * Método constructor del camino.
	 */
	public Camino()
	{
		secuencia=new ListaEncadenada<Arco<K,V>>();
	}
	
	/**
	 * Método que retorna el costo del camino.
	 * @return Costo del camino.
	 */
	public double darCosto()
	{
		return costo;
	}
	
	/**
	 * Método que retorna la distancia del camino.
	 * @return Distancia del camino.
	 */
	public int darDistancia()
	{
		return distancia;
	}
	
	/**
	 * Método que agrega un arco al final del camino.
	 * @param arco Arco que será agregado al final del camino.
	 */
	public void agregarArcoFinal(Arco<K,V> arco)
	{
		verticeDestino.agregarArco(arco);
	}
	
	/**
	 * Método que agrega el arco al inicio del camino.
	 * @param arco Arco que será agregado al inicio del camino.
	 */
	public void agregarArcoInicio(Arco<K,V> arco)
	{
		verticeOrigen.agregarArco(arco);
	}
	
	/**
	 * Método que retorna la secuencia de arcos del camino.
	 * @return Secuencia de arcos del camino.
	 */
	public IListaEncadenada<Arco<K,V>> darListaSecuencia()
	{
		return secuencia;
	}

}
