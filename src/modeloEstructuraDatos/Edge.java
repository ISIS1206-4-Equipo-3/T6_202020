package modeloEstructuraDatos;

public class Edge<K extends Comparable<K>, V> {
	
	private Vertex<K,V> source;
	private Vertex<K,V> destino;
	private double peso;
	
	/**
	 * Crea el arco desde el vértice source al vértice dest con peso weight
	 * @param source vertice de salida del arco
	 * @param dest vertice destino del arco
	 * @param weight peso del arco a crear
	 */
	public Edge(Vertex<K,V> sour, Vertex<K,V> dest, double weight) {
		source = sour;
		destino = dest;
		peso = weight;
	}
	
	/**
	 * Devuelve el vértice origen 
	 * @return vértice origen
	 */
	public Vertex<K,V>  getSource(){
		return source;
	}
	
	/**
	 * Devuelve el vértice destino
	 * @return vértice destino
	 */
	public Vertex<K,V>  getDest(){
		return destino;
	}
	
	/**
	 * Devuelve el peso del arco
	 * @return peso del arco
	 */
	public double weight() {
		return peso;
	}
	
	/**
	 * Modifica el peso del arco
	 */
	public void setWeight(double weight) {
		peso = weight;
	}
}
