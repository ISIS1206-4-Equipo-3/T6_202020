package modeloLogicoTest;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import modeloEstructuraDatos.DiGraph;
import modeloLogico.Modelo;

public class modeloTest {


	private Modelo modelo;

	public void setUp1()
	{
		modelo = new Modelo();
		String[] lista = new String[1];
		lista[0] = "1";
		modelo.cargarDatos(lista);
	}


	@Test
	public void testCargarDatos() {
		setUp1();
		assertNotNull(modelo.darDiGraph());
		DiGraph<Integer, String> graph = modelo.darDiGraph();
		assertNotNull(graph.getVertex(72));
		assertNotNull(graph.getVertex(79));
		//System.out.println((graph.getVertex(72).getInfo()).toString()); esto solo funciona cuano se cargen con los valores 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
