package modeloLogico;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import view.View;



public class Modelo {
	
	public String RUTA_DATOS_1 = "./data/201801-1-citibike-tripdata.csv";
	
	public String RUTA_DATOS_2 = "./data/201801-2-citibike-tripdata.csv";
	

	private FileReader archivo1;
	private FileReader archivo2;
	private CSVReader lector;
	private int cantidadDeViajesBicicletaCargados;
	private View view; 
	
	public Modelo() { view = new View(); }
	
	public void cargarDatos(String ruta1, String ruta2 )
	{
		try {
			cantidadDeViajesBicicletaCargados = 0;
			long startTime = System.nanoTime();
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//			tabla = DiGraph new <idEstacion, Viaje>();   *corregir*
			archivo1 = new FileReader(ruta1);
			archivo2 = new FileReader(ruta2);
			lector = new CSVReaderBuilder (archivo1).withCSVParser(parser).build();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
