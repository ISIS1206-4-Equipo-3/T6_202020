package modeloEstructuraDatosTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Edge;
import modeloEstructuraDatos.Vertex;

public class DiGraphTest {

	private DiGraph digraph;
	private DiGraph digraph2;

	public void setUp1() {
		digraph = new DiGraph<Integer, String>();
		digraph.insertVertex(1, "a");
		digraph.insertVertex(2, "b");
		digraph.insertVertex(3, "c");
		digraph.insertVertex(4, "d");
		digraph.insertVertex(5, "e");
		digraph.addEdge(1, 2, 4);
		digraph.addEdge(1, 5, 18);
		digraph.addEdge(2, 1, 2);
		digraph.addEdge(2, 3, 10);
		digraph.addEdge(3, 4, 12);
		digraph.addEdge(4, 2, 8);
		digraph.addEdge(5, 1, 16);
		digraph.addEdge(5, 2, 6);
		digraph.addEdge(5, 4, 14);
	}

	public void setUp2() {
		digraph2 = new DiGraph<Integer, String>();
		digraph2.insertVertex(1, "a");
		digraph2.insertVertex(2, "b");
		digraph2.insertVertex(3, "c");
		digraph2.insertVertex(4, "d");
		digraph2.insertVertex(5, "e");
		digraph2.insertVertex(6, "f");
		digraph2.insertVertex(7, "g");
		digraph2.insertVertex(8, "h");
		digraph2.insertVertex(9, "i");
	}


	@Test
	public void testContainsVertex() {
		setUp1();
		assertEquals(true, digraph.containsVertex(1));
		assertEquals(true, digraph.containsVertex(2));
		assertEquals(true, digraph.containsVertex(4));
		assertEquals(true, digraph.containsVertex(5));
		assertEquals(false, digraph.containsVertex(7));
		assertEquals(false, digraph.containsVertex(8));
		assertEquals(false, digraph.containsVertex(100));
	}

	@Test
	public void testNumVertices() {
		setUp1();
		setUp2();
		assertEquals (5,digraph.numVertices());
		assertEquals (9,digraph2.numVertices());
	}

	@Test
	public void testNumEdges() {
		setUp1();
		setUp2();
		assertEquals (9,digraph.numEdges());
		assertEquals (0,digraph2.numEdges());
	}

	@Test
	public void testInsertVertex() {
		digraph = new DiGraph<Integer, String>();
		digraph.insertVertex(1, "a");
		assertEquals (1, digraph.numVertices());
	}

	@Test
	public void testAddEdge() {
		digraph = new DiGraph<Integer, String>();
		digraph.insertVertex(1, "a");
		digraph.insertVertex(2, "b");
		digraph.addEdge(1, 2, 3);
		assertEquals (1, digraph.numEdges());
	}

	@Test
	public void testGetVertex() {
		setUp2();
		assertNotNull (digraph2.getVertex(1));
		assertNotNull (digraph2.getVertex(3));
		assertNotNull (digraph2.getVertex(5));
		assertNull(digraph2.getVertex(10));
		assertNull(digraph2.getVertex(5000));
	}

	@Test
	public void testGetEdge() {
		setUp1();
		assertNotNull(digraph.getEdge(1, 2));
		assertNotNull(digraph.getEdge(2, 1));
		assertNotNull(digraph.getEdge(2, 3));
		assertNotNull(digraph.getEdge(5, 4));
		assertNotNull(digraph.getEdge(3, 4));

		assertNull(digraph.getEdge(4, 3));
		assertNull(digraph.getEdge(4, 5));
		assertNull(digraph.getEdge(2, 4));
		assertNull(digraph.getEdge(3, 1));
		assertNull(digraph.getEdge(1, 3));
	}

	@Test
	public void testAdjacentEdges() {
		setUp1();

		assertEquals(2, digraph.adjacentEdges(1).size());
		assertEquals(3, digraph.adjacentEdges(5).size());
		assertEquals(1, digraph.adjacentEdges(3).size());
	}

	@Test
	public void testAdjacentVertex() {

		setUp1();
		assertEquals(2, digraph.adjacentVertex(1).size());
		assertEquals(3, digraph.adjacentVertex(5).size());
		assertEquals(1, digraph.adjacentVertex(3).size());
	}

	@Test
	public void testIndegree() {
		setUp1();
		assertEquals(2, digraph.indegree(1));
		assertEquals(3, digraph.indegree(2));
		assertEquals(1, digraph.indegree(3));
	}

	@Test
	public void testOutdegree() {
		setUp1();
		assertEquals(2, digraph.outdegree(1));
		assertEquals(3, digraph.outdegree(5));
		assertEquals(1, digraph.outdegree(3));
	}

	@Test
	public void testEdges() {
		setUp1();
		setUp2();
		assertEquals(9, digraph.edges().size());
		assertEquals(0, digraph2.edges().size());
	}

	@Test
	public void testVertices() {
		setUp1();
		setUp2();
		assertEquals(5, digraph.vertices().size());
		assertEquals(9, digraph2.vertices().size());
	}

}
