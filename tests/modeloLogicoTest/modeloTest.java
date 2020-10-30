package modeloLogicoTest;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import modeloLogico.Modelo;

public class modeloTest {


	private Modelo modelo;

	public void setUp1()
	{
		modelo = new Modelo();
		modelo.cargarDatosPorFechaInicial("./data/small/us_accidents_small.csv");


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
			assertNotEquals("No existen accidentes en esta fecha", modelo.conocerAccidentesDeUnaFecha("2016-06-23"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			modelo.conocerAccidentesDeUnaFecha("Colombia");
			fail();
		}catch (Exception e) {
			
		}
	}
	@Test
	public void testConocerLosAccidentesEnUnRangoDeHoras()
	{
		setUp1();
		assertNull(modelo.conocerAccidentesRangoDeHorasREQ5("24:00", "23:59"));
	}
	@Test
	public void testConocerLosAccidentesEnRangoFechasREQ3()
	{
		setUp1();
		String fecha1 = "2016-02-09";
		String fecha2 = "2016-06-23";
		try
		{
		String resp = modelo.conocerLosAccidentesEnRangoFechasREQ3(fecha2, "2016-06-24");
		assertTrue(resp.startsWith("\nSe han registrado " + 38));
		}
		catch(Exception e)
		{
			fail();
		}	
		
		
	
	}
	@Test
	public void testConocerLosAccidentesAUnaFechaREQ2()
	{
		setUp1();
		try {
			String resp = modelo.conocerLosAccidentesAUnaFechaREQ2("2016-06-24");
			assertTrue(resp.startsWith("\nSe han registrado " + modelo.darCantidadDeAccidentesCargados()));
		} catch (ParseException e) {
			fail();
		}
		try {
			modelo.conocerLosAccidentesAUnaFechaREQ2("Hola");
			fail();
		} catch (ParseException e) {
			
		}
		try {
			String resp = modelo.conocerLosAccidentesAUnaFechaREQ2("2016-02-08");
			assertEquals(null, resp);
		} catch (ParseException e) {
			
		}
		
		
	}
		
		@Test
		public void testconocerEstadoConMasAccidentesEnRangoDeFechas() {
			setUp1();		
			try {
				assertNotEquals("No existen accidentes en esta fecha", modelo.conocerEstadoConMasAccidentesEnRangoDeFechas("2016-5-5","2016-10-10"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				modelo.conocerEstadoConMasAccidentesEnRangoDeFechas("Prueba","Prueba");
				fail();
			}catch (Exception e) {
				
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
