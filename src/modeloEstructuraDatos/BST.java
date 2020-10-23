package modeloEstructuraDatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements TablaSimbolosOrdenada<K, V> {


	private Nodo raiz;

	public BST(Nodo pRaiz) {
		raiz = pRaiz;
	}

	@Override
	public int size() {
		return size(raiz);
	}

	private int size(Nodo nodo)
	{
		if (nodo == null) return 0;
		else {return nodo.darNumNodosBajoEl();}
	}

	@Override
	public boolean isEmpty() {
		if(raiz==null) return true;
		return false;
	}

	@Override
	public V get(K key) {
		return get(raiz, key);
	}

	private V get(Nodo nodo, K key) {
		if (nodo == null) return null;
		int resultadoComparacion = key.compareTo((K) nodo.darKey());

		if (resultadoComparacion < 0) return get(nodo.darIzquierdo(), key); 
		else if (resultadoComparacion > 0) return get(nodo.darDerecho(), key); 
		else{ 
			try {
				return (V) nodo.darValor();
			}
			 catch (Exception e) {
				 return (V)(((ArrayList<Object>) (nodo.darValor())).get(0));
			 }
		}
	}
	
	public ArrayList<Object> getAll(K key) {
		return getAll(raiz, key);
	}
	private ArrayList getAll(Nodo nodo, K key) {
		if (nodo == null) return null;
		int resultadoComparacion = key.compareTo((K) nodo.darKey());

		if (resultadoComparacion < 0) return getAll(nodo.darIzquierdo(), key); 
		else if (resultadoComparacion > 0) return getAll(nodo.darDerecho(), key); 
		else{ 
			try {
				return (ArrayList) nodo.darValor();
			}catch(Exception e) {
				ArrayList<Object> rta = new ArrayList<Object>();
				rta.add(nodo.darValor());
				return (rta);
			}
		}
	}
	
	
	@Override
	public int getHeight(K key) {
		int rta = 1;
		Nodo act = raiz;
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

	@Override
	public void put(K key, V valor) {
		raiz = put(raiz, key, valor);
	}

	private Nodo put(Nodo nodo, K key, V valor) {
		if(nodo==null) return new Nodo(key, valor);
		int cmp = key.compareTo((K) nodo.darKey());
		if (cmp < 0) nodo.setIzquierdo(put(nodo.darIzquierdo(), key, valor));
		else if (cmp > 0) nodo.setDerecho(put(nodo.darDerecho(), key, valor));
		else if (cmp ==0) nodo.anadirValorANodo(valor);
		int nuevoNumeroDeNodosBajoEl = size(nodo.darIzquierdo()) + size(nodo.darDerecho()) + 1;
		nodo.establecerNumNodosBajoEl(nuevoNumeroDeNodosBajoEl);
		return nodo;
	}

	@Override
	public int height() {
		if (isEmpty()) return 0;
		return height(raiz);
	}
	
	private int height (Nodo nodo) {
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
	public K min() {
		if (raiz==null) return null;
		Nodo act = raiz;
		while (act.tieneIzquierdo()) act = act.darIzquierdo();
		return (K) act.darKey();
	}

	@Override
	public K max() {
		if (raiz==null) return null;
		Nodo act = raiz;
		while (act.tieneDerecho()) act = act.darDerecho();
		return (K) act.darKey();
	}

	@Override
	public List<K> keySet() {
		if (raiz==null) return null;
		return keySet(raiz);
	}
	
	private List<K> keySet(Nodo nodo) {
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
	
	private List<K> keysInRange(Nodo nodo, K init, K end) {
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
	
	private List<V> valuesInRange(Nodo nodo, K init, K end) {
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
