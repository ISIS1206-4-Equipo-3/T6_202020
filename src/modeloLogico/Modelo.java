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

	private DiGraph graph;
	private AlgoritmoKosajaru<Integer, String> ADK;

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
				if (numero.equals("1")) ruta = RUTA_DATOS_1;
				else if (numero.equals("2")) ruta = RUTA_DATOS_2;
				else if (numero.equals("3")) ruta = RUTA_DATOS_3;
				else if (numero.equals("4")) ruta = RUTA_DATOS_4;
				else {
					view.printError("No se a identificado el caracter \"" + numero + "\" la carga de datos ha fallado y se ha detenido.");
					graph = null;
					return;
				}

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
							view.printMessage("Ha existido un error anadiendo el viaje que inicia en la estaci�n con id " +idInicio);

						}
						int duracionViaje = Integer.parseInt(linea[0]);
						int idFinal = Integer.parseInt(linea[7]);
						int idBicicleta = Integer.parseInt(linea[11]);
						double longitudInicio = Double.parseDouble(linea[6]);
						double latitudInicio = Double.parseDouble(linea[5]);
						String nombreInicio = linea[4];
						double longitudFinal = Double.parseDouble(linea[10]);
						double latitudFinal = Double.parseDouble(linea[9]);
						String nombreFinal = linea[8];
						Viaje viaje = new Viaje(duracionViaje, idInicio, idFinal, idBicicleta, latitudInicio, latitudFinal, longitudInicio, longitudFinal, fechaInicial, fechaFinal);
						if (!graph.containsVertex(idInicio)) {
							graph.insertVertex(idInicio, nombreInicio);
						}
						if (!graph.containsVertex(idFinal)) {
							graph.insertVertex(idFinal, nombreFinal);
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
			System.out.println("El arco con peso m�nimo conecta el vertice con id "+ arcoMin.getSource().getInfo() +" con el vertice con id "+ arcoMin.getDest().getInfo() + " y su peso es "+ arcoMin.weight());
			System.out.println("El arco con peso m�ximo conecta el vertice con id "+ arcoMax.getSource().getInfo() +" con el vertice con id "+ arcoMax.getDest().getInfo() + " y su peso es "+ arcoMax.weight());
			System.out.println("Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n");
			ADK = new AlgoritmoKosajaru<Integer, String>(graph);
		}
			catch (Exception e) {
				e.printStackTrace();
			}	
		
	}
	public String gradoEntradaSalida(int id)
	{
		long startTime = System.nanoTime();	
		Vertex vertice = graph.getVertex(id);
		if(vertice == null)
			return "Esta estacion no existe";
		long endTime = System.nanoTime();
		return "El grado de entrada de la estacion " + id + " es: " + vertice.indegree() + ", el de salida es: " + vertice.outdegree() + "\nTiempo de la carga "+ (endTime-startTime)/1e6 + " ms\n";	}
	
	public DiGraph darDiGraph() {
		return graph;
	}
	
	public String cantidadDeClusteresREQ1(int id1, int id2) {
		String rta = "";
		if(graph.getVertex(id1)==null) return "   ++CAUTION: El id de la estacion " + id1 + " no se encuentra en la base de datos.";
		if(graph.getVertex(id2)==null) return "   ++CAUTION: El id de la estacion " + id2 + " no se encuentra en la base de datos.";
		long startTime = System.nanoTime();	
		rta += "\nEn total se tienen " + ADK.cantidadDeClusters() + " componentes fuertemente conectados.\n";
		rta += "Las estaciones con ID \"" + id1 + "\" e ID \""+ id2 + "\"";
		if (!ADK.fuertementeConectados(id1, id2)) rta +=" NO";
		rta += " estan fuertemente conectadas.\n";
		long endTime = System.nanoTime();
		rta += "\nTiempo que tardo el requierimiento: " + (endTime-startTime)/1e6 + " ms\n";
		return rta;
	}
	
	
	public String rutaTuristicaCircularREQ2(int minutosMin, int minutosMax, int idEstacion, Integer cantidadDeOpcionesAImprimir) {
		String rta = "";
		long startTime = System.nanoTime();	
		System.out.println("cluster a buscar : " + ADK.darClusterDe(idEstacion));
		DiGraph <Integer, String> graphoDeCluster = ADK.formarGrafoParaCluster(ADK.darClusterDe(idEstacion));
		//ADK.imprimirResultados();
		System.out.println("El cluster contiene las estaciones: " + ADK.darIDsEnCluster(ADK.darClusterDe(idEstacion)));
		System.out.println("Cargando...");
		if(graphoDeCluster.numVertices()<2) return "\nNo es posible establecer una ruta circular en esa estacion debido a que existen muy pocos vertices adyacentes a el\n";
		AlgoritmoJohnson ADJ = new AlgoritmoJohnson();
		List <List<Vertex<Integer,String>>> listaDeListas = ADJ.simpleCyles(graphoDeCluster, graphoDeCluster.getVertex(idEstacion));
		double[] tiempoParaCadaRuta = new double[listaDeListas.size()];
		for(int i =0; i<listaDeListas.size();i++) {
			List<Vertex<Integer,String>> listaActual = listaDeListas.get(i);
			double tiempoRuta = 0;
			for(int j = 1; j<listaActual.size();j++) {
				tiempoRuta += graphoDeCluster.getVertex(listaActual.get(j-1).getId()).getEdge(listaActual.get(j).getId()).weight();
			}
			tiempoRuta += ((listaActual.size()-1)*20);
			tiempoParaCadaRuta[i] = tiempoRuta;
		}
		ArrayList<Integer> viajesFiltrados = new ArrayList<Integer>();
		for(int i = 0; i<tiempoParaCadaRuta.length;i++) if(tiempoParaCadaRuta[i]>=minutosMin  && tiempoParaCadaRuta[i]<=minutosMax) viajesFiltrados.add(i);
		
		if(viajesFiltrados.isEmpty()) {
			int tiempoMinRuta = (int)tiempoParaCadaRuta[0];
			int tiempoMaxRuta = (int)tiempoParaCadaRuta[0];
			for (double doubleeact : tiempoParaCadaRuta) {
				int intAct = (int)doubleeact;
				if(intAct<tiempoMinRuta) tiempoMinRuta = intAct;
				if(intAct>tiempoMaxRuta) tiempoMaxRuta = intAct;
			}
			return "\nFue posible establecer " + tiempoParaCadaRuta.length +" rutas circulares, pero no se pudieron a encontrar en ese rango de tiempo\n" 
			 +"Se encontraron con un minimo de " + tiempoMinRuta + " minutos y un maximo de " + tiempoMaxRuta + " minutos." ;
		}
		rta += "\nSe han encontrado " + viajesFiltrados.size() + " viajes circulares que parten y finalizan en la estacion con ID \"" + idEstacion + "\"";
		rta += "\ny que ademas tienen una duracion media entre " + minutosMin + " minutos y " + minutosMax + " minutos.";
		rta += "\n(Se imprimieron "+ cantidadDeOpcionesAImprimir+" opciones)";
		System.out.println("\n\n      Rutas circulares disponibles:\n");
		int cont = 1;
		for (Integer integer : viajesFiltrados) {
			if(cont>cantidadDeOpcionesAImprimir) break;
			List<Vertex<Integer,String>> listaActual = listaDeListas.get(integer);
			System.out.println("     Opcion " + cont + ":");
			for(int j = 1; j<listaActual.size();j++) {
				System.out.println("         Segmento "+ j + ": " + graphoDeCluster.getVertex(listaActual.get(j-1).getId()).getInfo() + " (ID:"+listaActual.get(j-1).getId() + ") -> " + 
				graphoDeCluster.getVertex(listaActual.get(j).getId()).getInfo() + " (ID:" + listaActual.get(j).getId() 
				+ ") --- aprox " + (int)graphoDeCluster.getVertex(listaActual.get(j-1).getId()).getEdge(listaActual.get(j).getId()).weight() + " minutos trayecto + 20 minutos de parada");
			}
			System.out.println("");
			cont++;
		}
		long endTime = System.nanoTime();
		rta += "\nTiempo que tardo el requierimiento: " + (endTime-startTime)/1e6 + " ms\n";
		return rta;
	}
}

