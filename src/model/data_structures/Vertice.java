package model.data_structures;

import java.util.Iterator;

public class Vertice <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Vertice<K,V>>
{
	/**
	 * Atributo de la llave.
	 */
	private K idVtce;
	
	/**
	 * Atributo de los valores del vértice.
	 */
	private V infoVtce;
	
	/**
	 * Atributo booleano que maneja si está marcado el vértice.
	 */
	private boolean marca;
	
	/**
	 * Color del vértice.
	 */
	private int color;
	
	/**
	 * Arco a donde llega el vértice.
	 */
	private Arco<K,V> arcoLlegada;
	
	/**
	 * Distancia del vértice.
	 */
	private int distancia;
	
	/**
	 * Lista encadenada de arcos.
	 */
	private IListaEncadenada<Arco<K,V>> adyacentes;
	
	/**
	 * Método constructor que iguala los atributos a los parámetros dados.
	 * @param id Llave del vértice.
	 * @param info Valor de información del vértice.
	 */
	public Vertice(K id, V info)
	{
		idVtce=id;
		infoVtce=info;
		adyacentes=new ListaEncadenada<Arco<K,V>>();
	}
	
	/**
	 * Método que retorna la llave del vértice.
	 * @return Llave del vértice.
	 */
	public K darId()
	{
		return idVtce;
	}
	
	/**
	 * Método que retorna la información del vértice.
	 * @return
	 */
	public V darInfo()
	{
		return infoVtce;
	}
	
	/**
	 * Método que agrega el arco ingresado por parámetro al vértice.
	 */
	public void agregarArco(Arco<K,V>arco)
	{
		if(adyacentes.buscar(arco)!=null)
		{
			adyacentes.buscar(arco).cambiarCosto(arco.darCosto());
		}
		else
		{
			adyacentes.agregarFinal(arco);
		}
	}
	
	/**
	 * Método que elimina el arco con la llave ingresada por parámetro.
	 * @param idDest Llave del arco que busca ser eliminado.
	 * @return Arco eliminado.
	 */
	public Arco<K,V> eliminarArco(K idDest)
	{
		Iterator<Arco<K,V>>arcos=darAdyacentes();
		while(arcos.hasNext())
		{
			Arco<K,V> actual=arcos.next();
			if(actual.darDestino().darId().compareTo(idDest)==0)
			{
				return adyacentes.eliminar(actual);
			}
		}
		return null;
	}
	
	/**
	 * Método que retorna un iterador de los arcos adyacentes al vértice actual.
	 * @return Iterador con los arcos adyacentes.
	 */
	public Iterator<Arco<K,V>> darAdyacentes()
	{
		return adyacentes.iterator();
	}
	
	/**
	 * Método que retorna la marca del vértice.
	 * @return Marca del vértice. True si ya pasó por el vértice, false en caso contrario.
	 */
	public boolean darMarca()
	{
		return marca;
	}
	
	/**
	 * Método que marca con un color el vértice de llegada de un arco determinado.
	 * @param colorP Color con el que será marcado el vértice de llegada.
	 * @param arcoLlega Arco de llegada que contiene el vértice de destino marcado.
	 */
	public void marcar(int colorP, Arco<K,V> arcoLlega)
	{
		color=colorP;
		arcoLlegada=arcoLlega;
		marca=true;
	}
	
	/**
	 * Método que desmarca el arco.
	 */
	public void desmarcar()
	{
		arcoLlegada=null;
		marca=false;
	}
	
	/**
	 * Método que realiza un recorrido DFS por el vértice.
	 * @param colorP Color del vértice.
	 * @param arcoLlega Arco de llegada para el recorrido.
	 */
	public void dfs(int pColor, Arco<K,V>arcoLlega)
	{
		marcar(pColor, arcoLlega);
		for (Arco<K,V> arco : adyacentes)
		{
			Vertice<K,V>v=arco.darDestino();
			if (!v.estaMarcado())
			{
				v.dfs(pColor,arco);
				
			}
		}
	}
	
	/**
	 * Método que realiza un recorrido BFS por el vértice.
	 * @param colorP Color del vértice.
	 */
	public void bfs(int colorP)
	{
		
	}
	
	/**
	 * Método que retorna la lista encadenada de arcos del vértice.
	 * @return
	 */
	public IListaEncadenada<Arco<K,V>> darLista()
	{
		return adyacentes;
	}

	/**
	 * Método compareTo entre vértices.
	 * @return
	 */
	@Override
	public int compareTo(Vertice<K, V> o) 
	{
		return o.darId().compareTo(idVtce);
	}
	
	/**
	 * Método que cambia el color del vértice.
	 * @param pColor Color que ingresa por parámetro.
	 */
	public void cambiarColor(int pColor)
	{
		color=pColor;
	}
	
	/**
	 * Método que cambia la información del vértice actual.
	 */
	public void cambiarInformacion(V pInfo)
	{
		infoVtce=pInfo;
	}
	
	/**
	 * Método que retorna si el vértice está marcado o no.
	 * @return True en caso de que esté marcado. False en caso contrario.
	 */
	public boolean estaMarcado()
	{
		return marca;
	}
	
	/**
	 * Método que cambia el arco de llegada
	 */
	public void cambiarArcoLlegada(Arco<K,V>pArco)
	{
		arcoLlegada=pArco;
	}
	
	/**
	 * Método que retorna el color del vértice.
	 * @return Método que retorna el color. 
	 */
	public int darColor()
	{
		return color;
	}
	
	/**
	 * Método que retorna el arco de llegada del vértice.
	 * @return Método que retorna el arco de llegada del vértice. 
	 */
	public Arco<K,V> darArcoLlegada()
	{
		return arcoLlegada;
	}
}
