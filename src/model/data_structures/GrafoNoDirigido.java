package model.data_structures;

import java.util.Iterator;

public class GrafoNoDirigido <K extends Comparable<K>, V extends Comparable<V>> implements IGrafoNoDirigido<K,V>
{ 
	/**
	 * Número de vertices.
	 */
	private int vertices;

	/**
	 * Número de arcos.
	 */
	private int arcos;

	/**
	 * Tabla de Hash de los vértices.
	 */
	private IHashTable<K,Vertice<K,V>> tabla;

	/**
	 * Método constructor que crea un arco no dirigido de tamaño de n vértices.
	 * @param n Número de vértices por parámetro.
	 */
	public GrafoNoDirigido(int n)
	{
		vertices=0;
		arcos=0;
		tabla=new HashSeparateChaining<K,Vertice<K,V>>(n);
	}

	/**
	 * Método que retorna el número de vértices.
	 * @return Número de vértices.
	 */
	public int V() 
	{
		return vertices;
	}

	/**
	 * Método que retorna el número de arcos. Cada arco No dirigido debe contarse una única vez.
	 * @return Número de arcos.
	 */
	public int E() 
	{
		return arcos;
	}

	/**
	 * Adicionar el arco No dirigido entre el vértice IdVertexIni y el vértice IdVertexFin. El arco tiene el costo cost.
	 * @param idVertexIni Vértice inicial.
	 * @param idVertexFin Vértice final.
	 * @param cost Costo del vértice.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost)
	{
		Vertice<K,V>verticeIni=tabla.getSet(idVertexIni).next();
		Vertice<K,V>verticeFin=tabla.getSet(idVertexFin).next();
		Arco<K,V>arco=new Arco<K,V>(verticeIni,verticeFin,cost);
		verticeIni.agregarArco(arco);
		arco=new Arco<K,V>(verticeFin,verticeIni,cost);
		verticeFin.agregarArco(arco);
		++arcos;
	}

	/**
	 * Método que obtiene la información de un vértice. Si el vértice no existe retorna null
	 * @param idVertex Llave de identificación del vértice.
	 * @return Vértice encontrado.
	 */
	public V getInfoVertex(K idVertex) 
	{
		return tabla.getSet(idVertex).next().darInfo();
	}

