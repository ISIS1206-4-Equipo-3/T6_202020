package modeloEstructuraDatos;

public class Edge<K extends Comparable<K>, V> {
	
	private Vertex<K,V> source;
	private Vertex<K,V> destino;
	private double peso;
	private int viajes;
	/**
	 * Crea el arco desde el vertice source al vertice dest con peso weight
	 * @param source vertice de salida del arco
	 * @param dest vertice destino del arco
	 * @param weight peso del arco a crear
	 */
	public Edge(Vertex<K,V> sour, Vertex<K,V> dest, double weight) {
		source = sour;
		destino = dest;
		peso = weight;
		viajes = 1;
	}
	public void anadirViaje()
	{
		viajes++;
	}
	public int getViajes() {
		return viajes;
	}

	public void setViajes(int viajes) {
		this.viajes = viajes;
	}

	/**
	 * Devuelve el vertice origen 
	 * @return vertice origen
	 */
	public Vertex<K,V>  getSource(){
		return source;
	}
	
	/**
	 * Devuelve el vertice destino
	 * @return vertice destino
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
	public void sumarPeso(double pesoASumar)
	{
		double pesoTotal = peso*viajes;
		anadirViaje();
		peso = (pesoTotal+pesoASumar)/viajes;
	}
	/**
	 * Modifica el peso del arco
	 */
	public void setWeight(double weight) {
		peso = weight;
	}
}
