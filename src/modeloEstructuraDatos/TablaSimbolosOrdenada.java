package modeloEstructuraDatos;

import java.util.List;

public interface TablaSimbolosOrdenada<K extends Comparable<K>,V> {
	
	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * 
	 * @return número de parejas [Llave,Valor] del árbol
	 */
	public int size();
	
	/**
	 * Informa si el árbol es vacío
	 * 
	 * @return true si está vacio, false en el caso contrario
	 */
	public boolean isEmpty ();

	
	/**
	 * Retorna el valor V asociado a la llave key dada. 
	 * Si la llave no se encuentra se retorna el valor null.
	 * 
	 * @return el valor V asociado a la llave key dada, null si no exisite
	 */
	public V get(K key);

	
	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key 
	 * (si la llave existe). 
	 * Retorna valor –1 si la llave No existe.
	 * 
	 * @return altura desde la raiz hasta la llave, -1 si no exite la llave en el arbol.
	 */
	public int getHeight(K key);

	
	/**
	 * Indica si la llave key se encuentra en el árbol
	 * 
	 * @return true si se encuentra, false en el caso contrario.
	 */
	public boolean contains(K key);

	
	/**
	 * Inserta la pareja [key, val] en el árbol. 
	 * Si la llave ya existe se reemplaza el valor.
	 */
	public void put(K key, V val);

	
	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta 
	 * (aquella que tenga mayor número de enlaces desde la raíz a una hoja).
	 * 
	 * @return la altura del árbol.
	 */
	public int height();

	
	/**
	 * Retorna la llave más pequena del árbol. Valor null si árbol vacío
	 * 
	 * @return la llave más pequena del árbol. Valor null si árbol vacío
	 */
	public K min();

	
	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 * 
	 * @return la llave más grande del árbol. Valor null si árbol vacío
	 */
	public K max(); 
	
	
	/**
	 * Retorna las llaves del árbol. 
	 * Para su implementación en BST o RBT deben retornarse usando un recorrido en Inorden.
	 * 
	 * @return las llaves del árbol.
	 */
	public List<K> keySet();

	
	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. 
	 * Las llaves en el rango deben retornarse en orden ascendente. 
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * 
	 * @return todas las llaves K en el árbol que se encuentran en el rango de llaves dado
	 */
	public List<K> keysInRange(K init, K end);
	
	
	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * 
	 * @return todos los valores V en el árbol que estén asociados al rango de llaves dado
	 */
	public List<V> valuesInRange(K init, K end);

	

}
