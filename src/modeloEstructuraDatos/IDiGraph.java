package modeloEstructuraDatos;

import java.util.List;

public interface IDiGraph<K extends Comparable<K>,V extends Comparable<V>>{

	/**
	 * Retorna true si el vértice con id suministrado está en el grafo
	 */
	public boolean containsVertex(K id);

	/**
	 * Devuelve el número de vértices en el grafo
	 */
	public int numVertices();

	/**
	 * Devuelve el número de arcos en el grafo
	 */
	public int numEdges();

	/**
	 * Añade un vértice al grafo con su identificador y valor.
	 */
	public void insertVertex(K id, V value);

	/**
	 * Añade un arco dirigido pesado entre el vértice source y dest, con peso weight. 
	 * Si el arco YA existe se modifica su peso.
	 */
	public void addEdge(K source, K dest, double weight);

	/**
	 * Retorna el vértice a partir de su identificador único.
	 */
	public Vertex <K,V> getVertex(K id);

	/**
	 * Retorna el arco entre los vértices idS y idD (si existe). 
	 * Retorna null si no existe
	 */
	public Edge<K,V> getEdge(K idS, K idD) ;

	/**
	 * Devuelve una lista de arcos adyacentes (salientes) al vértice con id
	 */
	public List<Edge<K,V>> adjacentEdges(K id);

	/**
	 * Devuelve una lista de vértices adyacentes (salientes) al vértice con id
	 */
	public List<Vertex<K,V>> adjacentVertex(K id);

	/**
	 * Devuelve el grado de entrada del vértice vertex (número de arcos entrantes)
	 */
	public int indegree(K vertex);

	/**
	 * Devuelve el grado de salida del vértice vertex (número de arcos salientes)
	 */
	public int outdegree(K vertex);

	/**
	 * Devuelve una lista con todos los arcos del grafo
	 */
	public List<Edge<K,V>> edges();

	/**
	 * Devuelve una lista con los vértices del grafo
	 */
	public List<Vertex<K,V>> vertices();
	
	
}
