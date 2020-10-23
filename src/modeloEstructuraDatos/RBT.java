package modeloEstructuraDatos;

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
		// TODO Auto-generated method stub
		return false;
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
		if (esRojo(nodo.darIzquierdo()) && esRojo(nodo.darIzquierdo())) nodo = rotarDerecho(nodo);
		if (esRojo(nodo.darIzquierdo()) && esRojo(nodo.darDerecho())) flipColores(nodo);
		
		int nuevoNumeroDeNodosBajoEl = size(nodo.darIzquierdo()) + size(nodo.darDerecho()) + 1;
		nodo.establecerNumNodosBajoEl(nuevoNumeroDeNodosBajoEl);
		return nodo;
	}
	NodoRB rotarIzquierdo(NodoRB nodo)
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
	NodoRB rotarDerecho(NodoRB nodo)
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
		// TODO Auto-generated method stub
		return 0;
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
