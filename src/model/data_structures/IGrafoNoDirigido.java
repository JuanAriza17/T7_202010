package model.data_structures;

public interface IGrafoNoDirigido <K extends Comparable<K>, V extends Comparable<V>> 
{
	/**
	 * Método que retorna el número de vértices.
	 * @return Número de vértices.
	 */
	public int V();
	
	/**
	 * Método que retorna el número de arcos. Cada arco No dirigido debe contarse una única vez.
	 * @return Número de arcos.
	 */
	public int E();
	
	/**
	 * Adicionar el arco No dirigido entre el vértice IdVertexIni y el vértice IdVertexFin. El arco tiene el costo cost.
	 * @param idVertexIni Vértice inicial.
	 * @param idVertexFin Vértice final.
	 * @param cost Costo del vértice.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * Método que obtiene la información de un vértice. Si el vértice no existe retorna null
	 * @param idVertex Llave de identificación del vértice.
	 * @return Vértice encontrado.
	 */
	public V getInfoVertex(K idVertex);
	
	/**
	 * Modifica la información del vértice idVertex.
	 * @param idVertex  Vértice que será modificado.
	 * @param infoVertex 
	 */
	public void setInfoVertex(K idVertex, V infoVertex);
	
	/**
	 * Método obtiene el costo de un arco, si el arco no existe, retorna –1.0
	 * @param idVertexIni Llave del vértice inicial.
	 * @param idVertexFin Llave del vértice final.
	 * @return Método que retorna el costo.
	 */
	public double getCostArc(K idVertexIni, K idVertexFin);
	
	/**
	 * Método que Modifica el costo del arco No dirigido entre los vértices idVertexIni e idVertexFin
	 * @param idVertexIni Llave del vértice inicial.
	 * @param idVertexFin Llave del vértice final.
	 * @param cost Costo que será modificado.
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * Método que adiciona un vértice con un Id único. El vértice tiene la información InfoVertex.
	 * @param idVertex Llave del vértice inicial.
	 * @param infoVertex Valor del vértice añadido.
	 */
	public void addVertex(K idVertex, V infoVertex);
	
	/**
	 * Retorna los identificadores de los vértices adyacentes a idVertex.
	 * @param idVertex Vértice en el que se buscará los vértices adyacentes.
	 * @return Iterable de los vértices adyacentes.
	 */
	public Iterable <K> adj (K idVertex);
	
	/**
	 * Desmarca todos los vértices del grafo.
	 */
	public void uncheck();
	
	/**
	 * Ejecuta la búsqueda de profundidad (DFS) sobre el grafo con el vértice s como origen. Los vértices resultado de la búsqueda quedan marcados y deben tener información que pertenecen a una misma componente conectada.
	 * @param s Llave del vértice inicial.
	 */
	public void dfs(K s);
	
	/**
	 * Obtiene la cantidad de componentes conectados del grafo. Cada vértice debe quedar marcado y debe reconocer a cuál componente conectada pertenece. En caso de que el grafo esté vacío, retorna 0.
	 * @return Número de vértices de componentes conectados.
	 */
	public int cc();
	
	/**
	 * Método que obtiene los vértices alcanzados a partir del vértice idVertex después de la ejecución de los métodos dfs(K) y cc().
	 * @param idVertex Llave del vértice inicial.
	 * @return Iterable de los vértices alcanzados.
	 */
	public Iterable<K> getCC(K idVertex);
	


	
	


	
}
