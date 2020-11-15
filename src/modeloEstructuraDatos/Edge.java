package modeloEstructuraDatos;

public class Edge<K extends Comparable<K>, V> {
	
	/**
	 * Crea el arco desde el vértice source al vértice dest con peso weight
	 * @param source vertice de salida del arco
	 * @param dest vertice destino del arco
	 * @param weight peso del arco a crear
	 */
	public Edge(Vertex<K,V> source, Vertex<K,V> dest, double weight) {
		
	}
	
	/**
	 * Devuelve el vértice origen 
	 * @return vértice origen
	 */
	public Vertex<K,V>  getSource(){
		return null;
	}
	
	/**
	 * Devuelve el vértice destino
	 * @return vértice destino
	 */
	public Vertex<K,V>  getDest(){
		return null;
	}
	
	/**
	 * Devuelve el peso del arco
	 * @return peso del arco
	 */
	public double weight() {
		return 0;
	}
	
	/**
	 * Modifica el peso del arco
	 */
	public void setWeight(double weight) {
		
	}
}
