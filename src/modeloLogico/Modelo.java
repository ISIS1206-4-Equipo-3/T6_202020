package modeloLogico;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import modeloEstructuraDatos.Accidente;
import modeloEstructuraDatos.BST;
import modeloEstructuraDatos.RBT;



public class Modelo {

	public String RUTA_DATOS_PRINCIPALES = "./data/small/us_accidents_small.csv";

	private FileReader archivo;
	private CSVReader lector;
	public RBT tabla;


	public void cargarDatosPorFechaInicial(String rutaPrincipal)
	{
		try {
			long startTime = System.nanoTime();
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			tabla = new RBT<Date, Accidente>();
			archivo = new FileReader(rutaPrincipal);
			lector = new CSVReaderBuilder (archivo).withCSVParser(parser).build();
			String [] linea = lector.readNext();
			while(((linea = lector.readNext())!=null)){
				if(linea != null ) {
					String[] fecha = linea[4].split(" ");
					String[] fechaJusta = fecha[0].split("-");
					Date fechaInicial = new Date();
					try
					{
						DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
						fechaInicial = formato.parse(fechaJusta[0] + "-" + fechaJusta[1] + "-" + fechaJusta[2] );
					}
					catch (Exception e)
					{
						fechaInicial = new Date(0, 0, 0);
					}
					int severidad = Integer.parseInt(linea[3]);
					String id = linea[0];
					String ciudad = linea[15];
					Accidente accidente = new Accidente(fechaInicial, id, severidad, ciudad);
					tabla.put(fechaInicial, accidente);
				}
			}

			long endTime = System.nanoTime();
			System.out.println("Valor minimo");

			Accidente minimo;
			try {
				minimo = (Accidente) tabla.get(tabla.min());
			} catch (Exception e) {
				minimo = (Accidente) ((ArrayList) tabla.get(tabla.min())).get(0);
			}

			minimo.imprimirAccidente();
			System.out.println("Valor maximo");

			Accidente maximo;
			try {
				maximo = (Accidente) tabla.get(tabla.max());
			} catch (Exception e) {
				maximo = (Accidente) ((ArrayList) tabla.get(tabla.max())).get(((ArrayList) tabla.get(tabla.max())).size()-1);
			}

			maximo.imprimirAccidente();
			System.out.println("-------- Los datos fueron cargados correctamente --------\n");
			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String conocerAccidentesDeUnaFecha(String fecha) throws Exception
	{
		if(tabla ==null) return "\n++CAUTION: Es necesario cargar primero los datos (Opcion 10)\n";
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicial = formato.parse(fecha);
		ArrayList accidentes = (ArrayList) tabla.get(fechaInicial);
		if(accidentes == null)
		{
			return "No existen accidentes en esta fecha";
		}
		int contador1 =0;
		int contador2 =0;
		int contador3 =0;
		int contador4 =0;
		for(int i =0; i < accidentes.size(); i++)
		{
			Accidente accidente = (Accidente)accidentes.get(i);
			if(accidente.getSeveridad()==1)
			{
				contador1++;
			}
			else if(accidente.getSeveridad()==2)
			{
				contador2++;
			}
			else if(accidente.getSeveridad()==3)
			{
				contador3++;
			}
			else
			{
				contador4++;
			}
		}
		return "El total de accidentes en esta fecha es de: " + accidentes.size() + ". \n De estos accidentes, " + contador1 + 
				" fueron de severidad 1, " + contador2 + " fueron de severidad 2, " + contador3 + " fueron de severidad 3, y "
				+ contador4 + " fueron de severidad 4. \n";


	}




}
