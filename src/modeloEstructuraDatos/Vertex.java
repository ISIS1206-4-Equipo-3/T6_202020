package modeloEstructuraDatos;

import java.util.List;

public class Vertex<K extends Comparable<K>,V> {
	/**
	 * Crea un vértice con identificador único y valor 
	 * (información asociada), el vértice inicia desmarcado
	 * @param id identificador
	 * @param value valor asociado
	 */
	public Vertex(K id, V value){
		
	}
	
	/**
	 * Devuelve el identificador del vértice
	 * @return identificador del vértice
	 */
	public  K getId() {
		return null;
	}
	
	/**
	 * Devuelve el valor asociado al vértice
	 * @return valor asociado al vértice
	 */
	public V getInfo() {
		return null;
	}
	
	/**
	 * Retorna si el vértice está marcado o no
	 * @return true en el caso de marcado, false en el caso contrario
	 */
	public boolean getMark() {
		return false;
	}
	
	/**
	 *  Agrega un arco adyacente al vértice
	 * @param edge arco adyacente que se quiere agregar al vertice
	 */
	public void addEdge( Edge<K,V> edge ) {
		
	}
	
	/**
	 * marca el vertice
	 */
	public void mark() {
		
	}
	
	/**
	 * desmarca el vertice
	 */
	public void unmark() {
		
	}
	
	/**
	 * Retorna el número de arcos (salientes) del vértice
	 * @return número de arcos salientes del vértice
	 */
	public int outdegree() {
		return 0;
	}
	
	/**
	 * Retorna el número de arcos (entrantes) del vértice
	 * @return número de arcos entrantes del vértice
	 */
	public int indegree() {
		return 0;
	}
	
	/**
	 * Retorna el arco con el vértice vertex (si existe). 
	 * Retorna null si no existe.
	 * @param vertex vertice a buscar y a retornar su arco
	 * @return arco con vertice vertex, null en  el caso de no encontrarlo/no existir.
	 */
	public Edge<K,V> getEdge(K vertex){
		return null;
	}
	
	 /**
	  * Retorna una lista con sus vértices adyacentes (salientes)
	  * @return lista con sus vértices adyacentes salientes
	  */
	public List<Vertex<K,V>> vertices(){
		return null;
	}
	
	 /**
	  * Retorna una lista con sus arcos adyacentes (salientes)
	  * @return lista con sus arcos adyacentes salientes
	  */
	public List<Edge<K,V>> edges(){
		return null;
	}
	
	
	
	
}
