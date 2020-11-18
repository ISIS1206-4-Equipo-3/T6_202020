package modeloEstructuraDatos;

public class Estacion {
	
	
	
	private int latitud;
	private int longitud;
	private int id;
	private String nombre;
	
	public Estacion(int pLatitud, int pLongitud, int pId, String pNombre)
	{
		latitud = pLatitud;
		longitud = pLongitud;
		id = pId;
		nombre = pNombre;
	}

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String darValor()
	{
		return id + "," + latitud + "," + longitud + "," + nombre;
	}
	
	
	
	
	
	
	
	
	
	
}