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

	@Test
	public void testGradoEntradaSalida()
	{
		setUp1();
		assertEquals(modelo.gradoEntradaSalida(119), "El grado de entrada de la estacion 119 es: 3, el de salida es: 53");
		assertEquals(modelo.gradoEntradaSalida(83), "El grado de entrada de la estacion 83 es: 3, el de salida es: 162");
		assertEquals(modelo.gradoEntradaSalida(0), "Esta estacion no existe");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
