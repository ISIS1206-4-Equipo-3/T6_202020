package modeloEstructuraDatos;

import java.util.Date;

public class Viaje {

	private int duracionViaje;	
	private int idInicio;
	private int idFinal;
	private int idBicicleta;
	private double latitudInicio;
	private double latitudFinal;
	private double longitudInicio;
	private double longitudFinal;
	private Date fechaInicio;
	private Date fechaFinal;
	
	
	public Viaje(int duracionViaje, int idInicio, int idFinal, int idBicicleta, double latitudInicio, double latitudFinal, double longitudInicio, double longitudFinal, Date fechaInicio, Date fechaFinal) {
		
		this.duracionViaje = duracionViaje;
		this.idInicio = idInicio;
		this.idFinal = idFinal;
		this.idBicicleta = idBicicleta;
		this.latitudInicio = latitudInicio;
		this.latitudFinal = latitudFinal;
		this.longitudInicio = longitudInicio;
		this.longitudFinal = longitudFinal;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		
	}
	
	
	public void imprimirViaje()
	{
		System.out.println("Id Bicileta: " + idBicicleta);
		System.out.println("Fecha inicial: " + (fechaInicio.getYear()+1900) + "-" + (fechaInicio.getMonth()+1) + "-" + fechaInicio.getDate());
		System.out.println("Fecha final: " + (fechaFinal.getYear()+1900) + "-" + (fechaFinal.getMonth()+1) + "-" + fechaFinal.getDate());
		System.out.println("Duracion Viaje: "+ duracionViaje);

	}


	public int getDuracionViaje() {
		return duracionViaje;
	}


	public void setDuracionViaje(int duracionViaje) {
		this.duracionViaje = duracionViaje;
	}


	public int getIdInicio() {
		return idInicio;
	}


	public void setIdInicio(int idInicio) {
		this.idInicio = idInicio;
	}


	public int getIdFinal() {
		return idFinal;
	}


	public void setIdFinal(int idFinal) {
		this.idFinal = idFinal;
	}


	public int getIdBicicleta() {
		return idBicicleta;
	}


	public void setIdBicicleta(int idBicicleta) {
		this.idBicicleta = idBicicleta;
	}


	public double getLatitudInicio() {
		return latitudInicio;
	}


	public void setLatitudInicio(double latitudInicio) {
		this.latitudInicio = latitudInicio;
	}


	public double getLatitudFinal() {
		return latitudFinal;
	}


	public void setLatitudFinal(double latitudFinal) {
		this.latitudFinal = latitudFinal;
	}


	public double getLongitudInicio() {
		return longitudInicio;
	}


	public void setLongitudInicio(double longitudInicio) {
		this.longitudInicio = longitudInicio;
	}


	public double getLongitudFinal() {
		return longitudFinal;
	}


	public void setLongitudFinal(double longitudFinal) {
		this.longitudFinal = longitudFinal;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	
	
	
}
