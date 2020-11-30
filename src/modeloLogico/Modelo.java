package modeloLogico;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Edge;
import modeloEstructuraDatos.TablaHashLinearProbing;
import modeloEstructuraDatos.Vertex;
import modeloEstructuraDatos.Viaje;
import view.View;



public class Modelo {

	public String RUTA_DATOS_1 = "./data/201801-1-citibike-tripdata.csv";

	public String RUTA_DATOS_2 = "./data/201801-2-citibike-tripdata.csv";

	public String RUTA_DATOS_3 = "./data/201801-3-citibike-tripdata.csv";

	public String RUTA_DATOS_4 = "./data/201801-4-citibike-tripdata.csv";

	private DiGraph graph;
	private ArrayList<Viaje> viajes;
	private AlgoritmoKosajaru<Integer, String> ADK;

	private FileReader archivo;
	private CSVReader lector;
	private int cantidadDeViajesBicicletaCargados;
	private View view; 

	public DiGraph <Integer, String> grafoDeCluster;
	public ArrayList<ArrayList<Edge<Integer,String>>> resultado; 
	
	public Modelo() { view = new View(); }

	public void cargarDatos(String[] lista)
	{
		try {
			cantidadDeViajesBicicletaCargados = 0;
			String ruta=" ";
			long startTime = System.nanoTime();	
			viajes = new ArrayList<Viaje>();
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
						int anoNacimiento = Integer.parseInt(linea[13]);
						double longitudInicio = Double.parseDouble(linea[6]);
						double latitudInicio = Double.parseDouble(linea[5]);
						String nombreInicio = linea[4];
						double longitudFinal = Double.parseDouble(linea[10]);
						double latitudFinal = Double.parseDouble(linea[9]);
						String nombreFinal = linea[8];
						Viaje viaje = new Viaje(duracionViaje, idInicio, idFinal, idBicicleta, latitudInicio, latitudFinal, longitudInicio, longitudFinal, fechaInicial, fechaFinal, anoNacimiento);
						viajes.add(viaje);
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
			System.out.println("El arco con peso m�nimo conecta el vertice llamado "+ arcoMin.getSource().getInfo() +" con el vertice llamado "+ arcoMin.getDest().getInfo() + " y su peso es "+ arcoMin.weight());
			System.out.println("El arco con peso m�ximo conecta el vertice llamado "+ arcoMax.getSource().getInfo() +" con el vertice llamado "+ arcoMax.getDest().getInfo() + " y su peso es "+ arcoMax.weight());
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

	public String estacionesCriticas()
	{
		int top1Llegada = 0;
		String nombreTop1Llegada = "";
		int top2Llegada = 0;
		String nombreTop2Llegada = "";
		int top3Llegada = 0;	
		String nombreTop3Llegada = "";

		int top1Salida = 0;
		String nombreTop1Salida = "";
		int top2Salida = 0;
		String nombreTop2Salida = "";
		int top3Salida = 0;	
		String nombreTop3Salida = "";

		int menorUtilizada1 = Integer.MAX_VALUE;
		int menorUtilizada2 = Integer.MAX_VALUE;
		int menorUtilizada3 = Integer.MAX_VALUE;
		String nombreMenorUtilizada1 = ""; 
		String nombreMenorUtilizada2 = "";
		String nombreMenorUtilizada3 = "";

		ArrayList<Vertex<Integer, String>> listaVertices = (ArrayList) graph.vertices();
		long startTime = System.nanoTime();
		for (Vertex<Integer, String> vertex : listaVertices) {
			String nombre = vertex.getInfo();

			int llegada = 0;
			for (Viaje viaje : viajes) 
			{
				if(viaje.getIdFinal() == vertex.getId())
				{
					llegada++;
				}
			}
			vertex.setViajesLlegando(llegada);
			int salida = vertex.getViajesSaliendo();
			int suma = llegada + salida;
			if(llegada>top3Llegada) {
				if(llegada > top1Llegada)
				{
					top3Llegada = top2Llegada;
					nombreTop3Llegada = nombreTop2Llegada;
					top2Llegada = top1Llegada;
					nombreTop2Llegada = nombreTop1Llegada;
					top1Llegada = llegada;
					nombreTop1Llegada = nombre;
				}
				else if(llegada > top2Llegada)
				{
					top3Llegada = top2Llegada;
					nombreTop3Llegada = nombreTop2Llegada;
					top2Llegada = llegada;
					nombreTop2Llegada = nombre;
				}
				else
				{
					top3Llegada = llegada;
					nombreTop3Llegada = nombre;
				}
			}
			if(salida > top3Salida)	
			{
				if(salida > top1Salida)
				{
					top3Salida = top2Salida;
					nombreTop3Salida = nombreTop2Salida;
					top2Salida = top1Salida;
					nombreTop2Salida = nombreTop1Salida;
					top1Salida = salida;
					nombreTop1Salida = nombre;
				}
				else if(salida > top2Salida)
				{
					top3Salida = top2Salida;
					nombreTop3Salida = nombreTop2Salida;
					top2Salida = salida;
					nombreTop2Salida = nombre;
				}
				else
				{
					top3Salida = salida;
					nombreTop3Salida = nombre;
				}
			}
			if(suma < menorUtilizada3)
			{
				if(suma<menorUtilizada1)
				{
					menorUtilizada3 = menorUtilizada2;
					nombreMenorUtilizada3 = nombreMenorUtilizada2;
					menorUtilizada2 = menorUtilizada1;
					nombreMenorUtilizada2 = nombreMenorUtilizada1;
					menorUtilizada1 = suma;
					nombreMenorUtilizada1 = nombre;
				}
				else if(suma < menorUtilizada2)
				{
					menorUtilizada3 = menorUtilizada2;
					nombreMenorUtilizada3 = nombreMenorUtilizada2;
					menorUtilizada2 = suma;
					nombreMenorUtilizada2 = nombre;
				}
				else
				{
					menorUtilizada3 = suma;
					nombreMenorUtilizada3 = nombre;
				}
			}
		}
		long endTime = System.nanoTime();
		return "Las 3 estaciones con mas llegadas son:\n 1." + nombreTop1Llegada + " Con " + top1Llegada  + "\n 2."+ nombreTop2Llegada + " Con " + top2Llegada + "\n 3."+ nombreTop3Llegada + " Con " + top3Llegada  + ".\n\n "
				+ "Las 3 estaciones con mas salidas son:\n 1." + nombreTop1Salida + " Con " + top1Salida + "\n 2."+ nombreTop2Salida + " Con " + top2Salida  +"\n 3."+ nombreTop3Salida + " Con " + top3Salida + ".\n\n "
				+ "Las 3 estaciones menos utilizadas son:\n 1." + nombreMenorUtilizada1 + " Con " + menorUtilizada1 + "\n 2."+ nombreMenorUtilizada2 + " Con " + menorUtilizada2  + "\n 3."+ nombreMenorUtilizada3 + " Con " + menorUtilizada1 +  ".\n\n "
				+ "\nTiempo del requerimiento "+ (endTime-startTime)/1e6 + " ms\n";
	}
	
	/**
	 * 
	 * @param rango, siendo 1.0-10, 2.11-20, 3.21-30, 4.31-40, 5.41-50, 6.51-60, 7.60+
	 * @return La respuesta de la ruta ideal para la edad en el rango que llega por parametro
	 */
	public String recomendadorDeRutas(int rango)
	{
		int anoActual = 2020;
		ArrayList<Viaje> viajesEnRango = viajesEnRangoEdad(rango, anoActual);
		TablaHashLinearProbing<Integer, Integer> tablaOrigen = new TablaHashLinearProbing<>(50);
		TablaHashLinearProbing<Integer, Integer> tablaDestino = new TablaHashLinearProbing<>(50);
		int idMaximoOrigen = 0;
		int numMaxViajesOrigen = 0;
		int idMaximoDestino = 0;
		int numMaxViajesDestino = 0;
		long startTime = System.nanoTime();
		for (Viaje viaje : viajesEnRango) {
			int numViajesOrigen = 0;
			int numViajesDestino = 0;
			if(tablaOrigen.get(viaje.getIdInicio())==null)
			{
				tablaOrigen.put(viaje.getIdInicio(), 0);
			for (Viaje viaje2 : viajesEnRango) {
				if(viaje.getIdInicio()==viaje2.getIdInicio()) {numViajesOrigen++;}
			}
			if(numViajesOrigen>numMaxViajesOrigen) 
			{	numMaxViajesOrigen = numViajesOrigen; 
				idMaximoOrigen = viaje.getIdInicio();}
			}
			if(tablaDestino.get(viaje.getIdFinal())==null)
			{
				tablaDestino.put(viaje.getIdFinal(), 0);
			for (Viaje viaje2 : viajesEnRango) {
				if(viaje.getIdFinal()==viaje2.getIdFinal()) {numViajesDestino++;}
			}
			if(numViajesDestino>numMaxViajesDestino) 
			{	numMaxViajesDestino = numViajesDestino; 
				idMaximoDestino = viaje.getIdFinal();}
			}
		}
		AlgoritmoJohnson ADJ = new AlgoritmoJohnson();
		List <List<Vertex<Integer,String>>> listaDeCaminos = ADJ.cycles(graph, graph.getVertex(idMaximoOrigen), graph.getVertex(idMaximoDestino));
		if(listaDeCaminos.size()==0)
		{
			return "No existe ningun camino entre las estaciones que mas usan las personas en el rango de edad indicado" ;
		}
		double duracionMinima = Integer.MAX_VALUE;
		List<Vertex<Integer, String>> listaMinima = null;
		for (List<Vertex<Integer, String>> list : listaDeCaminos) {
			double duracionCamino = 0;
			for (int i =0;i<(list.size()-1);i++ ) {
				List<Edge<Integer, String>> listaArcos = list.get(i).edges();
				boolean encontro = false;
				for(int j =0;j<listaArcos.size()&&!encontro;j++)
				{
					if(listaArcos.get(j).getDest().equals(list.get(i+1)))
					{
						duracionCamino += listaArcos.get(j).weight();
						encontro = true;
					}
				}
			}
			if(duracionCamino<duracionMinima)
			{
				duracionMinima = duracionCamino;
				listaMinima = list;
			}
		}
		String resp = "";
		for(int i =(listaMinima.size()-1); i>=0;i--)
		{
			resp += listaMinima.get(i).getInfo() + "\n";
		}
		long endTime = System.nanoTime();
		resp += "\n La duracion de este recorrido es de " + duracionMinima;
		resp += "\nTiempo que tardo el requerimiento: " + (endTime-startTime)/1e6 + " ms\n";
		listaDeCaminos=null;
		listaMinima=null;
		viajesEnRango = null;
		tablaDestino = null;
		tablaOrigen = null;
		return "La ruta de inicio es: " + graph.getVertex(idMaximoOrigen).getInfo() +"\n"
				+ "La ruta final es: " + graph.getVertex(idMaximoDestino).getInfo() + "\n"
				+ "La lista de estaciones en la ruta es: \n" + resp;
	}
	public ArrayList<Viaje> viajesEnRangoEdad(int rango, int anoPresente)
	{
		ArrayList<Viaje> enRango = new ArrayList<Viaje>();
		for (Viaje viaje : viajes) {
			int edad = anoPresente-viaje.getAnoNacimiento();
			if(rango == 1 )
			{
				if(edad>=0 && edad<=10){enRango.add(viaje);}
			}
			else if(rango ==2)
			{
				if(edad>10 && edad<21){enRango.add(viaje);}
			}
			else if(rango ==3)
			{
				if(edad>20 && edad<31){enRango.add(viaje);}
			}
			else if(rango ==4)
			{
				if(edad>30 && edad<41){enRango.add(viaje);}
			}
			else if(rango ==5)
			{
				if(edad>40 && edad<51){enRango.add(viaje);}
			}
			else if(rango ==6)
			{
				if(edad>50 && edad<61){enRango.add(viaje);}
			}
			else
			{
				if(edad>60){enRango.add(viaje);}
			}
		}
		
		return enRango;
		
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

	
	public String rutaTuristicaPorResistencia(double tiempoDisponible, int idEstacionInicio) {
		long startTime = System.nanoTime();	
		grafoDeCluster = ADK.formarGrafoParaCluster(ADK.darClusterDe(idEstacionInicio));
		Set<Integer> visitado = new HashSet<Integer>(grafoDeCluster.numEdges());
		resultado = new ArrayList<ArrayList<Edge<Integer,String>>>();
		
		ArrayList<Edge<Integer, String>> primeraRuta = new ArrayList<Edge<Integer,String>>();
		rutaTuristicaPorResistenciaRecursivo(tiempoDisponible, idEstacionInicio,primeraRuta,visitado);
		
		String rta = "Ruta turistica por resistencia\n";
		int contador = 1;
		for (ArrayList<Edge<Integer, String>> ruta : resultado) {
			rta += "Ruta # "+ contador++ +"\n";
			for (Edge<Integer, String> edge : ruta) {
				rta += "\tEstacion Inicio "+ edge.getSource().getInfo() + ", estacion final "+ edge.getDest().getInfo() + ". duracion estimada de segmento "+ edge.weight() + " segundos\n";
			}
		}
		long endTime = System.nanoTime();
		rta += "\nTiempo que tardo el requierimiento: " + (endTime-startTime)/1e6 + " ms\n";
		return rta;
		
	}
	
	public void rutaTuristicaPorResistenciaRecursivo(double tiempoDisponible, int idEstacionInicio, ArrayList<Edge<Integer,String>> ruta, Set<Integer> visitadoHastaAhora) {
//		 ArrayList<Edge<Integer,String>> ruta = new ArrayList<Edge<Integer,String>>(rutaHastaAhora);
		 Set<Integer> visitado = new HashSet<Integer>(visitadoHastaAhora);
		visitado.add(idEstacionInicio);
//		for (Edge<Integer, String> edge : grafoDeCluster.adjacentEdges(idEstacionInicio)) {
//			System.out.println(edge.getSource().getId()+" "+ edge.getDest().getId() +" " + edge.weight() +"\n");
//			}
		boolean esFinal = false;
		for (Edge<Integer, String> edge : grafoDeCluster.adjacentEdges(idEstacionInicio)) {
//			System.out.println(idEstacionInicio);
			if (visitado.contains(edge.getDest().getId())) {
				esFinal = true;
				continue;
			}
			
			double pesoRuta = 0.0;
			for (Edge<Integer, String> edge2 : ruta) {
				pesoRuta+= edge2.weight();
			}
			if (pesoRuta+edge.weight()>tiempoDisponible*60) {
//				System.out.println("\t"+edge.getDest().getId() + edge.weight());
				esFinal = true;
				continue;
			}
			 ArrayList<Edge<Integer,String>> rutaNueva = new ArrayList<Edge<Integer,String>> (ruta);
			rutaNueva.add(edge);
			rutaTuristicaPorResistenciaRecursivo(tiempoDisponible, edge.getDest().getId(),rutaNueva,visitado);
			
		}
		if (esFinal && !ruta.isEmpty()) {
			resultado.add(ruta);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	public String rutaTuristicaPorResistenciaJohnson(int tiempoDisponible, int idEstacionInicio) {
		String rta = "";
		long startTime = System.nanoTime();	
		DiGraph <Integer, String> grafoDeCluster = ADK.formarGrafoParaCluster(ADK.darClusterDe(idEstacionInicio));


		AlgoritmoJohnson ADJ = new AlgoritmoJohnson();
		List <List<Vertex<Integer,String>>> listaDeListas = ADJ.simpleCyles(grafoDeCluster, grafoDeCluster.getVertex(idEstacionInicio));
		double[] tiempoParaCadaRuta = new double[listaDeListas.size()];
		for(int i =0; i<listaDeListas.size();i++) {
			List<Vertex<Integer,String>> listaActual = listaDeListas.get(i);
			double tiempoRuta = 0;
			for(int j = 1; j<listaActual.size();j++) {
				tiempoRuta += grafoDeCluster.getVertex(listaActual.get(j-1).getId()).getEdge(listaActual.get(j).getId()).weight();
			}
			tiempoRuta += ((listaActual.size()-1)*20);
			tiempoParaCadaRuta[i] = tiempoRuta;
		}
		ArrayList<Integer> viajesFiltrados = new ArrayList<Integer>();
		for(int i = 0; i<tiempoParaCadaRuta.length;i++) if(tiempoParaCadaRuta[i]<=tiempoDisponible) viajesFiltrados.add(i);

		if(viajesFiltrados.isEmpty()) {
			int tiempoMaxRuta = (int)tiempoParaCadaRuta[0];
			for (double doubleeact : tiempoParaCadaRuta) {
				int intAct = (int)doubleeact;
				if(intAct>tiempoMaxRuta) tiempoMaxRuta = intAct;
			}
			return "\nFue posible establecer " + tiempoParaCadaRuta.length +" rutas" ;
		}
		rta += "\nSe han encontrado " + viajesFiltrados.size() + " viajes circulares que parten y finalizan en la estacion con ID \"" + idEstacionInicio + "\"";
		rta += "\ny que ademas tienen una duracion maxima de " + tiempoDisponible + " minutos.";
		//		rta += "\n(Se imprimieron "+ cantidadDeOpcionesAImprimir+" opciones)";
		System.out.println("\n\n      Rutas circulares disponibles:\n");
		int cont = 1;
		for (Integer integer : viajesFiltrados) {
			//			if(cont>cantidadDeOpcionesAImprimir) break;
			List<Vertex<Integer,String>> listaActual = listaDeListas.get(integer);
			System.out.println("     Opcion " + cont + ":");
			for(int j = 1; j<listaActual.size();j++) {
				System.out.println("         Segmento "+ j + ": " + grafoDeCluster.getVertex(listaActual.get(j-1).getId()).getInfo() + " (ID:"+listaActual.get(j-1).getId() + ") -> " + 
						grafoDeCluster.getVertex(listaActual.get(j).getId()).getInfo() + " (ID:" + listaActual.get(j).getId() 
						+ ") --- aprox " + (int)grafoDeCluster.getVertex(listaActual.get(j-1).getId()).getEdge(listaActual.get(j).getId()).weight() + " minutos trayecto + 20 minutos de parada");
			}
			System.out.println("");
			cont++;
		}
		long endTime = System.nanoTime();
		rta += "\nTiempo que tardo el requierimiento: " + (endTime-startTime)/1e6 + " ms\n";
		return rta;
	}

	
	
	
	
	public String rutaDeInteresTuristico(double longitudActual, double latitudActual, double longitudDestino, double latitudDestino) {

		double longitudActualPersona = longitudActual;
		double latitudActualPersona = latitudActual;
		double longitudDestinoPersona = latitudActual;
		double latitudDestinoPersona = latitudDestino;
		double longitudEstacion = 0.0;
		double latitudEstacion = 0.0;
		double distanciaNueva = -1;
		double distanciaVieja = -1;	
		double distanciaNuevaFin = -1;
		double distanciaViejaFin = -1;
		
		int idEstacionSalida = 0;
		int idEstacionLlegada = 0;
			
		String nombreEstacionSalida = "";
		String nombreEstacionLlegada = "";

		

		ArrayList<Vertex<Integer, String>> listaVertices = (ArrayList) graph.vertices();
		long startTime = System.nanoTime();
		for (Vertex<Integer, String> vertex : listaVertices) {
			
			
			for (Viaje viaje : viajes) 
			{
				latitudEstacion = viaje.getLatitudInicio();
				longitudEstacion = viaje.getLongitudInicio();
				
				distanciaNueva = Math.sqrt(Math.pow((longitudEstacion-longitudActualPersona), 2)+Math.pow((latitudEstacion-latitudActualPersona),2));
				
				if (distanciaNueva<distanciaVieja) {
					idEstacionSalida = viaje.getIdInicio();
				}
				distanciaVieja = distanciaNueva;
				distanciaNueva = -1;
			}
			for (Viaje viaje : viajes) 
			{
				latitudEstacion = viaje.getLatitudInicio();
				longitudEstacion = viaje.getLongitudInicio();
				
				distanciaNuevaFin = Math.sqrt(Math.pow((longitudEstacion-longitudDestinoPersona), 2)+Math.pow((latitudEstacion-latitudDestinoPersona),2));
				
				if (distanciaNuevaFin<distanciaViejaFin) {
					idEstacionLlegada = viaje.getIdInicio();
				}
				distanciaViejaFin = distanciaNuevaFin;
				distanciaNuevaFin = -1;
			}
		}
		for (Vertex<Integer, String> vertex : listaVertices) {
			String nombre = vertex.getInfo();
			if (idEstacionSalida==vertex.getId()) {
				nombreEstacionSalida = vertex.getInfo();
			}
		}
		for (Vertex<Integer, String> vertex : listaVertices) {
			String nombre = vertex.getInfo();
			if (idEstacionLlegada==vertex.getId()) {
				nombreEstacionLlegada = vertex.getInfo();
			}
		}
		long endTime = System.nanoTime();
		return "La estaci�n de inicio m�s cercada es: \n"+nombreEstacionSalida +"\n La estaci�n mas cercana al lugar turistico es: \n " +nombreEstacionLlegada + "\nTiempo del requerimiento "+ (endTime-startTime)/1e6 + " ms\n";
	}
}

