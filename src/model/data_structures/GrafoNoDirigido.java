package model.data_structures;

import java.util.Iterator;

public class GrafoNoDirigido <K extends Comparable<K>, V extends Comparable<V>> implements IGrafoNoDirigido<K,V>
{ 
	/**
	 * N�mero de vertices.
	 */
	private int vertices;

	/**
	 * N�mero de arcos.
	 */
	private int arcos;

	/**
	 * Tabla de Hash de los v�rtices.
	 */
	private IHashTable<K,Vertice<K,V>> tabla;

	/**
	 * Lista de arcos del grafo.
	 */
	private IListaEncadenada<Arco<K,V>>listaArcos;

	/**
	 * M�todo constructor que crea un arco no dirigido de tama�o de n v�rtices.
	 * @param n N�mero de v�rtices por par�metro.
	 */
	public GrafoNoDirigido(int n)
	{
		vertices=0;
		arcos=0;
		tabla=new HashSeparateChaining<K,Vertice<K,V>>(n);
		listaArcos=new ListaEncadenada<Arco<K,V>>();
	}

	/**
	 * M�todo que retorna el n�mero de v�rtices.
	 * @return N�mero de v�rtices.
	 */
	public int V() 
	{
		return vertices;
	}

	/**
	 * M�todo que retorna la lista de arcos.
	 * @return Lista de arcos.
	 */
	public IListaEncadenada<Arco<K,V>> darListaArcos()
	{
		return listaArcos;
	}

	/**
	 * M�todo que retorna el n�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
	 * @return N�mero de arcos.
	 */
	public int E() 
	{
		return arcos;
	}

	/**
	 * Adicionar el arco No dirigido entre el v�rtice IdVertexIni y el v�rtice IdVertexFin. El arco tiene el costo cost.
	 * @param idVertexIni V�rtice inicial.
	 * @param idVertexFin V�rtice final.
	 * @param cost Costo del v�rtice.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost)
	{
		if(getInfoVertex(idVertexIni)!=null && getInfoVertex(idVertexFin)!=null)
		{
			boolean sumarArco=true;
			Vertice<K,V>verticeIni=tabla.getSet(idVertexIni).next();
			Vertice<K,V>verticeFin=tabla.getSet(idVertexFin).next();
			Iterator<Arco<K,V>> it=verticeIni.darAdyacentes(); 
			while(it.hasNext())
			{
				Arco<K,V>a=it.next();
				if(a.darDestino().darId().compareTo(verticeFin.darId())==0)
				{
					sumarArco=false;
					break;
				}
			}
			Arco<K,V>arco=new Arco<K,V>(verticeIni,verticeFin,cost);
			if(sumarArco)
			{
				listaArcos.agregarFinal(arco);
				++arcos;
			}
			else
			{
				listaArcos.buscar(arco).cambiarCosto(arco.darCosto());
			}
			verticeIni.agregarArco(arco);
			arco=new Arco<K,V>(verticeFin,verticeIni,cost);
			verticeFin.agregarArco(arco);

		}
	}

	/**
	 * M�todo que obtiene la informaci�n de un v�rtice. Si el v�rtice no existe retorna null
	 * @param idVertex Llave de identificaci�n del v�rtice.
	 * @return Informaci�n del v�rtice encontrado.
	 */
	public V getInfoVertex(K idVertex) 
	{
		Iterator<Vertice<K, V>> it = tabla.getSet(idVertex);
		return it!=null?it.next().darInfo():null;
	}

	/**
	 * M�todo que retorna un v�rtice. Si el v�rtice no existe retorna null
	 * @param idVertex Llave de identificaci�n del v�rtice.
	 * @return V�rtice encontrado.
	 */
	public Vertice<K, V> getVertex(K idVertex) 
	{
		Iterator<Vertice<K, V>> it = tabla.getSet(idVertex);
		return it!=null?it.next():null;
	}

