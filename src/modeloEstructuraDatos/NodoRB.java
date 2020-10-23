package modeloEstructuraDatos;

import java.util.ArrayList;

public class NodoRB {

	public static final boolean ROJO = true;
	public static final boolean NEGRO = false;
	private Object key;
	private Object valor;
	private NodoRB izquierdo;
	private NodoRB derecho;
	private int numeroDeNodosBajoEl;
	private boolean color;

	
	
	
	public NodoRB(Object pkey, Object value, boolean color) {
		numeroDeNodosBajoEl = 1;
		this.key = pkey;
		this.valor = value;
		this.color = color;
	}
	
	public boolean esRojo () {return color;}
	
	public void setNegro () {color = NEGRO;}
	
	public void setRojo () {color = ROJO;}
	
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
	public void anadirValorANodo (Object pValor) {
		ArrayList<Object> listaDeNodos= new ArrayList<Object>();
		try {
			listaDeNodos.addAll( (ArrayList<Object>) valor);
			listaDeNodos.add(pValor);
		}catch (Exception e) {
			listaDeNodos.add(valor);
			listaDeNodos.add(pValor);
		}
		this.valor = listaDeNodos;
		
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
