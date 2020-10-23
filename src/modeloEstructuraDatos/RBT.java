package modeloEstructuraDatos;

import java.util.List;

public class RBT<K extends Comparable<K>, V> implements TablaSimbolosOrdenada<K,V>{

	private NodoRB raiz;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(K key, V val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> keysInRange(K init, K end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<V> valuesInRange(K init, K end) {
		// TODO Auto-generated method stub
		return null;
	}

}
