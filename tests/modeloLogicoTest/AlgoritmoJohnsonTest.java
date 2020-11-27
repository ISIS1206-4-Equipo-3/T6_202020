package modeloLogicoTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Vertex;
import modeloLogico.AlgoritmoJohnson;
import modeloLogico.AlgoritmoKosajaru;

public class AlgoritmoJohnsonTest {

	private AlgoritmoJohnson ADJ;

	DiGraph<Integer, String> grafo;
	
	public void setUp1() {
		
		grafo = new DiGraph<Integer, String>();
		
		grafo.insertVertex(0, "cero");
		grafo.insertVertex(2, "dos");
		grafo.insertVertex(3, "tres");
		grafo.insertVertex(4, "cuatro");
		grafo.insertVertex(5, "cinco");
		grafo.addEdge(0, 5, 1);
		grafo.addEdge(2, 0, 8);
		grafo.addEdge(2, 3, 5);
		grafo.addEdge(3, 2, 6);
		grafo.addEdge(3, 5, 2);
		grafo.addEdge(4, 3, 4);
		grafo.addEdge(4, 2, 10);
		grafo.addEdge(5, 4, 3);
		
		ADJ = new AlgoritmoJohnson ();
	}
	
public void setUp2() {
		
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
		
		ADJ = new AlgoritmoJohnson ();
	}
	

	@Test
	public void testSimpleCyles1() {
		setUp1();
		List<List<Vertex<Integer,String>>> listaDeListas = ADJ.simpleCyles(grafo, grafo.getVertex(0));
		imprimirListas(listaDeListas);
		assertEquals(2,listaDeListas.size());
	}
	
	@Test
	public void testSimpleCyles2() {
		setUp2();
		AlgoritmoKosajaru<Integer, String> ADK  = new AlgoritmoKosajaru<Integer, String>(grafo);
		
		
		DiGraph<Integer, String> adaptado = ADK.formarGrafoParaCluster(0);
		List<List<Vertex<Integer,String>>> listaDeListas = listaDeListas = ADJ.simpleCyles(adaptado, adaptado.getVertex(1));
		imprimirListas(listaDeListas);
		assertEquals(0,listaDeListas.size());
	}
	
	@Test
	public void testSimpleCyles3() {
		setUp2();
		AlgoritmoKosajaru<Integer, String> ADK  = new AlgoritmoKosajaru<Integer, String>(grafo);
		
		DiGraph<Integer, String> adaptado = ADK.formarGrafoParaCluster(2);
		List<List<Vertex<Integer,String>>> listaDeListas = listaDeListas = ADJ.simpleCyles(adaptado, adaptado.getVertex(9));
		imprimirListas(listaDeListas);
		assertEquals(2,listaDeListas.size());
	}
	
	@Test
	public void testSimpleCyles4() {
		setUp2();
		AlgoritmoKosajaru<Integer, String> ADK  = new AlgoritmoKosajaru<Integer, String>(grafo);
		
		DiGraph<Integer, String> adaptado = ADK.formarGrafoParaCluster(3);
		List<List<Vertex<Integer,String>>> listaDeListas = listaDeListas = ADJ.simpleCyles(adaptado, adaptado.getVertex(8));
		imprimirListas(listaDeListas);
		assertEquals(1,listaDeListas.size());
	}
	
	private void imprimirListas(List<List<Vertex<Integer,String>>> listaDeListas) {
		for (List<Vertex<Integer, String>> list : listaDeListas) {
			System.out.print("[");
			for (Vertex<Integer, String> vertice : list) {
				System.out.print(vertice.getId()+",");
			}
			System.out.println("]");
		}
		System.out.println("-------------");
	}
	
	

}
