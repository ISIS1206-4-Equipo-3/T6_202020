package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class TablaHashSeparateChaining<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos <K, V>{

	private NodoTH[] valueList;
	private int M;

	/**
	 * Crea una tabla de has con manejo de colisiones separate Chaining
	 * @param pCapacidad Hace referencia a la cantidad de filas que va a tener la tabla.
	 */
	public TablaHashSeparateChaining(int pCapacidad) {
		M = pCapacidad;
		valueList = new NodoTH[pCapacidad];
		
	}

	private int hash (K key) {return ((key.hashCode() & 0x7fffffff) % M);}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		Integer hashKey = hash(key);
		NodoTH nuevo = new NodoTH(key, value);
		nuevo.setSiguiente(getNodoTH(hashKey));
		valueList[hashKey] = nuevo;
	}

	public NodoTH getNodoTH(K key){return valueList[hash(key)];}
	private  NodoTH getNodoTH (Integer hashKey){return valueList[hashKey];}
	
	
	/**
	 * Este metodo solamente va a retornar el primer elemento asociado a esa llave, (Ultimo ananido).
	 */
	@Override
	public V get(K key) {
		int valorHash = hash(key);
		NodoTH aBuscar = valueList[valorHash];
		if (aBuscar==null) return null;
		boolean centinela = true;
		while(centinela) {
			if (aBuscar ==null) centinela = false;
			else if(((K)aBuscar.darKey())!=key) centinela = false;
			else aBuscar = aBuscar.darSiguiente();
		}
		if(aBuscar == null) return null; 
		return (V) aBuscar.darValor();
	}

	/**
	 * Este metodo va a eliminar TODOS los elementos asociados a esa llave
	 * Se va a retornar solamente el primer elemento agregado.
	 */
	@Override

	public V remove(K key) {
		V rta = null;
		int hashKey = hash(key);
		NodoTH act = getNodoTH(hashKey);
		if(act == null ) return null;
		if(act.darKey().equals(key)) {
			rta = (V) act.darValor();
			valueList[hashKey] = act.darSiguiente();
		}else {
			boolean centinela = true;
			while(act.darSiguiente()!=null && centinela) {
				if(act.darSiguiente().darKey().equals(key)) {
					rta = (V) act.darSiguiente().darValor();
					centinela =false;
					act.setSiguiente(act.darSiguiente().darSiguiente());
				}
				act = act.darSiguiente();
			}
		}
		return rta;
	}

	@Override
	public boolean contains(K key) {
		NodoTH act = getNodoTH(key);
		while(act!=null) {
			if(act.darKey().equals(key)) return true;
			act = act.darSiguiente();
		}
		return false;
	}


	@Override
	public boolean isEmpty() {
		for (NodoTH NodoTHs : valueList) {
			if(NodoTHs!=null) return false;
		}
		return true;
	}

	@Override
	public int size() {
		int contador = 0;
		for (NodoTH NodoTHs : valueList) {
			NodoTH act = NodoTHs;
			while(act!=null) {
				++contador;
				act = act.darSiguiente();
			}
		}
		return contador;
	}

	@Override
	public List<K> keySet() {
		if(isEmpty()) return null;
		List <K> listaLlaves = new ArrayList<K>();
		for (NodoTH NodoTH : valueList) {
			NodoTH NodoTHAct = NodoTH;
			while(NodoTHAct!=null) {
				if(NodoTHAct.darKey()!=null) listaLlaves.add((K) NodoTHAct.darKey());
				NodoTHAct = NodoTHAct.darSiguiente();
			}
		}
		return listaLlaves;
	}

	@Override
	public List<V> valueSet() {
		if(isEmpty()) return null;
		List <V> listaValores = new ArrayList<V>();
		for (NodoTH NodoTH : valueList) {
			NodoTH NodoTHAct = NodoTH;
			while(NodoTHAct!=null) {
				if(NodoTHAct.darValor()!=null) listaValores.add((V) NodoTHAct.darValor());
				NodoTHAct = NodoTHAct.darSiguiente();
			}
		}
		return listaValores;

	}
	
	
	//OJOOO ESTE METODO SOLO SIRVE EN EL RETO 3 
	public K darLlaveMayorElemento() {
		if(isEmpty()) return null;
		K llaveConMayorElemento = null;
		int valorMayor = 0;
		for (NodoTH NodoTH : valueList) {
			NodoTH NodoTHAct = NodoTH;
			while(NodoTHAct!=null) {
				if(NodoTHAct.darValor()!=null) {
					
					if ((Integer) NodoTHAct.darValor() >valorMayor) {
						
						valorMayor = (Integer) NodoTHAct.darValor();
						llaveConMayorElemento = (K) NodoTHAct.darKey();
					}
				}
				NodoTHAct = NodoTHAct.darSiguiente();
			}
		}
		return llaveConMayorElemento;
	}
	
	/**
	 * Este metodo retorna siempre el primer elemento que se encuentre en el primer NodoTH, esto significa el ultimo
	 * elemento agregado al primero NodoTH no nulo.
	 */
	public V darPrimerElemento() {
		V rta = null;
		int primerElemento = 0;
		while(valueList[primerElemento]==null) primerElemento++;
		NodoTH act = valueList[primerElemento];
		return (V) act.darValor();
	}
	
	/**
	 * Este metodo retorna siempre el primer elemento que se encuentre en el ultimo NodoTH, esto significa el ultimo
	 * elemento agregado al ultimo NodoTH no nulo de la lista.
	 */
	public V darUltimoElemento() {
		V rta = null;
		int ultimoElemento = M-1;
		while(valueList[ultimoElemento]==null) ultimoElemento--;
		NodoTH act = valueList[ultimoElemento];
		return (V) act.darValor();
	}


}
