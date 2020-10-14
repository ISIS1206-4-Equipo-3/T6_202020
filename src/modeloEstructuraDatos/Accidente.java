package modeloEstructuraDatos;
import java.util.Date;
public class Accidente {
	
	private Date fechaInicial;
	
	private String id;
	
	private int severidad;
	
	private String ciudad;

	public Accidente(Date fechaInicial, String id, int severidad, String ciudad) {
		this.fechaInicial = fechaInicial;
		this.id = id;
		this.severidad = severidad;
		this.ciudad = ciudad;
	}

	public void imprimirAccidente()
	{
		System.out.println("Id: " + id);
		System.out.println("Fecha inicial: " + (fechaInicial.getYear()+1900) + "-" + fechaInicial.getMonth() + "-" + fechaInicial.getDay());
		System.out.println("Severidad: "+ severidad);
		System.out.println("Ciudad: "+ ciudad + "\n");
		
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
	 

}
