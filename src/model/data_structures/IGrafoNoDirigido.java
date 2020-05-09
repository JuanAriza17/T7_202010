package model.data_structures;

public interface IGrafoNoDirigido <K extends Comparable<K>, V extends Comparable<V>> 
{
	/**
	 * M�todo que retorna el n�mero de v�rtices.
	 * @return N�mero de v�rtices.
	 */
	public int V();
	
	/**
	 * M�todo que retorna el n�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
	 * @return N�mero de arcos.
	 */
	public int E();
	
	/**
	 * Adicionar el arco No dirigido entre el v�rtice IdVertexIni y el v�rtice IdVertexFin. El arco tiene el costo cost.
	 * @param idVertexIni V�rtice inicial.
	 * @param idVertexFin V�rtice final.
	 * @param cost Costo del v�rtice.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * M�todo que obtiene la informaci�n de un v�rtice. Si el v�rtice no existe retorna null
	 * @param idVertex Llave de identificaci�n del v�rtice.
	 * @return V�rtice encontrado.
	 */
	public V getInfoVertex(K idVertex);
	
	/**
	 * Modifica la informaci�n del v�rtice idVertex.
	 * @param idVertex  V�rtice que ser� modificado.
	 * @param infoVertex 
	 */
	public void setInfoVertex(K idVertex, V infoVertex);
	
	/**
	 * M�todo obtiene el costo de un arco, si el arco no existe, retorna �1.0
	 * @param idVertexIni Llave del v�rtice inicial.
	 * @param idVertexFin Llave del v�rtice final.
	 * @return M�todo que retorna el costo.
	 */
	public double getCostArc(K idVertexIni, K idVertexFin);
	
	/**
	 * M�todo que Modifica el costo del arco No dirigido entre los v�rtices idVertexIni e idVertexFin
	 * @param idVertexIni Llave del v�rtice inicial.
	 * @param idVertexFin Llave del v�rtice final.
	 * @param cost Costo que ser� modificado.
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * M�todo que adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex.
	 * @param idVertex Llave del v�rtice inicial.
	 * @param infoVertex Valor del v�rtice a�adido.
	 */
	public void addVertex(K idVertex, V infoVertex);
	
	/**
	 * Retorna los identificadores de los v�rtices adyacentes a idVertex.
	 * @param idVertex V�rtice en el que se buscar� los v�rtices adyacentes.
	 * @return Iterable de los v�rtices adyacentes.
	 */
	public Iterable <K> adj (K idVertex);
	
	/**
	 * Desmarca todos los v�rtices del grafo.
	 */
	public void uncheck();
	
	/**
	 * Ejecuta la b�squeda de profundidad (DFS) sobre el grafo con el v�rtice s como origen. Los v�rtices resultado de la b�squeda quedan marcados y deben tener informaci�n que pertenecen a una misma componente conectada.
	 * @param s Llave del v�rtice inicial.
	 */
	public void dfs(K s);
	
	/**
	 * Obtiene la cantidad de componentes conectados del grafo. Cada v�rtice debe quedar marcado y debe reconocer a cu�l componente conectada pertenece. En caso de que el grafo est� vac�o, retorna 0.
	 * @return N�mero de v�rtices de componentes conectados.
	 */
	public int cc();
	
	/**
	 * M�todo que obtiene los v�rtices alcanzados a partir del v�rtice idVertex despu�s de la ejecuci�n de los m�todos dfs(K) y cc().
	 * @param idVertex Llave del v�rtice inicial.
	 * @return Iterable de los v�rtices alcanzados.
	 */
	public Iterable<K> getCC(K idVertex);
	
	/**
	 * M�todo que retorna la tabla de Hash que implementa el grafo.
	 * @return Tabla de Hash del grafo.
	 */
	public IHashTable<K,Vertice<K,V>> darTabla();
	
	/**
	 * M�todo que retorna un v�rtice. Si el v�rtice no existe retorna null
	 * @param idVertex Llave de identificaci�n del v�rtice.
	 * @return V�rtice encontrado.
	 */
	public Vertice<K, V> getVertex(K idVertex);

	
	


	
}
