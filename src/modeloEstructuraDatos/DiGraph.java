package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class DiGraph<K extends Comparable<K>, V> implements IDiGraph<K, V> {

	TablaHashLinearProbing<K, V> tablaHash;
	
	public DiGraph () {
		tablaHash = new TablaHashLinearProbing<K, V>(2);
	}
	
	@Override
	public boolean containsVertex(K id) {
		if (tablaHash.get(id)==null) return false;
		return true;
	}

	@Override
	public int numVertices() {
		return tablaHash.size();
	}

	@Override
	public int numEdges() {
		int rta = 0;
		ArrayList listaValores = (ArrayList) tablaHash.valueSet();
		for (Object vertex : listaValores) {
			Vertex act = (Vertex) vertex;
			rta += act.outdegree();
		}
		return rta;
	}

	@Override
	public void insertVertex(K id, V value) {
		tablaHash.put(id, value);
	}

	@Override
	public void addEdge(K source, K dest, double weight) {
		Vertex vertexsource = (Vertex) tablaHash.get(source);
		Vertex vertexdest = (Vertex) tablaHash.get(dest);
		if(vertexsource == null || vertexdest == null) System.out.println("OJO_EXISTIO_ERROR_EN_ADD_EDGE");
		
	}

	@Override
	public Vertex<K, V> getVertex(K id) {
		if(tablaHash.get(id)!=null) return (Vertex<K, V>) tablaHash.get(id);
		return null;
	}

	@Override
	public Edge<K, V> getEdge(K idS, K idD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Edge<K, V>> adjacentEdges(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex<K, V>> adjacentVertex(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int outdegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Edge<K, V>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex<K, V>> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

}