	/**
	 * Modifica la informaci�n del v�rtice idVertex.
	 * @param idVertex  V�rtice que ser� modificado.
	 * @param infoVertex Informaci�n del v�rtice
	 */
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		Iterator<Vertice<K, V>> it = tabla.getSet(idVertex);
		if(it!=null)
		{
			tabla.getSet(idVertex).next().cambiarInformacion(infoVertex);
		}
	}

	/**
	 * M�todo obtiene el costo de un arco, si el arco no existe, retorna �1.0
	 * @param idVertexIni Llave del v�rtice inicial.
	 * @param idVertexFin Llave del v�rtice final.
	 * @return M�todo que retorna el costo.
	 */
	public double getCostArc(K idVertexIni, K idVertexFin) 
	{
		Iterator<Vertice<K,V>> itIni=tabla.getSet(idVertexIni);
		Iterator<Vertice<K,V>> itFin=tabla.getSet(idVertexFin);
		if(itIni!=null && itFin!=null)
		{
			Vertice<K,V> verticeIni=itIni.next();
			Vertice<K,V> verticeFin=itFin.next();
			Iterator<Arco<K,V>> iterator=verticeIni.darAdyacentes();
			while(iterator.hasNext())
			{
				Arco<K,V>actual=iterator.next();
				if(actual.darDestino().compareTo(verticeFin)==0)
				{
					return actual.darCosto();
				}
			}
		}
		return -1.0;
	}

	/**
	 * M�todo que Modifica el costo del arco No dirigido entre los v�rtices idVertexIni e idVertexFin
	 * @param idVertexIni Llave del v�rtice inicial.
	 * @param idVertexFin Llave del v�rtice final.
	 * @param cost Costo que ser� modificado.
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
	 * M�todo que adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex.
	 * @param idVertex Llave del v�rtice inicial.
	 * @param infoVertex Valor del v�rtice a�adido.
	 */
	public void addVertex(K idVertex, V infoVertex) 
	{
		++vertices;
		tabla.putInSet(idVertex, new Vertice<K,V>(idVertex, infoVertex));
	}

	/**
	 * Retorna los identificadores de los v�rtices adyacentes a idVertex.
	 * @param idVertex V�rtice en el que se buscar� los v�rtices adyacentes.
	 * @return Iterable de los v�rtices adyacentes.
	 */
	public Iterable<K> adj (K idVertex)
	{
		if(tabla.getSet(idVertex)!=null)
		{
			Iterator<Arco<K, V>>arcos=tabla.getSet(idVertex).next().darAdyacentes();
			IListaEncadenada<K>listaVertices=new ListaEncadenada<K>();
			while(arcos.hasNext())
			{
				Arco<K,V>actual=arcos.next();
				listaVertices.agregarFinal(actual.darDestino().darId());
			}
			return (Iterable<K>) listaVertices;
		}
		else
		{
			return null;
		}

	}

	/**
	 * Desmarca todos los v�rtices del grafo.
	 */
	public void uncheck()
	{
		if(tabla.darNumPares()!=0)
		{
			Iterator<K> iterator=tabla.keys();
			while(iterator.hasNext())
			{
				K actual=iterator.next();
				tabla.getSet(actual).next().desmarcar();
			}
		}
	}

	/**
	 * Ejecuta la b�squeda de profundidad (DFS) sobre el grafo con el v�rtice s como origen. Los v�rtices resultado de la b�squeda quedan marcados y deben tener informaci�n que pertenecen a una misma componente conectada.
	 * @param s Llave del v�rtice inicial.
	 */
	public void dfs(K s) 
	{
		if(tabla.darNumPares()!=0)
		{
			if(getVertex(s)!=null)
			{
				tabla.getSet(s).next().dfs(0,null);
			}
		}
	}

	/**
	 * Obtiene la cantidad de componentes conectados del grafo. Cada v�rtice debe quedar marcado y debe reconocer a cu�l componente conectada pertenece. En caso de que el grafo est� vac�o, retorna 0.
	 * @return N�mero de v�rtices de componentes conectados.
	 */
	public int cc()
	{
		uncheck();
		int contador=0;
		if(tabla.darNumPares()!=0)
		{
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
		else
		{
			return 0;
		}
	}

	/**
	 * M�todo que obtiene los v�rtices alcanzados a partir del v�rtice idVertex despu�s de la ejecuci�n de los m�todos dfs(K) y cc().
	 * @param idVertex Llave del v�rtice inicial.
	 * @return Iterable de los v�rtices alcanzados.
	 */
	public Iterable<K> getCC(K idVertex)
	{
		if(getVertex(idVertex)!=null)
		{
			uncheck();
			dfs(idVertex);
			Iterator<K>iterator=tabla.keys();
			IListaEncadenada<K> CC=new ListaEncadenada<K>();
			while(iterator.hasNext())
			{
				K llave=iterator.next();
				Vertice<K,V>actual=tabla.getSet(llave).next();
				if(actual.estaMarcado())
				{
					CC.agregarFinal(actual.darId());
				}
			}
			return (Iterable<K>) CC;
		}
		else
		{
			return null;
		}
	}

	/**
	 * M�todo que retorna la tabla de Hash que implementa el grafo.
	 * @return Tabla de Hash del grafo.
	 */
	public IHashTable<K,Vertice<K,V>> darTabla()
	{
		return tabla;
	}

}
