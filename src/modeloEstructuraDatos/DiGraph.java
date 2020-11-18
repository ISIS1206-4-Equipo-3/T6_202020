package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class DiGraph<K extends Comparable<K>, V> implements IDiGraph<K, V> {

	TablaHashLinearProbing<K, Vertex<K,V>> tablaHash;

	public DiGraph () {
		tablaHash = new TablaHashLinearProbing<K, Vertex<K,V>>(2);
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
		Vertex <K,V> nuevo = new Vertex<K,V>(id, value);
		tablaHash.put(id, nuevo);
	}

	@Override
	public void addEdge(K source, K dest, double weight) {
		Vertex vertexSource = (Vertex) tablaHash.get(source);
		Vertex vertexDest = (Vertex) tablaHash.get(dest);
		if(vertexSource == null || vertexDest == null) 
		{System.out.println("OJO_EXISTIO_ERROR_EN_ADD_EDGE");}
		else {
			List<Edge<K,V>> lista = vertexSource.edges();
			boolean encontro = false;
			for(int i =0; i<lista.size() && !encontro ; i++)
			{
				Edge<K,V> arco = lista.get(i);
				if(arco.getDest().equals(vertexDest))
				{
					encontro = true;
					arco.sumarPeso(weight);
				}
			}
			if(!encontro)
			{
				Edge<K,V> arco = new Edge<>(vertexSource, vertexDest, weight);
				vertexSource.edges().add(arco);
				vertexSource.addVertex(vertexSource);
				vertexSource.UnOutDegreeMas();
				vertexDest.UnInDegreeMas();
			}
		}
	}

	@Override
	public Vertex<K, V> getVertex(K id) {
		if(tablaHash.get(id)!=null) return (Vertex<K, V>) tablaHash.get(id);
		return null;
	}

	@Override
	public Edge<K, V> getEdge(K idS, K idD) {
		// TODO Auto-generated method stub
		List <Edge<K, V>> edges = tablaHash.get(idS).edges();
		for (Edge<K, V> edge : edges) {
			if(edge.getDest().getId() ==  idD) return edge;
		}
		return null;
	}

	@Override
	public List<Edge<K, V>> adjacentEdges(K id) {
		return tablaHash.get(id).edges();
	}

	@Override
	public List<Vertex<K, V>> adjacentVertex(K id) {
		return tablaHash.get(id).vertices();
	}

	@Override
	public int indegree(K vertex) {
		Vertex act = (Vertex) tablaHash.get(vertex);
		if (act==null) return 0;
		return act.indegree();
	}

	@Override
	public int outdegree(K vertex) {
		Vertex act = (Vertex) tablaHash.get(vertex);
		if (act==null) return 0;
		return act.outdegree();
	}

	@Override
	public List<Edge<K, V>> edges() {
		ArrayList<Edge<K, V>> rta = new ArrayList<Edge<K, V>>();
		List <Vertex<K, V>> vertices = vertices();
		for (Vertex<K,V> vertice : vertices) {
			rta.addAll(vertice.edges());
		}
		return rta;
	}

	@Override
	public List <Vertex<K, V>> vertices() {
		return tablaHash.valueSet();
	}

}
