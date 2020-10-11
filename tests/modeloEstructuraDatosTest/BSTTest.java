package modeloEstructuraDatosTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modeloEstructuraDatos.BST;

public class BSTTest {
	
	private BST bst1;
	private BST bst2;
	
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
	
	public void setUp2() {
		bst2 = new BST<String, Integer>(null);
		bst2.put("cinco", 5);
		bst2.put("tres", 3);
		bst2.put( "uno", 1);
		bst2.put("dos", 2);
		bst2.put("diez",10);
		bst2.put("ocho",8);
		bst2.put( "nueve",9);
		bst2.put( "seis",6);
		bst2.put( "cuatro",4);
		bst2.put( "siete",7);
		bst2.put( "cero",0);
	}
	
	public void setUp3() {
		bst2 = new BST<String, String>(null);
		bst2.put("cinco", "5");
		bst2.put("tres", "3");
		bst2.put( "uno", "1");
		bst2.put("dos", "2");
		bst2.put("diez","10");
	}
	
	public void setUp4() {
		bst1 = new BST<String, String>(null);
	}

	public void setUp5() {
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
	public void sizeTest() {
		setUp1();
		assertEquals(11,bst1.size());
		setUp3();
		assertEquals(5,bst2.size());
	}
	
	@Test
	public void isEmptyTest() {
		setUp4();
		assertEquals(true,bst1.isEmpty());
		setUp1();
		assertEquals(false,bst1.isEmpty());
	}
	
	@Test
	public void getTest() {
		setUp1();
		assertEquals("ocho",bst1.get(8));
		assertEquals("nueve",bst1.get(9));
		assertEquals("cero",bst1.get(0));
		assertEquals("uno",bst1.get(1));
		assertEquals("cinco",bst1.get(5));
		
		setUp5();
		assertEquals("8",bst2.get("ocho"));
		assertEquals("9",bst2.get("nueve"));
		assertEquals("0",bst2.get("cero"));
		assertEquals("1",bst2.get("uno"));
		assertEquals("5",bst2.get("cinco"));
		
		setUp2();
		assertEquals(8,bst2.get("ocho"));
		assertEquals(9,bst2.get("nueve"));
		assertEquals(0,bst2.get("cero"));
		assertEquals(1,bst2.get("uno"));
		assertEquals(5,bst2.get("cinco"));
	}
	
	@Test
	public void getHeightTest() {
		setUp1();
		assertEquals(1,bst1.getHeight(5));
		assertEquals(4,bst1.getHeight(6));
		assertEquals(5,bst1.getHeight(7));
		setUp3();
		assertEquals(1,bst2.getHeight("cinco"));
		assertEquals(3,bst2.getHeight("dos"));
		assertEquals(4,bst2.getHeight("diez"));
	}
	
	@Test
	public void containsTest() {
		setUp1();
		assertEquals(true,bst1.contains(1));
		assertEquals(true,bst1.contains(3));
		assertEquals(true,bst1.contains(5));
		assertEquals(true,bst1.contains(10));
		assertEquals(false,bst1.contains(12));
		assertEquals(false,bst1.contains(100));
		assertEquals(false,bst1.contains(-1));
		assertEquals(false,bst1.contains(30));
	}
	
	@Test
	public void heightTest() {
		setUp1();
		assertEquals(5,bst1.height());
		setUp3();
		assertEquals(4,bst2.height());
	}
	
	@Test
	public void minTest() {
		setUp1();
		assertEquals(0,bst1.min());
		setUp3();
		assertEquals("cinco",bst2.min());
		setUp2();
		assertEquals("cero",bst2.min());
		setUp4();
		assertEquals(null,bst1.min());
	}
	
	@Test
	public void maxTest() {
		setUp1();
		assertEquals(10,bst1.max());
		setUp3();
		assertEquals("uno",bst2.max());
		setUp2();
		assertEquals("uno",bst2.max());
		setUp4();
		assertEquals(null,bst1.max());
	}
	
	@Test
	public void keySetTest() {
		setUp1();
		assertEquals(true,bst1.keySet().contains(1));
		assertEquals(true,bst1.keySet().contains(10));
		assertEquals(11,bst1.keySet().size());
		
		setUp2();
		assertEquals(true,bst2.keySet().contains("uno"));
		assertEquals(true,bst2.keySet().contains("cero"));
		assertEquals(11,bst2.keySet().size());
		
		setUp3();
		assertEquals(true,bst2.keySet().contains("uno"));
		assertEquals(true,bst2.keySet().contains("diez"));
		assertEquals(5,bst2.keySet().size());
		
		setUp4();
		assertEquals(null,bst1.keySet());
		
	}
	
	@Test
	public void keysInRangeTest() {
		setUp1();
		assertEquals(true,bst1.keysInRange(3,5).contains(4));
		assertEquals(true,bst1.keysInRange(3,5).contains(3));
		assertEquals(false,bst1.keysInRange(3,5).contains(1));
		assertEquals(false,bst1.keysInRange(3,5).contains(10));
		assertEquals(false,bst1.keysInRange(3,5).contains(6));
		assertEquals(5,bst1.keysInRange(3,7).size());
	}
	
	@Test
	public void valuesInRange() {
		setUp1();
		assertEquals(true,bst1.valuesInRange(3,5).contains("cuatro"));
		assertEquals(true,bst1.valuesInRange(3,5).contains("cinco"));
		assertEquals(false,bst1.valuesInRange(3,5).contains("uno"));
		assertEquals(false,bst1.valuesInRange(3,5).contains("diez"));
		assertEquals(false,bst1.valuesInRange(3,5).contains("seis"));
		assertEquals(5,bst1.valuesInRange(3,7).size());
	}
	
	
	
}
