package modeloLogicoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Vertex;
import modeloLogico.AlgoritmoKosajaru;

public class AlgoritmoKosajaruTest {

	AlgoritmoKosajaru<Integer, String> ADK;
	@Before
	public void setUp1(){
		DiGraph<Integer, String> grafo;
		grafo = new DiGraph<Integer, String>();
		grafo.insertVertex(0, "cero");
		grafo.insertVertex(1, "uno");
		grafo.insertVertex(2, "dos");
		grafo.insertVertex(3, "tres");
		grafo.insertVertex(4, "cuatro");
		grafo.insertVertex(5, "cinco");
		grafo.insertVertex(6, "seis");
		grafo.insertVertex(7, "siete");
		grafo.insertVertex(8, "ocho");
		grafo.insertVertex(9, "nueve");
		grafo.insertVertex(10, "diez");
		grafo.insertVertex(11, "once");
		grafo.insertVertex(12, "doce");
		grafo.addEdge(0, 1, 7);
		grafo.addEdge(0, 5, 1);
		grafo.addEdge(2, 0, 8);
		grafo.addEdge(2, 3, 5);
		grafo.addEdge(3, 2, 6);
		grafo.addEdge(3, 5, 2);
		grafo.addEdge(4, 3, 4);
		grafo.addEdge(4, 2, 10);
		grafo.addEdge(5, 4, 3);
		grafo.addEdge(6, 0, 9);
		grafo.addEdge(6, 4, 11);
		grafo.addEdge(6, 8, 15);
		grafo.addEdge(6, 9, 13);
		grafo.addEdge(7, 6, 16);
		grafo.addEdge(7, 9, 17);
		grafo.addEdge(8, 6, 14);
		grafo.addEdge(9, 10, 18);
		grafo.addEdge(9, 11, 22);
		grafo.addEdge(10, 12, 20);
		grafo.addEdge(11, 12, 21);
		grafo.addEdge(11, 4, 12);
		grafo.addEdge(12, 9, 19);
		
		ADK = new AlgoritmoKosajaru<Integer, String>(grafo);
		
	}

	@Test
	public void testAlgoritmoKosajaru() {
		setUp1();
		assertNotEquals(null, ADK);
		System.out.println("-----RESULTADOS OBTENIDOS-----");
		ADK.imprimirResultados();
		System.out.println("\n\n-----RESULTADOS ESPERADOS-----");
		System.out.println("ID: 0 - Cluster: 1\n" + 
				"ID: 1 - Cluster: 0\n" + 
				"ID: 2 - Cluster: 1\n" + 
				"ID: 3 - Cluster: 1\n" + 
				"ID: 4 - Cluster: 1\n" + 
				"ID: 5 - Cluster: 1\n" + 
				"ID: 6 - Cluster: 3\n" + 
				"ID: 7 - Cluster: 4\n" + 
				"ID: 8 - Cluster: 3\n" + 
				"ID: 9 - Cluster: 2\n" + 
				"ID: 10 - Cluster: 2\n" + 
				"ID: 11 - Cluster: 2\n" + 
				"ID: 12 - Cluster: 2");
	}

	@Test
	public void testFuertementeConectados() {
		setUp1();
		assertEquals(true, ADK.fuertementeConectados(3, 2));
		assertEquals(true, ADK.fuertementeConectados(2, 3));
		assertEquals(true, ADK.fuertementeConectados(6, 8));
		assertEquals(true, ADK.fuertementeConectados(2, 3));
		assertEquals(true, ADK.fuertementeConectados(0, 5));
		assertEquals(true, ADK.fuertementeConectados(9, 12));
		assertEquals(true, ADK.fuertementeConectados(10, 11));
		assertEquals(true, ADK.fuertementeConectados(9, 10));
		assertEquals(true, ADK.fuertementeConectados(3, 5));
		assertEquals(true, ADK.fuertementeConectados(5, 4));
		assertEquals(true, ADK.fuertementeConectados(2, 4));
		assertEquals(false, ADK.fuertementeConectados(2, 9));
		assertEquals(false, ADK.fuertementeConectados(2, 7));
		assertEquals(false, ADK.fuertementeConectados(2, 1));
		assertEquals(false, ADK.fuertementeConectados(1, 9));
		assertEquals(false, ADK.fuertementeConectados(8, 9));
		assertEquals(false, ADK.fuertementeConectados(5, 9));
		assertEquals(false, ADK.fuertementeConectados(9, 7));
		assertEquals(false, ADK.fuertementeConectados(10, 7));
		assertEquals(false, ADK.fuertementeConectados(7, 1));
		assertEquals(false, ADK.fuertementeConectados(1, 6));
	}

	@Test
	public void testDarClusterDe() {
		setUp1();
		assertEquals(0,ADK.darClusterDe(1));
		assertEquals(1,ADK.darClusterDe(0));
		assertEquals(1,ADK.darClusterDe(4));
		assertEquals(1,ADK.darClusterDe(3));
		assertEquals(3,ADK.darClusterDe(8));
		assertEquals(3,ADK.darClusterDe(6));
		assertEquals(2,ADK.darClusterDe(9));
		assertEquals(2,ADK.darClusterDe(12));
		assertEquals(2,ADK.darClusterDe(10));
		assertEquals(4,ADK.darClusterDe(7));
	}

	@Test
	public void testCantidadDeClusters() {
		assertEquals(5,ADK.cantidadDeClusters());
	}
	
	@Test
	public void testDarIDsEnCluster() {
		setUp1();
		assertEquals(true,ADK.darIDsEnCluster(0).contains(1));
		assertEquals(1,ADK.darIDsEnCluster(0).size());
		assertEquals(true,ADK.darIDsEnCluster(1).contains(0));
		assertEquals(true,ADK.darIDsEnCluster(1).contains(3));
		assertEquals(5,ADK.darIDsEnCluster(1).size());
		assertEquals(true,ADK.darIDsEnCluster(3).contains(8));
		assertEquals(2,ADK.darIDsEnCluster(3).size());
		assertEquals(1,ADK.darIDsEnCluster(4).size());
		
	}
	
	@Test
	public void testFormarGrafoParaCluster() {
		setUp1();
		DiGraph <Integer,String> graphoact = ADK.formarGrafoParaCluster(1);
		assertEquals(5,graphoact.numVertices());
		assertEquals(8, graphoact.numEdges());
		assertEquals(1, graphoact.adjacentVertex(0).size());
		assertEquals(1, graphoact.adjacentVertex(5).size());
		assertEquals(2, graphoact.adjacentVertex(4).size());
		assertEquals(true, graphoact.containsVertex(2));
		assertEquals(false, graphoact.containsVertex(1));
		assertEquals(false, graphoact.containsVertex(6));
		assertEquals(false, graphoact.containsVertex(10));
	}

}
