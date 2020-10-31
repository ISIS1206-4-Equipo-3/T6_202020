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

import modeloEstructuraDatos.Accidente;
import modeloEstructuraDatos.BST;
import modeloEstructuraDatos.NodoTH;
import modeloEstructuraDatos.RBT;
import modeloEstructuraDatos.TablaHashSeparateChaining;
import view.View;



public class Modelo {

	public String RUTA_DATOS_PRINCIPALES = "./data/small/us_accidents_small.csv";

	private FileReader archivo;
	private CSVReader lector;
	public RBT tabla;

	private Date fechaMinima; //Este atributo hace referencia a la menor fecha creada
	private Date fechaMaxima; //Este atributo hace referencia a la mayor fecha creada
	private int cantidadDeAccidentesCargados; //Este atributo hace referencia a la cantidad de accidentes totales que fueron cargados
	private View vista;

	public Modelo () {
		vista = new View();
	}


	public void cargarDatosPorFechaInicial(String rutaPrincipal)
	{
		try {
			cantidadDeAccidentesCargados = 0;
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
					String id = linea[0];
					try
					{
						DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						fechaInicial = formato.parse(fechaJusta[0] + "-" + fechaJusta[1] + "-" + fechaJusta[2] + " " + fecha[1]);
					}
					catch (Exception e)
					{
						fechaInicial = new Date(0, 0, 0);
						vista.printMessage("Ha existido un error anadiendo el accidente con id " +id);

					}
					int severidad = Integer.parseInt(linea[3]);
					String estado = linea[17];
					String ciudad = linea[15];
					double longitud = Double.parseDouble(linea[7]);
					double latitud = Double.parseDouble(linea[6]);
					Accidente accidente = new Accidente(fechaInicial, id, severidad, ciudad, estado, longitud, latitud);
					tabla.put(fechaInicial, accidente);
					++cantidadDeAccidentesCargados;
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
			fechaMinima = minimo.getFechaInicial();
			minimo.imprimirAccidente();
			System.out.println("Valor maximo");

			Accidente maximo;
			try {
				maximo = (Accidente) tabla.get(tabla.max());
			} catch (Exception e) {
				maximo = (Accidente) ((ArrayList) tabla.get(tabla.max())).get(((ArrayList) tabla.get(tabla.max())).size()-1);
			}
			fechaMaxima = maximo.getFechaInicial();
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
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fI = fecha + " 00:00:00";
		String fF = fecha + " 23:59:59";
		Date fechaInicial = null;
		Date fechaFinal = null;
		fechaInicial = formato.parse(fI);
		fechaFinal = formato.parse(fF);
		ArrayList accidentes = (ArrayList) tabla.valuesInRange(fechaInicial, fechaFinal);
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
	public String conocerLosAccidentesEnRangoFechasREQ3(String fecha1, String fecha2) throws ParseException
	{
		long startTime = System.nanoTime();
		if(tabla ==null) {
			vista.printError("Es necesario cargar primero los datos (Opcion 10)");
			return null;
		}

		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicial =null;
		Date fechaFinal = null;
		fechaInicial = formato.parse(fecha1);
		fechaFinal = formato.parse(fecha2);

		if(fechaMinima.compareTo(fechaInicial)>0) {
			vista.printError("No puede escoger una fecha antes de "+ convertirDateAFormato(fechaMinima, true));
			return null;
		}

		ArrayList accidentes = (ArrayList) tabla.valuesInRange(fechaInicial, fechaFinal);
		if(accidentes == null)
		{
			vista.printError("No existen accidentes entre estas fechas");
			return null;
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
		long endTime = System.nanoTime();
		int resp = contador1;
		int num = 1;
		if(resp<contador2)
			resp = contador2; num =2;
			if(resp<contador3)
				resp = contador3; num = 3;
				if(resp<contador4)
					resp = contador4; num = 4;

					return "\nSe han registrado " + accidentes.size() + " accidentes entre la fecha " + fecha1 + " y la fecha " + fecha2 + 
							".\n La severidad mas comun entre los accidentes de este rango de fechas fue la " + num + " con " + resp + " ocurrencias.\n" + 
							"Tiempo que tardo el requerimiento " + (endTime-startTime)/1e6 + " ms \n\n";

	}


	public String conocerLosAccidentesAUnaFechaREQ2(String fecha) throws ParseException {
		long startTime = System.nanoTime();
		if(tabla ==null) {
			vista.printError("Es necesario cargar primero los datos (Opcion 10)");
			return null;
		}

		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicial =null;
		fechaInicial = formato.parse(fecha);

		if(fechaMinima.compareTo(fechaInicial)>0) {
			vista.printError("No puede escoger una fecha antes de "+ convertirDateAFormato(fechaMinima, true));
			return null;
		}

		ArrayList accidentes = (ArrayList) tabla.valuesInRange(fechaMinima, fechaInicial);
		if(accidentes == null)
		{
			vista.printError("No existen accidentes en estas fechas");
			return null;
		}


		TablaHashSeparateChaining<String, Integer> tablahash = new TablaHashSeparateChaining<String, Integer>(accidentes.size()/5);
		Integer mayoresAccidentesDeFecha = 0;

		for(int i =0; i < accidentes.size(); i++)
		{
			Accidente accidente = (Accidente)accidentes.get(i);
			Date fechaAct = accidente.getFechaInicial();
			String key = convertirDateAFormato(fechaAct, true);
			Integer valorAnterior;
			try {
				valorAnterior = tablahash.get(key);
				if (valorAnterior ==null) valorAnterior = 0;
			} catch (Exception e) {
				e.printStackTrace();
				valorAnterior = 0; 
			}
			try {
				if(valorAnterior != 0) tablahash.remove(key);
			}catch (Exception e) {}
			tablahash.put(key, valorAnterior+1);
			if(valorAnterior+1>mayoresAccidentesDeFecha) mayoresAccidentesDeFecha=valorAnterior+1;
		}
		String fechaConMayoresAccidentes = tablahash.darLlaveMayorElemento();

		long endTime = System.nanoTime();

		return "\nSe han registrado " + accidentes.size() + " accidentes antes de la fecha " + fecha + "\ny el dia " + fechaConMayoresAccidentes + 
				" fue el dia con mas accidentes, registrando " + mayoresAccidentesDeFecha + " accidentes.\n" + 
				"Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n";
	}

	public String convertirDateAFormato (Date fechaAConvertir , boolean conLineas) {
		if(!conLineas) return "" + (fechaAConvertir.getYear()+1900) + (fechaAConvertir.getMonth()+1) + fechaAConvertir.getDate();
		return "" + (fechaAConvertir.getYear()+1900) + "-" + (fechaAConvertir.getMonth()+1) + "-" + fechaAConvertir.getDate();

	}

	public String conocerAccidentesRangoDeHorasREQ5(String horaMinutoInicialBuscada, String horaMinutoFinalBuscada) {

		if(tabla ==null) {
			vista.printError("Es necesario cargar primero los datos (Opcion 10)");
			return null;
		}

		long startTime = System.nanoTime();

		String[] horaMinutoBuscadaSplit = horaMinutoInicialBuscada.split(":");
		Integer horaBuscada = Integer.parseInt(horaMinutoBuscadaSplit[0]);
		Integer minutoBuscado = Integer.parseInt(horaMinutoBuscadaSplit[1]);
		Integer horaInicial =0;
		Integer minutoInicial = 0;
		Integer horaFinal = 0;
		Integer minutoFinal = 0;

		if(horaBuscada<0) return errorFormatoDeHoras();
		if(horaBuscada>23)return errorFormatoDeHoras();
		if(minutoBuscado<0)return errorFormatoDeHoras();
		if(minutoBuscado>60)return errorFormatoDeHoras();

		if(minutoBuscado>30) {
			minutoInicial  = 0;
			horaBuscada = ((horaBuscada+1)%24);
		}
		else if (minutoBuscado>15) minutoInicial = 30;
		else minutoInicial = 0;
		horaInicial = horaBuscada;

		horaMinutoBuscadaSplit = horaMinutoFinalBuscada.split(":");
		horaBuscada = Integer.parseInt(horaMinutoBuscadaSplit[0]);
		minutoBuscado = Integer.parseInt(horaMinutoBuscadaSplit[1]);

		if(horaBuscada<0) return errorFormatoDeHoras();
		if(horaBuscada>23)return errorFormatoDeHoras();
		if(minutoBuscado<0)return errorFormatoDeHoras();
		if(minutoBuscado>60)return errorFormatoDeHoras();

		if(minutoBuscado>30) {
			minutoFinal  = 0;
			horaBuscada = ((horaBuscada+1)%24);
		}
		else if (minutoBuscado>15) minutoFinal = 30;
		else minutoFinal = 0;
		horaFinal = horaBuscada;

		ArrayList keyset = (ArrayList) tabla.keySet();
		int contadorAccidentes = 0;

		boolean daLaVuelta;
		if(horaInicial<=horaFinal) daLaVuelta = false;
		else daLaVuelta = true;

		int contador1 =0;
		int contador2 =0;
		int contador3 =0;
		int contador4 =0;

		for (Object llave : keyset) {
			Date actual  = (Date) llave;
			Integer horaAct = actual.getHours();
			Integer minAct = actual.getMinutes();
			if(horaDentroDeRangoBuscado(daLaVuelta, horaInicial, horaFinal, minutoInicial, minutoFinal, horaAct, minAct))
			{
				ArrayList<Accidente> accidentes = new ArrayList<Accidente>();
				try {
					Accidente accidente = (Accidente) tabla.get(actual);
					accidentes.add(accidente);
				}catch (Exception e) {
					accidentes.addAll((ArrayList)tabla.get(actual));
				}

				for (Accidente accidente : accidentes) {
					++contadorAccidentes;
					if(accidente.getSeveridad()==1)
					{
						++contador1;
					}
					else if(accidente.getSeveridad()==2)
					{
						++contador2;
					}
					else if(accidente.getSeveridad()==3)
					{
						++contador3;
					}
					else
					{
						++contador4;
					}
				}

			}
		}

		long endTime = System.nanoTime();

		String minutosInicial;
		if(minutoInicial<10) minutosInicial = "0" + minutoInicial; else minutosInicial = minutoInicial+"";

		String minutosFinal;
		if(minutoFinal<10) minutosFinal = "0" + minutoFinal; else minutosFinal = minutoFinal+"";

		double porcentajeConRespectoAtotal = (100d/cantidadDeAccidentesCargados)*contadorAccidentes ;

		return "\nSe han buscado accidentes entre las " + horaInicial + ":"+minutosInicial +" y las " + horaFinal+":" + minutosFinal + ".\n" + 
		"El total de accidentes en este rango de horas es de " + contadorAccidentes + "\nEsto representa un " +porcentajeConRespectoAtotal + "% con respecto a los accidentes totales." + 
		"\n De estos accidentes, " + contador1 + 
		" fueron de severidad 1, " + contador2 + " fueron de severidad 2, " + contador3 + " fueron de severidad 3 y "
		+ contador4 + " fueron de severidad 4. \n"+"Tiempo que tardo la carga de datos: " + (endTime-startTime)/1e6 + " ms \n\n";
	}

	private boolean horaDentroDeRangoBuscado (boolean daLaVuelta, Integer hI, Integer hF, Integer mI, Integer mF, Integer hA, Integer mA) {
		boolean rta = false;
		if(daLaVuelta) {
			boolean centinela = true;
			if(hI<=hA || hF>=hA) {
				if(hI==hA) {
					if(mI>mA) centinela =false;
				}
				if(hF==hA) {
					if(mF<mA) centinela =false;
				}
				if (centinela) return true;
			}
		}else {
			boolean centinela = true;
			if(hI<=hA && hF>=hA) {
				if(hI==hA) {
					if(mI>mA) centinela =false;
				}
				if(hF==hA) {
					if(mF<mA) centinela =false;
				}
				if (centinela) return true;
			}
		}
		return rta;
	}

	private String errorFormatoDeHoras() {
		vista.printError("Las horas deben seguir el fomrato HH:mm y el rango 00:00-23:59");
		return null;
	}
	public Date getFechaMinima() {
		return fechaMinima;
	}


	public void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;
	}


	public Date getFechaMaxima() {
		return fechaMaxima;
	}


	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}


	public int darCantidadDeAccidentesCargados()
	{
		return cantidadDeAccidentesCargados;
	}


	public String conocerEstadoConMasAccidentesEnRangoDeFechas(String primeraFecha, String segundaFecha) throws ParseException
	{
		long startTime = System.nanoTime();
		if(tabla ==null) {
			vista.printError("Es necesario cargar primero los datos (Opcion 10)");
			return null;
		}

		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		List<String> estados = new ArrayList<>();
		String e ="";
		Date fechaInicialEstadoMas =null;
		Date fechaFinalEstadoMas = null;
		fechaInicialEstadoMas = formato.parse(primeraFecha);
		fechaFinalEstadoMas = formato.parse(segundaFecha);


		if(fechaMinima.compareTo(fechaInicialEstadoMas)>0) {
			vista.printError("No puede escoger una fecha antes de "+ convertirDateAFormato(fechaMinima, true));
			return null;
		}

		ArrayList accidentes = (ArrayList) tabla.valuesInRange(fechaInicialEstadoMas, fechaFinalEstadoMas);
		if(accidentes == null)
		{
			vista.printError("No existen accidentes entre estas fechas");
			return null;
		}
	ArrayList estados2 = new ArrayList<String>();
		TablaHashSeparateChaining<String, Integer> tablahashEstados = new TablaHashSeparateChaining<String, Integer>(accidentes.size()/50);
		for(int i =0; i < accidentes.size(); i++)
		{
			Accidente accidente = (Accidente)accidentes.get(i);
			String key = accidente.getEstado();
			if (estados2.size()==0) {
				estados2.add(key);
			}
			for (int j = 0; j < estados2.size() && estados2.size()<50; j++) {
				if (!estados2.get(j).equals(key)) {
					estados2.add(key);
					j=estados2.size();
				}
			}

			tablahashEstados.put(key, 1);	
		}

		int numeroAccidentes = 0;
		String accidenteMas ="";
		for (int i = 0; i < estados2.size(); i++) {

			NodoTH nodo = tablahashEstados.getNodoTH((String) estados2.get(i));
			int contador = 0;


			while (nodo!=null) {
				nodo = nodo.darSiguiente();
				contador++;

			}

			if (contador>numeroAccidentes) {
				numeroAccidentes=contador;
				accidenteMas =(String) estados2.get(i);
			}
		}

		TablaHashSeparateChaining<String, Integer> tablahash = new TablaHashSeparateChaining<String, Integer>(accidentes.size()/5);
		Integer mayoresAccidentesEnUnaFecha = 0;

		for(int i =0; i < accidentes.size(); i++)
		{
			Accidente accidente = (Accidente)accidentes.get(i);
			Date fechaActual = accidente.getFechaInicial();
			String key = convertirDateAFormato(fechaActual, true);
			Integer valorAnterior;
			try {
				valorAnterior = tablahash.get(key);
				if (valorAnterior ==null) valorAnterior = 0;
			} catch (Exception a) {
				a.printStackTrace();
				valorAnterior = 0; 
			}
			try {
				if(valorAnterior != 0) tablahash.remove(key);
			}catch (Exception b) {}
			tablahash.put(key, valorAnterior+1);
			if(valorAnterior+1>mayoresAccidentesEnUnaFecha) mayoresAccidentesEnUnaFecha=valorAnterior+1;
		}
		String fechaConMayorCantidadDeAccidentes = tablahash.darLlaveMayorElemento();
		long endTime = System.nanoTime();


		return "\nSe han registrado " + accidentes.size() + " accidentes entre la fecha " + primeraFecha + " y la fecha " + segundaFecha + 
				".\nEl estado con mayor cantidad de accidentes en este rango de fechas fue " + accidenteMas +" con un total de " + numeroAccidentes  +" accidentes. \n" 
				+"La fecha en la que se presentaron más accidentes fue "+ fechaConMayorCantidadDeAccidentes+ "\n"+
				"Tiempo que tardo el requerimiento " + (endTime-startTime)/1e6 + " ms \n\n";
	}

	
	public String conocerZonaMasAccidentada(String longi, String lat, String rad) {
		long startTime = System.nanoTime();
		double longitudPuntoCentral = Double.parseDouble(longi);
		double latitudPuntoCentral = Double.parseDouble(lat);
		double radio = Double.parseDouble(rad);
		double distancia = 0;
		if(tabla ==null) {
			vista.printError("Es necesario cargar primero los datos (Opcion 10)");
			return null;
		}
		ArrayList accidentes = (ArrayList) tabla.valuesInRange(fechaMinima, fechaMaxima);
		ArrayList accidentesEnLaZona = new ArrayList<Accidente>();
		if(accidentes == null)
		{
			vista.printError("No existen accidentes entre estas fechas");
			return null;
		}
		double accidenteLat = 0.0;
		double accidenteLong = 0.0;
		for (int i = 0; i < accidentes.size(); i++) {
			Accidente accidente = (Accidente) accidentes.get(i);
			 accidenteLat = accidente.getLatitud();
			 accidenteLong = accidente.getLongitud();

	        double distancialong = accidenteLong - longitudPuntoCentral;  
	        double distancialatitud = accidenteLat - latitudPuntoCentral; 
	        double hipPrueba = Math.sqrt((distancialong*distancialong) + (distancialatitud*distancialatitud));
	       
	       if (hipPrueba<=radio) {
			accidentesEnLaZona.add(accidente);
		}
		}
		long endTime = System.nanoTime();  
		return "En la zona seleccionada se han presentado " + accidentesEnLaZona.size() +" accidentes" + "\n" + "Tiempo que tardo el requerimiento " + (endTime-startTime)/1e6 + " ms \n\n";
		
	}
	
}
