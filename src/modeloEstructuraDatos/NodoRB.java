package modeloEstructuraDatos;

public class NodoRB {

	private Object key;
	private Object valor;
	private NodoRB izquierdo;
	private NodoRB derecho;
	private int numeroDeNodosBajoEl;

	public NodoRB(Object pkey, Object value) {
		numeroDeNodosBajoEl = 1;
		this.key = pkey;
		this.valor = value;
	}
	
	public void establecerNumNodosBajoEl(int nuevoNumero) { numeroDeNodosBajoEl = nuevoNumero;}
	
	public int darNumNodosBajoEl ( ) {return numeroDeNodosBajoEl;}
	
	public void cambiarValor(Object pDato) {valor = pDato;}

	public Object darValor() { return valor; }
	
	public Object darKey() { return key; }
	
	public NodoRB darIzquierdo() {return izquierdo;}
	
	public void setIzquierdo (NodoRB pIzquierdo) {izquierdo = pIzquierdo;}
	
	public NodoRB darDerecho() {return derecho;}
	
	public void setDerecho (NodoRB pDerecho) {derecho = pDerecho;}
	
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
