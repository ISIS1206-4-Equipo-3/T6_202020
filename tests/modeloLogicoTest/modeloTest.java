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
		assertEquals(modelo.gradoEntradaSalida(119).contains("El grado de entrada de la estacion 119 es: 3, el de salida es: 53"),true);
		assertEquals(modelo.gradoEntradaSalida(83).contains("El grado de entrada de la estacion 83 es: 3, el de salida es: 162"), true);
		assertEquals(modelo.gradoEntradaSalida(0).contains("Esta estacion no existe"),true );
	}
	@Test
	public void testEstacionesCriticas()
	{
		setUp1();
		assertTrue(!modelo.estacionesCriticas().contains("Las 3 estaciones con mas llegadas son:\n 1. "));
	}
	@Test
	public void testRecomendadorDeRutas()
	{
		setUp1();
		assertEquals("No existe ningun camino entre las estaciones que mas usan las personas en el rango de edad indicado", modelo.recomendadorDeRutas(2));
	}
	
	@Test
	public void testRutaTuristicaPorResistencia()
	{
		setUp1();
		assertNotNull( modelo.rutaTuristicaPorResistencia(10, 72));
		assertNotNull( modelo.rutaTuristicaPorResistencia(10, 128));
	}
	@Test
	public void testRutaDeInteresTuristico()
	{
		setUp1();
		assertNotNull( modelo.rutaDeInteresTuristico(-73.99392555, 40.76727216, -73.95928176, 40.68676784));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
