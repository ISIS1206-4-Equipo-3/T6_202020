package modeloEstructuraDatos;

import java.util.List;

public class Vertex<K extends Comparable<K>,V> {
	
	boolean marked;
	List<Vertex<K,V>> vertices;
	
	List<Edge<K,V>> arcos;
	/**
	 * Crea un vertice con identificador unico y valor 
	 * (informacion asociada), el vertice inicia desmarcado
	 * @param id identificador
	 * @param value valor asociado
	 */
	public Vertex(K id, V value){
		marked = false;
	}
	
	/**
	 * Devuelve el identificador del vertice
	 * @return identificador del vertice
	 */
	public  K getId() {
		return null;
	}
	
	/**
	 * Devuelve el valor asociado al vertice
	 * @return valor asociado al vertice
	 */
	public V getInfo() {
		return null;
	}
	
	/**
	 * Retorna si el vertice esta marcado o no
	 * @return true en el caso de marcado, false en el caso contrario
	 */
	public boolean getMark() {
		return false;
	}
	
	/**
	 *  Agrega un arco adyacente al vertice
	 * @param edge arco adyacente que se quiere agregar al vertice
	 */
	public void addEdge( Edge<K,V> edge ) {
		
	}
	
	/**
	 * marca el vertice
	 */
	public void mark() {
		marked = true;
	}
	
	/**
	 * desmarca el vertice
	 */
	public void unmark() {
		marked = false;
	}
	
	/**
	 * Retorna el numero de arcos (salientes) del vertice
	 * @return numero de arcos salientes del vertice
	 */
	public int outdegree() {
		return 0;
	}
	
	/**
	 * Retorna el numero de arcos (entrantes) del vertice
	 * @return numero de arcos entrantes del vertice
	 */
	public int indegree() {
		return 0;
	}
	
	/**
	 * Retorna el arco con el vertice vertex (si existe). 
	 * Retorna null si no existe.
	 * @param vertex vertice a buscar y a retornar su arco
	 * @return arco con vertice vertex, null en  el caso de no encontrarlo/no existir.
	 */
	public Edge<K,V> getEdge(K vertex){
		return null;
	}
	
	 /**
	  * Retorna una lista con sus vertices adyacentes (salientes)
	  * @return lista con sus vertices adyacentes salientes
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
