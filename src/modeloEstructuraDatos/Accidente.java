package modeloEstructuraDatos;
import java.util.Date;
public class Accidente {
	
	private Date fechaInicial;
	
	private String id;
	
	private int severidad;
	
	private String ciudad;
	
	private String estado;
	
	private double longitud;
	
	private double latitud;

	public Accidente(Date fechaInicial, String id, int severidad, String ciudad, String estado, double longitud, double latitud ) {
		this.fechaInicial = fechaInicial;
		this.id = id;
		this.severidad = severidad;
		this.ciudad = ciudad;
		this.estado = estado;
		this.longitud = longitud;
		this.latitud = latitud;
		
	}

	public void imprimirAccidente()
	{
		System.out.println("Id: " + id);
		System.out.println("Fecha inicial: " + (fechaInicial.getYear()+1900) + "-" + (fechaInicial.getMonth()+1) + "-" + fechaInicial.getDate());
		System.out.println("Severidad: "+ severidad);
		System.out.println("Ciudad: "+ ciudad );
		System.out.println("Estado: "+ estado);
	
		
		
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeveridad() {
		return severidad;
	}

	public void setSeveridad(int severidad) {
		this.severidad = severidad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud () {
		this.longitud = longitud;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public void setLatitud() {
		this.latitud = latitud;
	}
	
}