	/**
	 * Modifica la información del vértice idVertex.
	 * @param idVertex  Vértice que será modificado.
	 * @param infoVertex 
	 */
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		tabla.getSet(idVertex).next().cambiarInformacion(infoVertex);
	}

	/**
	 * Método obtiene el costo de un arco, si el arco no existe, retorna –1.0
	 * @param idVertexIni Llave del vértice inicial.
	 * @param idVertexFin Llave del vértice final.
	 * @return Método que retorna el costo.
	 */
	public double getCostArc(K idVertexIni, K idVertexFin) 
	{
		Vertice<K,V> verticeIni=tabla.getSet(idVertexIni).next();
		Vertice<K,V> verticeFin=tabla.getSet(idVertexFin).next();
		Iterator<Arco<K,V>> iterator=verticeIni.darAdyacentes();
		while(iterator.hasNext())
		{
			Arco<K,V>actual=iterator.next();
			if(actual.darDestino().compareTo(verticeFin)==0)
			{
				return actual.darCosto();
			}
		}
		return -1.0;
	}

	/**
	 * Método que Modifica el costo del arco No dirigido entre los vértices idVertexIni e idVertexFin
	 * @param idVertexIni Llave del vértice inicial.
	 * @param idVertexFin Llave del vértice final.
	 * @param cost Costo que será modificado.
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, double cost) 
	{
		Vertice<K,V> verticeIni=tabla.getSet(idVertexIni).next();
		Vertice<K,V> verticeFin=tabla.getSet(idVertexFin).next();
		Iterator<Arco<K,V>> iterator=verticeIni.darAdyacentes();
		while(iterator.hasNext())
		{
			Arco<K,V>actual=iterator.next();
			if(actual.darDestino().compareTo(verticeFin)==0)
			{
				actual.cambiarCosto(cost);
				//tabla.getSet(idVertexIni).next().darLista().buscar(actual).cambiarCosto(cost);
				break;
			}
		}

		iterator=verticeFin.darAdyacentes();
		while(iterator.hasNext())
		{
			Arco<K,V>actual=iterator.next();
			if(actual.darDestino().compareTo(verticeFin)==0)
			{
				actual.cambiarCosto(cost);
				//tabla.getSet(idVertexIni).next().darLista().buscar(actual).cambiarCosto(cost);
				return;
			}
		}
	}

	/**
	 * Método que adiciona un vértice con un Id único. El vértice tiene la información InfoVertex.
	 * @param idVertex Llave del vértice inicial.
	 * @param infoVertex Valor del vértice añadido.
	 */
	public void addVertex(K idVertex, V infoVertex) 
	{
		++vertices;
		tabla.putInSet(idVertex, new Vertice<K,V>(idVertex, infoVertex));
	}

	/**
	 * Retorna los identificadores de los vértices adyacentes a idVertex.
	 * @param idVertex Vértice en el que se buscará los vértices adyacentes.
	 * @return Iterable de los vértices adyacentes.
	 */
	public Iterable<K> adj (K idVertex)
	{
		Iterator<Arco<K, V>>arcos=tabla.getSet(idVertex).next().darAdyacentes();
		IListaEncadenada<Arco<K,V>>listaArcos=new ListaEncadenada<Arco<K,V>>();
		while(arcos.hasNext())
		{
			Arco<K,V>actual=arcos.next();
			listaArcos.agregarFinal(actual);
		}
		return (Iterable<K>) listaArcos;
	}

	/**
	 * Desmarca todos los vértices del grafo.
	 */
	public void uncheck()
	{
		Iterator<K> iterator=tabla.keys();
		while(iterator.hasNext())
		{
			K actual=iterator.next();
			tabla.getSet(actual).next().desmarcar();
		}
	}

	/**
	 * Ejecuta la búsqueda de profundidad (DFS) sobre el grafo con el vértice s como origen. Los vértices resultado de la búsqueda quedan marcados y deben tener información que pertenecen a una misma componente conectada.
	 * @param s Llave del vértice inicial.
	 */
	public void dfs(K s) 
	{
		tabla.getSet(s).next().dfs(0,null);
	}

	/**
	 * Obtiene la cantidad de componentes conectados del grafo. Cada vértice debe quedar marcado y debe reconocer a cuál componente conectada pertenece. En caso de que el grafo esté vacío, retorna 0.
	 * @return Número de vértices de componentes conectados.
	 */
	public int cc()
	{
		uncheck();
		int contador=0;
		Iterator<K>iterator=tabla.keys();
		dfs(iterator.next());
		while(iterator.hasNext())
		{
			K llave=iterator.next();
			Vertice<K,V>actual=tabla.getSet(llave).next();
			if(!actual.estaMarcado())
			{
				++contador;
				actual.dfs(contador, null);
			}
		}
		
		return contador+1;
	}

	/**
	 * Método que obtiene los vértices alcanzados a partir del vértice idVertex después de la ejecución de los métodos dfs(K) y cc().
	 * @param idVertex Llave del vértice inicial.
	 * @return Iterable de los vértices alcanzados.
	 */
	public Iterable<K> getCC(K idVertex)
	{
		uncheck();
		dfs(idVertex);
		Iterator<K>iterator=tabla.keys();
		IListaEncadenada<Vertice<K,V>> CC=new ListaEncadenada<Vertice<K,V>>();
		while(iterator.hasNext())
		{
			K llave=iterator.next();
			Vertice<K,V>actual=tabla.getSet(llave).next();
			if(actual.estaMarcado())
			{
				CC.agregarFinal(actual);
			}
		}
		return (Iterable<K>) CC;
	}
	
}
