package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class Vertex<K extends Comparable<K>,V> {
	
	private boolean marked;
	
	private List<Vertex<K,V>> vertices;
	
	private V valor;
	
	private K id;
	
	private int gradoIn;
	
	private int gradoOut;
	
	private int viajesLlegando;
	
	private int viajesSaliendo;
	
	private List<Edge<K,V>> arcos;
	/**
	 * Crea un vertice con identificador unico y valor 
	 * (informacion asociada), el vertice inicia desmarcado
	 * @param id identificador
	 * @param value valor asociado
	 */
	public Vertex(K id, V pValor){
		
		this.id = id;
		valor = pValor;
		marked = false;
		arcos = new ArrayList<Edge<K,V>>();
		vertices = new ArrayList<Vertex<K,V>>();
		gradoIn = 0;
		gradoOut = 0;
		viajesLlegando = 0;
		viajesSaliendo = 0;
	}
	
	/**
	 * Devuelve el identificador del vertice
	 * @return identificador del vertice
	 */
	public  K getId() {
		return id;
	}
	
	/**
	 * Devuelve el valor asociado al vertice
	 * @return valor asociado al vertice
	 */
	public V getInfo() {
		return valor;
	}
	
	/**
	 * Retorna si el vertice esta marcado o no
	 * @return true en el caso de marcado, false en el caso contrario
	 */
	public boolean getMark() {
		return marked;
	}
	
	/**
	 *  Agrega un arco adyacente al vertice
	 * @param edge arco adyacente que se quiere agregar al vertice
	 */
	public void addEdge( Edge<K,V> edge ) 
	{
		arcos.add(edge);
	}
	
	/**
	 * Suma 1 al gradoOut, o sea que se cuenta un arco saliente mas
	 */
	public void UnOutDegreeMas()
	{
		gradoOut++;
	}
	
	public int getViajesLlegando() {
		return viajesLlegando;
	}

	public void setViajesLlegando(int viajesLlegando) {
		this.viajesLlegando = viajesLlegando;
	}

	public int getViajesSaliendo() {
		viajesSaliendo();
		return viajesSaliendo;
	}

	public void setViajesSaliendo(int viajesSaliendo) {
		this.viajesSaliendo = viajesSaliendo;
	}

	public void viajesSaliendo()
	{
		for (Edge<K, V> edge : arcos) {
			viajesSaliendo += edge.getViajes();
		}
	}
	/**
	 * Suma 1 al gradoIn, o sea que se cuenta un arco entrante mas
	 */
	public void UnInDegreeMas()
	{
		gradoIn++;
	}
	/**
	 *  Agrega un vertice adyacente al vertice
	 * @param edge vertice adyacente que se quiere agregar al vertice
	 */
	public void addVertex( Vertex<K,V> vertice)
	{
		vertices.add(vertice);
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
		return gradoOut;
	}
	
	/**
	 * Retorna el numero de arcos (entrantes) del vertice
	 * @return numero de arcos entrantes del vertice
	 */
	public int indegree() {
		return gradoIn;
	}
	
	/**
	 * Retorna el arco con el vertice vertex (si existe). 
	 * Retorna null si no existe.
	 * @param vertex vertice a buscar y a retornar su arco
	 * @return arco con vertice vertex, null en  el caso de no encontrarlo/no existir.
	 */
	public Edge<K,V> getEdge(Vertex<K,V> vertex){
		for(int i = 0; i<arcos.size(); i++)
		{
			if(arcos.get(i).getDest().equals(vertex))
			{
				return arcos.get(i);
			}
		}
		return null;
	}
	
	public Edge<K,V> getEdge(K id){
		for(int i = 0; i<arcos.size(); i++)
		{
			if(arcos.get(i).getDest().getId()==id)
			{
				return arcos.get(i);
			}
		}
		return null;
	}
	
	 /**
	  * Retorna una lista con sus vertices adyacentes (salientes)
	  * @return lista con sus vertices adyacentes salientes
	  */
	public List<Vertex<K,V>> vertices(){
		return vertices;
	}
	
	 /**
	  * Retorna una lista con sus arcos adyacentes (salientes)
	  * @return lista con sus arcos adyacentes salientes
	  */
	public List<Edge<K,V>> edges(){
		return arcos;
	}
	
	public ArrayList<K> darIdsAdyacentes() {
		ArrayList<K> rta = new ArrayList<K>();
		for (Edge<K,V> act : arcos) {
			rta.add(act.getDest().getId());
		}
		return rta;
	}
	
	
	
	
}
