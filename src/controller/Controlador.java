package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modeloLogico.Modelo;
import view.View;

public class Controlador {
	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	public Controlador()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run()
	{
		Scanner lectura = new Scanner(System.in);
		boolean acabar = false;
		Integer dato = null;
		Comparable rta = " ";
		Comparable respuesta = " ";
		String rtaPeli = " ";

		while(!acabar)
		{
			try {
				view.printMenu();
				int opcion = Integer.parseInt(lectura.nextLine());
				switch(opcion) {
				
				case 1:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					view.printMessage("Escriba dos ID's de estaciones para saber si estas estan fuertemente conectadas (separados por coma).");
					String idsreq1 = lectura.nextLine();
					String[] idsreq1array = idsreq1.split(",");
					if(idsreq1array.length != 2) {view.printError("Se han introducido mas de 2 ID's"); break;}
					view.printMessage(modelo.cantidadDeClusteresREQ1(Integer.parseInt(idsreq1array[0].trim()),Integer.parseInt(idsreq1array[1].trim())));
					break;
				case 2:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					
					view.printMessage("Escriba el rango de tiempo disponible para el recorrido (en minutos separado con \"-\")");
					view.printMessage("   ej: 180-220");
					String tiemposreq2 = lectura.nextLine();
					view.printMessage("Escriba el ID de la estacion de partida y llegada");
					String idEstacionPartidaREQ2 = lectura.nextLine();
					String[] tiemposreq2array = tiemposreq2.split("-");
					if(tiemposreq2array.length != 2) {view.printError("Se han introducido mal el formato de tiempos"); break;}
					if(modelo.darDiGraph().getVertex(Integer.parseInt(idEstacionPartidaREQ2.trim()))==null) {view.printError("No existe una estacion con ese ID"); break;}
					view.printMessage("Â¿Cuantas opciones de viajes circulares desea conocer?");
					Integer cantidadDeOpcionesAImprimir = Integer.parseInt(lectura.nextLine());
					view.printMessage(modelo.rutaTuristicaCircularREQ2(Integer.parseInt(tiemposreq2array[0].trim()),Integer.parseInt(tiemposreq2array[1].trim()),Integer.parseInt(idEstacionPartidaREQ2.trim()),cantidadDeOpcionesAImprimir));
					break;
				case 3:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					view.printMessage(modelo.estacionesCriticas());
					
					break;
				case 4:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}				
					view.printMessage("Indique el tiempo máximo de resistencia para el recorrido");
					String tiempoDisponible = lectura.nextLine();
					view.printMessage("Indique el ID de la estacion inicial o de salida");
					String idEstacion = lectura.nextLine();
					if(Integer.parseInt(tiempoDisponible)<=0) {view.printError("El tiempo de resistencia debe ser mayor a cero"); break;}
					if(modelo.darDiGraph().getVertex(Integer.parseInt(idEstacion.trim()))==null) {view.printError("No existe una estacion con ese ID"); break;}
					view.printMessage(modelo.rutaTuristicaPorResistencia(Integer.parseInt(tiempoDisponible.trim()),Integer.parseInt(idEstacion.trim())));
					
					
					
					break;
				case 5:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					view.printReq4();
					try
					{
					int indice = Integer.parseInt(lectura.nextLine());
					if(indice<1 || indice>7)
					{
						view.printReq4Error();
					}
					else
					{
						System.out.println(modelo.recomendadorDeRutas(indice));
					}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						view.printReq4Error();
					}
					
					break;
				case 6:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
								
					view.printMessage("Indique su longitud actual");
					String longitudActual = lectura.nextLine();
					view.printMessage("Indique su latitud actual");
					String latitudActual = lectura.nextLine();
					view.printMessage("Indique la longitud de su destino");
					String longitudDestino = lectura.nextLine();
					view.printMessage("Indique la latitud de su destino");
					String latitudDestino = lectura.nextLine();
					view.printMessage(modelo.rutaDeInteresTuristico(Double.parseDouble(longitudActual.trim()),Double.parseDouble(latitudActual.trim()),Double.parseDouble(longitudDestino.trim()),Double.parseDouble(latitudDestino.trim())));
					
					break;
				case 7:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 8:

					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 9:
					view.printInformacionDeCreadores();
					break;
				case 10:
					view.printMessage("Escriba el numero de los archivos que desea cargar (separados por coma). Para mayor informacion escriba \"?\"");
					String rutasreq10 = lectura.nextLine();
					if (rutasreq10.contains("?")) {view.printExplicacionDeCarga(); break;}
					String[] lista = rutasreq10.split(",");
					for (int i = 0; i < lista.length; i++) {
						lista[i]=lista[i].trim();
					}
					try
					{
						modelo.cargarDatos(lista);
					}
					catch (Exception e)
					{
						view.printError("No se uso el formato adecuado, por favor usar numeros del 1 al 4 separados por coma.");
					}
					break;
				case 11:
					
					if(!verificarDatosCargados()) {view.printError("Se deben cargar primero los datos (OPC.10)"); break;}
					
					view.printMessage("Escriba el id de la estacion que desea buscar");
					int id = Integer.parseInt(lectura.nextLine());
					System.out.println(modelo.gradoEntradaSalida(id));
					break;	
				case 0:
					acabar=true;
					view.printDespedida();
					break;
				default:
					view.printErrorConRangoDeEntrada();
					break;
				}

			}catch (Exception e) {
				if(e.getClass().equals(java.lang.NumberFormatException.class)) view.printErrorConNumeroDeEntrada();
				else {
					view.printErrorDesconocido();
					e.printStackTrace();

				}

			}

		}

	}
	
	private boolean verificarDatosCargados() {
		if (modelo.darDiGraph()==null) return false;
		return true;
	}
}
