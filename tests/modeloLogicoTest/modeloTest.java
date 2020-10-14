package modeloLogicoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modeloLogico.Modelo;

public class modeloTest {


	private Modelo modelo;

	public void setUp1()
	{
		modelo = new Modelo();
		modelo.cargarDatosPorFechaInicial(modelo.RUTA_DATOS_PRINCIPALES);


	}


	@Test
	public void testCargarDatosPorFechaInicial() {
		setUp1();
		assertNotNull(modelo.tabla);

	}

	@Test
	public void testConocerAccidentesDeUnaFecha() {
		setUp1();		
		try {
			assertNotEquals("No existen accidentes en esta fecha", modelo.conocerAccidentesDeUnaFecha("2016-02-29"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			modelo.conocerAccidentesDeUnaFecha("Colombia");
			fail();
		}catch (Exception e) {
			
		}
	}
}
