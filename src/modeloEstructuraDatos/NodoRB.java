package modeloEstructuraDatos;


public class NodoRB {

	private Object key;
	private Object valor;
	private NodoRB izquierdo;
	private NodoRB derecho;
	private int numeroDeNodosBajoEl;
	private boolean rojo;
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	
	
	public NodoRB(Object pkey, Object value) {
		numeroDeNodosBajoEl = 1;
		this.key = pkey;
		this.valor = value;
		this.rojo = false;
	}
	
	public boolean esRojo () {return rojo;}
	
	public void setNegro () {rojo = false;}
	
	public void setRojo () {rojo = true;}
	
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
	
	private boolean verificarHijosParaRojo() {
		boolean rta = true;
		if(tieneIzquierdo()) {
			if (darIzquierdo().esRojo()) rta = false;
		}
		if(tieneDerecho()) {
			if (darDerecho().esRojo()) rta = false;
		}
		return rta;
	}
	public NodoRB darMayor()
	{
		return tieneDerecho() ? this : derecho.darMayor();
	}
	
	public NodoRB darMenor()
	{
		return tieneIzquierdo() ? this : izquierdo.darMenor();
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
