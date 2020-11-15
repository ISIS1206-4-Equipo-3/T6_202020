package view;

import modeloLogico.Modelo;

public class View {
	
	public View()
	{
		
	}
	
	public void printMenu()
	{
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("1. Cantidad de cluster de Viajes (REQ. 1:G)");
		System.out.println("2. Ruta turística Circular (REQ. 2:I)");
		System.out.println("3. Ruta turística de menor tiempo (REQ. 3:I)");
		System.out.println("4. Ruta turística por resistencia (REQ. 4:I)");
		System.out.println("5. Ruta mas corta entre estaciones (Req. 5:G)");
		System.out.println("6. Ruta de interés turístico (REQ. 6:G)");
		System.out.println("7. Identificación de Estaciones para Publicidad (REQ. 7:B)");
		System.out.println("8. Identificación de bicicletas para mantenimiento (REQ. 8:B)");
		System.out.println("9. Informacion de creadores");
		System.out.println("10. Cargar datos ");
		System.out.println("\n0. <-----EXIT");
		
		
	}

	public void printError(String mensajeDeError) {

		System.out.println("\n++CAUTION: " + mensajeDeError+ "\n");
	}
	
	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	
	public void printInformacionDeCreadores(){
		System.out.println("\nCreado por:");
		System.out.println("Alejandro Alcaraz 201921767");
		System.out.println("Alejandro Ahogado 201920701");
		System.out.println("Santiago Triana 201923265");
		System.out.println("Universidad de los Andes - Bogota, Colombia");
		System.out.println("Estructuras de Datos 202020_ISIS_1206_04 GRUPO 3\n");
	}

	
	public void printErrorConRangoDeEntrada() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha introducido un numero que no esta dentro del menu. Opcion invalida \n");
	}
	
	public void printErrorConNumeroDeEntrada() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha introducido un caracter diferente a un numero. Opcion invalida \n");
	}
	public void printErrorDesconocido() {
		// TODO Auto-generated method stub
		System.out.println("\n++CAUTION: Ha sucedido un error desconocido \n");
	}

	public void printDespedida() {
		// TODO Auto-generated method stub
		printInformacionDeCreadores();
		System.out.println("\nGracias por usar nuestra base de datos :)");
	}

	

}
