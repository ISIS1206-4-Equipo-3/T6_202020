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

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Edge;
import modeloEstructuraDatos.Vertex;
import modeloEstructuraDatos.Viaje;
import view.View;



public class Modelo {

	public String RUTA_DATOS_1 = "./data/201801-1-citibike-tripdata.csv";

	public String RUTA_DATOS_2 = "./data/201801-2-citibike-tripdata.csv";

	public String RUTA_DATOS_3 = "./data/201801-3-citibike-tripdata.csv";

	public String RUTA_DATOS_4 = "./data/201801-4-citibike-tripdata.csv";

	public DiGraph graph;

	private FileReader archivo;
	private CSVReader lector;
	private int cantidadDeViajesBicicletaCargados;
	private View view; 


	public Modelo() { view = new View(); }

	public void cargarDatos(String[] lista)
	{
		try {
			cantidadDeViajesBicicletaCargados = 0;
			String ruta=" ";
			long startTime = System.nanoTime();	
			graph =  new DiGraph <Integer, String>();
			for (String numero : lista) {
				if (numero.equals("1")) 
					ruta = RUTA_DATOS_1;
				if (numero.equals("2")) 
					ruta = RUTA_DATOS_2;
				if (numero.equals("3")) 
					ruta = RUTA_DATOS_3;
				if (numero.equals("4")) 
					ruta = RUTA_DATOS_4;

				CSVParser parser = new CSVParserBuilder().withSeparator(',').build();   
				archivo = new FileReader(ruta);
				lector = new CSVReaderBuilder (archivo).withCSVParser(parser).build();
				String [] linea = lector.readNext();
				while(((linea = lector.readNext())!=null)){
					if(linea != null ) {
						String[] fechaInicio = linea[1].split(" ");
						String[] fechaFin = linea[2].split(" ");
						String[] fechaJusta = fechaInicio[0].split("-");
						String[] fechaJusta2 = fechaFin[0].split("-");
						Date fechaInicial = new Date();
						Date fechaFinal = new Date();
						int idInicio = Integer.parseInt(linea[3]);
						try
						{
							DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							fechaInicial = formato.parse(fechaJusta[0] + "-" + fechaJusta[1] + "-" + fechaJusta[2] + " " + fechaInicio[1]);
							fechaFinal = formato.parse(fechaJusta2[0] + "-" + fechaJusta2[1] + "-" + fechaJusta2[2] + " " + fechaFin[1]);
						}
						catch (Exception e)
						{
							fechaInicial = new Date(0, 0, 0);
							fechaFinal = new Date(0, 0, 0);
							view.printMessage("Ha existido un error anadiendo el viaje que inicia en la estación con id " +idInicio);

						}
						int duracionViaje = Integer.parseInt(linea[0]);
						int idFinal = Integer.parseInt(linea[7]);
						int idBicicleta = Integer.parseInt(linea[11]);
						double longitudInicio = Double.parseDouble(linea[6]);
						double latitudInicio = Double.parseDouble(linea[5]);
						double longitudFinal = Double.parseDouble(linea[10]);
						double latitudFinal = Double.parseDouble(linea[9]);
						Viaje viaje = new Viaje(duracionViaje, idInicio, idFinal, idBicicleta, latitudInicio, latitudFinal, longitudInicio, longitudFinal, fechaInicial, fechaFinal);
						if (!graph.containsVertex(idInicio)) {
							graph.insertVertex(idInicio, idInicio);
						}
						if (!graph.containsVertex(idFinal)) {
							graph.insertVertex(idFinal, idFinal);
						}
						graph.addEdge(idInicio, idFinal, duracionViaje);

						++cantidadDeViajesBicicletaCargados;
					}
				}
			}
			long endTime = System.nanoTime();
			ArrayList<Edge<Integer,String>> arcos = (ArrayList<Edge<Integer, String>>) graph.edges();
			double pesoMin = Integer.MAX_VALUE;
			double pesoMax = 0.0;
			Edge arcoMax = null;
			Edge arcoMin = null;
			for (int i = 0; i < arcos.size(); i++) {
				if (arcos.get(i).weight()<pesoMin) {
					pesoMin = arcos.get(i).weight();
					arcoMin = arcos.get(i);
				}
			}
			for (int i = 0; i < arcos.size(); i++) {
				if (arcos.get(i).weight()>pesoMax) {
					pesoMax = arcos.get(i).weight();
					arcoMax = arcos.get(i);
				}
			}
			
			System.out.println("-------- Los datos fueron cargados correctamente --------\n");
			System.out.println("Se cargaron: "+cantidadDeViajesBicicletaCargados+" viajes\n");
			System.out.println("Son "+graph.vertices().size()+" estaciones\n");
			System.out.println("Son "+ arcos.size() +" arcos entre estaciones\n");
			System.out.println("El arco con peso mínimo conecta el vertice con id "+ arcoMin.getSource().getInfo() +" con el vertice con id "+ arcoMin.getDest().getInfo() + " y su peso es "+ arcoMin.weight());
			System.out.println("El arco con peso máximo conecta el vertice con id "+ arcoMax.getSource().getInfo() +" con el vertice con id "+ arcoMax.getDest().getInfo() + " y su peso es "+ arcoMax.weight());
			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");
		}
			catch (Exception e) {
				e.printStackTrace();
			}	
		
	}
	public String gradoEntradaSalida(int id)
	{
		Vertex vertice = graph.getVertex(id);
		if(vertice == null)
			return "Esta estacion no existe";
					
		return "El grado de entrada de la estacion " + id + " es: " + vertice.indegree() + ", el de salida es: " + vertice.outdegree();	}
	
	public DiGraph darDiGraph() {
		return graph;
	}
}

