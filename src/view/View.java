package view;

import modeloLogico.Modelo;

public class View {
	
	public View()
	{
		
	}
	
	public void printMenu()
	{
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("1. Conocer los accidentes en una fecha (G-LAB REQ1)");
		System.out.println("2. Conocer los accidentes anteriores a una fecha(I REQ2)");
		System.out.println("3. Conocer los accidentes en un rango de fechas(I REQ3)");
		System.out.println("4. Conocer el estado con mas accidentes (I REQ4)");
		System.out.println("5. Conocer los accidentes por rango de horas (G REQ5)");
		System.out.println("6. Conocer la zona geográfica mas accidentada (B REQ1)");
		System.out.println("7. Usar el conjunto completo de datos (B REQ1)");
		System.out.println("8. Informacion de creadores");
		System.out.println("9. Cambiar datos a cargar");
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
	
	public void printCambiarDatosACargar() {
		System.out.println("\n1. Cargar datos de peliculas pequenos.");
		System.out.println("2. Cargar datos de peliculas grandes.");
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
