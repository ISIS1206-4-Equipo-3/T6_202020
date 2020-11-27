package modeloLogico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Edge;
import modeloEstructuraDatos.TablaHashLinearProbing;
import modeloEstructuraDatos.Vertex;

public class AlgoritmoKosajaru <K extends Comparable<K>, V> {

	private TablaHashLinearProbing<K, Vertex<K,V>> tablaHash;
	private ArrayList<Vertex<K,V>> vectores;
	private TablaHashLinearProbing<Integer, Boolean> marcados;
	private TablaHashLinearProbing<Integer, Integer> clusters;
	
	private int count; // number of strong components 

	public AlgoritmoKosajaru(DiGraph G)
	{
		tablaHash = G.darTablaHashAct();
		vectores = (ArrayList<Vertex<K, V>>) tablaHash.valueSet();
		marcados = new TablaHashLinearProbing<Integer,Boolean>(vectores.size());
		for (Vertex<K, V> vertex : vectores) {
			marcados.put((Integer) vertex.getId(), false);
		}
		
		DiGraph graphoReves = G.darVuelta();
		
		ArrayList<Integer> order = DFOOrder(graphoReves, (int) tablaHash.darPrimerElemento().getId()); 
		
		Integer darPrimeroEnfalseID = darPrimeroEnFalse();
		while(darPrimeroEnfalseID!=null) {
			order.addAll(DFOOrder(graphoReves, darPrimeroEnfalseID));
			darPrimeroEnfalseID = darPrimeroEnFalse();
		}
		
		Collections.reverse(order);

		clusters = new TablaHashLinearProbing<Integer, Integer>(vectores.size());
		
		for (int s : order)
			if (marcados.get(s))
			{
				dfs(G, s); 
				count++;
			}
	}
	
	private ArrayList<Integer> rtaDFO;

	private ArrayList<Integer> DFOOrder(DiGraph G, int idVerticeFuente) {
		rtaDFO = new ArrayList<Integer>();
		DFOORderRecursivo(G, idVerticeFuente);
		rtaDFO.add(idVerticeFuente);
		return rtaDFO;
	}

	private void DFOORderRecursivo(DiGraph G, int idVerticeAct) {
		marcados.remove(idVerticeAct);
		marcados.put(idVerticeAct, true);
		
		ArrayList<Integer> ordenDeVisita = G.getVertex(idVerticeAct).darIdsAdyacentes();
		Comparator<Integer> cmp = Collections.reverseOrder();
		Collections.sort(ordenDeVisita, cmp);
		
		for (Integer w : ordenDeVisita) {
			if (!marcados.get(w)) {
				DFOORderRecursivo(G, w);
				rtaDFO.add(w);
			}
		}
	}

	private Integer darPrimeroEnFalse() {
		ArrayList<Boolean> valMarcados = (ArrayList<Boolean>) marcados.valueSet();
		int cont = 0 ;
		for (Boolean boolean1 : valMarcados) {
			if(boolean1==false) return marcados.keySet().get(cont);
			cont++;
		}
		return null;
	}


	private void dfs(DiGraph G, int idVerticeAct) {
		marcados.remove(idVerticeAct);
		marcados.put(idVerticeAct, false);
		clusters.put(idVerticeAct, count);
		
		ArrayList<Integer> ordenDeVisita = G.getVertex(idVerticeAct).darIdsAdyacentes();
		Comparator<Integer> cmp = Collections.reverseOrder();
		Collections.sort(ordenDeVisita, cmp);
		
		for (Integer w : ordenDeVisita) {
			if (marcados.get(w))
				dfs(G, w);
		}
	}
	
	/**
	 * Retorna informacion si 2 elementos se encuentran en el mismo cluster
	 * @param v id1 a verificar
	 * @param w id a verificar
	 * @return true en caso de que sean fuertemente conectados, false en el caso contrario
	 */
	public boolean fuertementeConectados(int v, int w) { return clusters.get(v) == clusters.get(w); }

	/**
	 * Retorna el numero del cluster en el que se encuentra el elemnento con id que entra por parametro
	 * @param k id del vertice del que se quiere saber el cluster
	 * @return
	 */
	public int darClusterDe(int k)
	{ return clusters.get(k); }

	/**
	 * Retorna la cantidad de clusters que tiene el grafo
	 * @return cantidad de clusters del grafo
	 */
	public int cantidadDeClusters()
	{ return count;}
	
	
	//OJO SOLO PARA DEBUGGEAR NO USAR EN LA PRACTICA
	/**
	 * Imprime resultados que ayudan en el debug.
	 */
	public void imprimirResultados(){
		ArrayList<K> llaves =  (ArrayList<K>) clusters.keySet();
		ArrayList<V> valores = (ArrayList<V>)clusters.valueSet();
		int pos = 0;
		for (V v : valores) {
			System.out.println("ID: " + llaves.get(pos) + " - Cluster: " + v);
			pos++;
		}
	}
	/**
	 * retorna un arraylist con los elementos que pertenecen al cluster qu entra por parametro
	 * @param cluster a buscar
	 * @return arraylist con los elementos que pertenecen al cluster qu entra por parametro
	 */
	public ArrayList<K> darIDsEnCluster(K cluster){
		ArrayList<K> rta = new ArrayList<K>();
		ArrayList<K> llaves =  (ArrayList<K>) clusters.keySet();
		ArrayList<K> valores = (ArrayList<K>)clusters.valueSet();
		int pos = 0;
		for (K k : valores) {
			if(cluster.equals(k)) rta.add(llaves.get(pos));
			pos++;
		}
		return rta;
	}
	
	public DiGraph<K,V> formarGrafoParaCluster(K cluster) {
		
		DiGraph<K, V> rta = new DiGraph<K, V>();
		ArrayList<K> idsVertices = darIDsEnCluster(cluster);
		for (K id : idsVertices) {
			rta.insertVertex(id, tablaHash.get(id).getInfo());
		}
		
		for (K id : idsVertices) {
			Vertex<K, V> act = tablaHash.get(id);
			ArrayList<Edge<K,V>> edgesAct =  (ArrayList<Edge<K, V>>) act.edges();
			for (Edge<K,V> edgeAct : edgesAct) {
				K idDestino = (K)edgeAct.getDest().getId();
				if(idsVertices.contains(idDestino)) rta.addEdge(id, idDestino, edgeAct.weight());
			}
		}
		
		return rta;
	}

}
