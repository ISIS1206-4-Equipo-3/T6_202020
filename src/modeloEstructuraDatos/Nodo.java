package modeloEstructuraDatos;

public class Nodo {

	private Object key;
	private Object valor;
	private Nodo izquierdo;
	private Nodo derecho;
	private int numeroDeNodosBajoEl;

	public Nodo(Object pkey, Object value) {
		numeroDeNodosBajoEl = 1;
		this.key = pkey;
		this.valor = value;
	}
	
	public void establecerNumNodosBajoEl(int nuevoNumero) { numeroDeNodosBajoEl = nuevoNumero;}
	
	public int darNumNodosBajoEl ( ) {return numeroDeNodosBajoEl;}
	
	public void cambiarValor(Object pDato) {valor = pDato;}

	public Object darValor() { return valor; }
	
	public Object darKey() { return key; }
	
	public Nodo darIzquierdo() {return izquierdo;}
	
	public void setIzquierdo (Nodo pIzquierdo) {izquierdo = pIzquierdo;}
	
	public Nodo darDerecho() {return derecho;}
	
	public void setDerecho (Nodo pDerecho) {derecho = pDerecho;}
	
	public boolean tieneIzquierdo () {
		if(izquierdo==null) return false;
		else { return true;}
	}
	
	public boolean tieneDerecho () {
		if(derecho==null) return false;
		else { return true;}
	}
//
//	public int compareTo(Nodo o) {
//		int hashCodeO = o.darKey().hashCode();
//		int hashCodeAct = key.hashCode();
//		if(hashCodeO>hashCodeAct) return -1;
//		if(hashCodeO<hashCodeAct) return 1;
//		return 0;
//	}
	

}
