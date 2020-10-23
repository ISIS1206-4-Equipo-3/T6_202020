package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.List;

public class RBT<K extends Comparable<K>, V> implements TablaSimbolosOrdenada<K,V>{

	private NodoRB raiz;


	@Override
	public boolean isEmpty() {
		if(raiz==null) return true;
		return false;
	}

	private boolean esRojo(NodoRB x)
	{
		if (x == null) return false;
		return x.esRojo();
	}
	@Override
	public V get(K key) {
		return get(raiz, key);
	}

	private V get(NodoRB nodo, K key) {
		if (nodo == null) return null;
		int resultadoComparacion = key.compareTo((K) nodo.darKey());

		if (resultadoComparacion < 0) return get(nodo.darIzquierdo(), key); 
		else if (resultadoComparacion > 0) return get(nodo.darDerecho(), key); 
		else{ return (V) nodo.darValor();}
	}

	@Override
	public int getHeight(K key) {
		int rta = 1;
		NodoRB act = raiz;
		boolean encontrado =false;
		while (act.darKey()!=key || act!=null) {
			int numComp = key.compareTo((K)act.darKey());
			if(numComp>0) {
				act = act.darDerecho(); 
				rta +=1;
			}
			else if(numComp<0) {
				act = act.darIzquierdo(); 
				rta +=1;
			}
			else {
				encontrado = true;
				break;
			}
		}
		if(encontrado) return rta;
		return -1;
	}

	@Override
	public boolean contains(K key) {
		V valor = get(key);
		if(valor==null)return false;
		return true; 
	}

	public void put(K key, V valor) {
		raiz = put(raiz, key, valor);
		raiz.setNegro();
	}

	private NodoRB put(NodoRB nodo, K key, V valor) {
		if(nodo==null) return new NodoRB(key, valor,NodoRB.ROJO);
		int cmp = key.compareTo((K) nodo.darKey());
		if (cmp < 0) nodo.setIzquierdo(put(nodo.darIzquierdo(), key, valor));
		else if (cmp > 0) nodo.setDerecho(put(nodo.darDerecho(), key, valor));
		else if (cmp ==0) nodo.anadirValorANodo(valor);
		if (esRojo(nodo.darDerecho()) && !esRojo(nodo.darIzquierdo())) nodo = rotarIzquierdo(nodo);
		if (esRojo(nodo.darIzquierdo()) && esRojo(nodo.darIzquierdo().darIzquierdo())) nodo = rotarDerecho(nodo);
		if (esRojo(nodo.darIzquierdo()) && esRojo(nodo.darDerecho())) flipColores(nodo);

		int nuevoNumeroDeNodosBajoEl = size(nodo.darIzquierdo()) + size(nodo.darDerecho()) + 1;
		nodo.establecerNumNodosBajoEl(nuevoNumeroDeNodosBajoEl);
		return nodo;
	}
	public NodoRB rotarIzquierdo(NodoRB nodo)
	{
		NodoRB x = nodo.darDerecho();
		nodo.setDerecho(x.darIzquierdo());
		x.setIzquierdo(nodo);
		if(nodo.esRojo())
		{
			x.setRojo();
		}
		else
		{
			x.setNegro();
		}
		nodo.setRojo();
		x.establecerNumNodosBajoEl(nodo.darNumNodosBajoEl());
		nodo.establecerNumNodosBajoEl(1+ size(nodo.darIzquierdo()) + size(nodo.darDerecho()));
		return x;
	}

	public NodoRB rotarDerecho(NodoRB nodo)
	{
		NodoRB x = nodo.darIzquierdo();
		nodo.setIzquierdo(x.darDerecho());
		x.setDerecho(nodo);
		if(nodo.esRojo())
		{
			x.setRojo();
		}
		else
		{
			x.setNegro();
		}
		nodo.setRojo();
		x.establecerNumNodosBajoEl(nodo.darNumNodosBajoEl());
		nodo.establecerNumNodosBajoEl(1+ size(nodo.darIzquierdo()) + size(nodo.darDerecho()));
		return x;
	}

	void flipColores(NodoRB nodo)
	{
		nodo.setRojo(); 
		nodo.darIzquierdo().setNegro();
		nodo.darDerecho().setNegro();
	}
	@Override
	public int height() {
		if (isEmpty()) return 0;
		return height(raiz);
	}
	
	private int height (NodoRB nodo) {
		int rta = 1;
		int alturaDerecha = 0;
		int alturaIzquierda = 0;
		if(nodo.tieneDerecho()) alturaDerecha = height(nodo.darDerecho());
		if(nodo.tieneIzquierdo()) alturaIzquierda = height(nodo.darIzquierdo());
		if(alturaDerecha>alturaIzquierda) rta += alturaDerecha;
		else rta +=alturaIzquierda;
		return rta;
	}
	
	@Override
	public int size() {
		return size(raiz);
	}

	private int size(NodoRB nodo)
	{
		if (nodo == null) return 0;
		else {return nodo.darNumNodosBajoEl();}
	}
	@Override
	public K min() {
		if (raiz==null) return null;
		NodoRB act = raiz;
		while (act.tieneIzquierdo()) act = act.darIzquierdo();
		return (K) act.darKey();
	}

	@Override
	public K max() {
		if (raiz==null) return null;
		NodoRB act = raiz;
		while (act.tieneDerecho()) act = act.darDerecho();
		return (K) act.darKey();
	}
	
	@Override
	public List<K> keySet() {
		if (raiz==null) return null;
		return keySet(raiz);
	}
	private List<K> keySet(NodoRB nodo) {
		List<K> lista = new  ArrayList<K>();
		lista.add((K)nodo.darKey());
		if(nodo.tieneIzquierdo()) lista.addAll(keySet(nodo.darIzquierdo()));
		if(nodo.tieneDerecho()) lista.addAll(keySet(nodo.darDerecho()));
		return lista;
	}

	@Override
	public List<K> keysInRange(K init, K end) {
		if (raiz==null) return null;
		return keysInRange(raiz, init, end);
	}
	
	private List<K> keysInRange(NodoRB nodo, K init, K end) {
		List<K> lista = new  ArrayList<K>();
		if((((K) nodo.darKey()).compareTo(init)>=0) && (((K) nodo.darKey()).compareTo(end)<=0)) lista.add((K)nodo.darKey());
		if(nodo.tieneIzquierdo()) lista.addAll(keysInRange(nodo.darIzquierdo(),init,end));
		if(nodo.tieneDerecho()) lista.addAll(keysInRange(nodo.darDerecho(),init,end));
		return lista;
	}

	@Override
	public List<V> valuesInRange(K init, K end) {
		if (raiz==null) return null;
		return valuesInRange(raiz, init, end);
	}
	
	private List<V> valuesInRange(NodoRB nodo, K init, K end) {
		List<V> lista = new  ArrayList<V>();
		if((((K) nodo.darKey()).compareTo(init)>=0) && (((K) nodo.darKey()).compareTo(end)<=0)) {
			try {
				ArrayList<V> definitiva = new ArrayList<V>();
				ArrayList<Object> listaObjetos = (ArrayList<Object>) nodo.darValor();
				for (Object object : listaObjetos) {
					definitiva.add((V) object);
				}
				lista.addAll(definitiva);
			}catch (Exception e) {
				lista.add((V)nodo.darValor());
			}
		}
			
		if(nodo.tieneIzquierdo()) lista.addAll(valuesInRange(nodo.darIzquierdo(),init,end));
		if(nodo.tieneDerecho()) lista.addAll(valuesInRange(nodo.darDerecho(),init,end));
		return lista;
	}

}
