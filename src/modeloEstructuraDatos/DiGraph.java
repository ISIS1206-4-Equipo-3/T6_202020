package modeloEstructuraDatos;

import java.util.List;

public class DiGraph<K extends Comparable<K>,V> implements IDiGraph<K, V> {

	
	@Override
	public boolean containsVertex(K id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertVertex(K id, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(K source, K dest, double weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vertex<K, V> getVertex(K id) {
		// TODO Auto-generated method stub
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
