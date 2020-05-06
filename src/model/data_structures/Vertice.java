package model.data_structures;

import java.util.Iterator;

public class Vertice <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Vertice<K,V>>
{
	/**
	 * Atributo de la llave.
	 */
	private K idVtce;
	
	/**
	 * Atributo de los valores del v�rtice.
	 */
	private V infoVtce;
	
	/**
	 * Atributo booleano que maneja si est� marcado el v�rtice.
	 */
	private boolean marca;
	
	/**
	 * Color del v�rtice.
	 */
	private int color;
	
	/**
	 * Arco a donde llega el v�rtice.
	 */
	private Arco<K,V> arcoLlegada;
	
	/**
	 * Distancia del v�rtice.
	 */
	private int distancia;
	
	/**
	 * Lista encadenada de arcos.
	 */
	private IListaEncadenada<Arco<K,V>> adyacentes;
	
	/**
	 * M�todo constructor que iguala los atributos a los par�metros dados.
	 * @param id Llave del v�rtice.
	 * @param info Valor de informaci�n del v�rtice.
	 */
	public Vertice(K id, V info)
	{
		idVtce=id;
		infoVtce=info;
	}
	
	/**
	 * M�todo que retorna la llave del v�rtice.
	 * @return Llave del v�rtice.
	 */
	public K darId()
	{
		return idVtce;
	}
	
	/**
	 * M�todo que retorna la informaci�n del v�rtice.
	 * @return
	 */
	public V darInfo()
	{
		return infoVtce;
	}
	
	/**
	 * M�todo que agrega el arco ingresado por par�metro al v�rtice.
	 */
	public void agregarArco(Arco<K,V>arco)
	{
		adyacentes.agregar(arco);
	}
	
	/**
	 * M�todo que elimina el arco con la llave ingresada por par�metro.
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
	 * M�todo que retorna un iterador de los arcos adyacentes al v�rtice actual.
	 * @return Iterador con los arcos adyacentes.
	 */
	public Iterator<Arco<K,V>> darAdyacentes()
	{
		return adyacentes.iterator();
	}
	
	/**
	 * M�todo que retorna la marca del v�rtice.
	 * @return Marca del v�rtice. True si ya pas� por el v�rtice, false en caso contrario.
	 */
	public boolean darMarca()
	{
		return marca;
	}
	
	/**
	 * M�todo que marca con un color el v�rtice de llegada de un arco determinado.
	 * @param colorP Color con el que ser� marcado el v�rtice de llegada.
	 * @param arcoLlega Arco de llegada que contiene el v�rtice de destino marcado.
	 */
	public void marcar(int colorP, Arco<K,V> arcoLlega)
	{
		color=colorP;
		arcoLlegada=arcoLlega;
		marca=true;
	}
	
	/**
	 * M�todo que desmarca el arco.
	 */
	public void desmarcar()
	{
		arcoLlegada=null;
		marca=false;
	}
	
	/**
	 * M�todo que realiza un recorrido DFS por el v�rtice.
	 * @param colorP Color del v�rtice.
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
	 * M�todo que realiza un recorrido BFS por el v�rtice.
	 * @param colorP Color del v�rtice.
	 */
	public void bfs(int colorP)
	{
		
	}
	
	/**
	 * M�todo que retorna la lista encadenada de arcos del v�rtice.
	 * @return
	 */
	public IListaEncadenada<Arco<K,V>> darLista()
	{
		return adyacentes;
	}

	/**
	 * M�todo compareTo entre v�rtices.
	 * @return
	 */
	@Override
	public int compareTo(Vertice<K, V> o) 
	{
		return 0;
	}
	
	/**
	 * M�todo que cambia el color del v�rtice.
	 * @param pColor Color que ingresa por par�metro.
	 */
	public void cambiarColor(int pColor)
	{
		color=pColor;
	}
	
	/**
	 * M�todo que cambia la informaci�n del v�rtice actual.
	 */
	public void cambiarInformacion(V pInfo)
	{
		infoVtce=pInfo;
	}
	
	/**
	 * M�todo que retorna si el v�rtice est� marcado o no.
	 * @return True en caso de que est� marcado. False en caso contrario.
	 */
	public boolean estaMarcado()
	{
		return marca;
	}
	
	/**
	 * M�todo que cambia el arco de llegada
	 */
	public void cambiarArcoLlegada(Arco<K,V>pArco)
	{
		arcoLlegada=pArco;
	}
}
