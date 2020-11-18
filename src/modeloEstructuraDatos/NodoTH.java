package modeloEstructuraDatos;

public class NodoTH {

	private Object key;
	private Object valor;
	private NodoTH siguiente;

	public NodoTH(Object pkey, Object value) {
		this.key = pkey;
		this.valor = value;
	}
	
	public void cambiarValor(Object pDato) {valor = pDato;}

	public Object darValor() { return valor; }
	
	public Object darKey() { return key; }
	
	public NodoTH darSiguiente() {return siguiente;}
	
	public void setSiguiente (NodoTH pSiguiente) {siguiente = pSiguiente;}
	
	public boolean tieneSiguiente () {
		if(siguiente==null) return false;
		else { return true;}
	}
	

}
