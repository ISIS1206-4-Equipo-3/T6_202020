package controller;

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
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 2:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 3:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 4:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 5:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 6:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 7:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 8:
					view.printError("Req aun no realizado");//REQUERERIMIENTO AUN NO REALIZADO (Borrar al realizar)
					break;
				case 9:
					view.printInformacionDeCreadores();
					break;
				case 10:
					view.printMessage("Escriba el numero de los archivos que desea cargar (separados por coma)");
					String rutasreq10 = lectura.nextLine();
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
}
