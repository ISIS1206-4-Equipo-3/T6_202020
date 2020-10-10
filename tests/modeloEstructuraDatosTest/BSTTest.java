package modeloEstructuraDatosTest;

import modeloEstructuraDatos.BST;
import modeloEstructuraDatosTest.Before;
import testModeloEstructuraDatos.Test;

public class BSTTest {

	
	BST<Integer, String> bst1;
	BST<String, String> bst2;
	
	@Before
	public void setUp1() {
		bst1 = new BST<Integer, String>(null);
		bst1.put(5, "cinco");
		bst1.put(3, "tres");
		bst1.put(1, "uno");
		bst1.put(2, "dos");
		bst1.put(10, "diez");
		bst1.put(8, "ocho");
		bst1.put(9, "nueve");
		bst1.put(6, "seis");
		bst1.put(4, "cuatro");
		bst1.put(7, "siete");
		bst1.put(0, "cero");
	}
	@Before
	public void setUp2() {
		bst2 = new BST<String, String>(null);
		bst2.put("cinco", "5");
		bst2.put("tres", "3");
		bst2.put( "uno", "1");
		bst2.put("dos", "2");
		bst2.put("diez","10");
		bst2.put("ocho","8");
		bst2.put( "nueve","9");
		bst2.put( "seis","6");
		bst2.put( "cuatro","4");
		bst2.put( "siete","7");
		bst2.put( "cero","0");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	@Test
	public void testPut() {
		fail("Not yet implemented");
	}

	@Test
	public void testHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testMin() {
		fail("Not yet implemented");
	}

	@Test
	public void testMax() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeySet() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeysInRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testValuesInRange() {
		fail("Not yet implemented");
	}

}
