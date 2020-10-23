package modeloEstructuraDatosTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modeloEstructuraDatos.BST;
import modeloEstructuraDatos.RBT;

public class RBTTest {
	
	private RBT rbt1;
	private RBT rbt2;
	
	public void setUp1() {
		rbt1 = new RBT<Integer, String>();
		rbt1.put(5, "cinco");
		rbt1.put(5, "cinco2");
		rbt1.put(3, "tres");
		rbt1.put(1, "uno");
		rbt1.put(2, "dos");
		rbt1.put(10, "diez");
		rbt1.put(8, "ocho");
		rbt1.put(9, "nueve");
		rbt1.put(6, "seis");
		rbt1.put(4, "cuatro");
		rbt1.put(7, "siete");
		rbt1.put(0, "cero");
	}
	
	public void setUp2() {
		rbt2 = new RBT<String, Integer>();
		rbt2.put("cinco", 5);
		rbt2.put("tres", 3);
		rbt2.put( "uno", 1);
		rbt2.put("dos", 2);
		rbt2.put("diez",10);
		rbt2.put("ocho",8);
		rbt2.put( "nueve",9);
		rbt2.put( "seis",6);
		rbt2.put( "cuatro",4);
		rbt2.put( "siete",7);
		rbt2.put( "cero",0);
	}
	
	public void setUp3() {
		rbt2 = new RBT<String, String>();
		rbt2.put("cinco", "5");
		rbt2.put("tres", "3");
		rbt2.put( "uno", "1");
		rbt2.put("dos", "2");
		rbt2.put("diez","10");
	}
	
	public void setUp4() {
		rbt1 = new RBT<String, String>();
	}

	public void setUp5() {
		rbt2 = new RBT<String, String>();
		rbt2.put("cinco", "5");
		rbt2.put("tres", "3");
		rbt2.put( "uno", "1");
		rbt2.put("dos", "2");
		rbt2.put("diez","10");
		rbt2.put("ocho","8");
		rbt2.put( "nueve","9");
		rbt2.put( "seis","6");
		rbt2.put( "cuatro","4");
		rbt2.put( "siete","7");
		rbt2.put( "cero","0");
	}
	
	public void setUp6(){
		
		rbt2 = new RBT<Integer, String>();
		rbt2.put(5, "1,5");
		rbt2.put(5, "2,5");
		rbt2.put(5, "3,5");
		rbt2.put(5, "4,5");
		rbt2.put(5, "5,5");
	}
	
	
	@Test
	public void sizeTest() {
		setUp1();
		assertEquals(11,rbt1.size());
		setUp3();
		assertEquals(5,rbt2.size());
	}
	
	@Test
	public void isEmptyTest() {
		setUp4();
		assertEquals(true,rbt1.isEmpty());
		setUp1();
		assertEquals(false,rbt1.isEmpty());
	}
	
	@Test
	public void getTest() {
		setUp1();
		assertEquals("ocho",rbt1.get(8));
		assertEquals("nueve",rbt1.get(9));
		assertEquals("cero",rbt1.get(0));
		assertEquals("uno",rbt1.get(1));
		assertEquals(2,((ArrayList<Object>) rbt1.get(5)).size());
		
		setUp5();
		assertEquals("8",rbt2.get("ocho"));
		assertEquals("9",rbt2.get("nueve"));
		assertEquals("0",rbt2.get("cero"));
		assertEquals("1",rbt2.get("uno"));
		assertEquals("5",rbt2.get("cinco"));
		
		setUp2();
		assertEquals(8,rbt2.get("ocho"));
		assertEquals(9,rbt2.get("nueve"));
		assertEquals(0,rbt2.get("cero"));
		assertEquals(1,rbt2.get("uno"));
		assertEquals(5,rbt2.get("cinco"));
	}
	
	@Test
	public void getHeightTest() {
		setUp1();
		assertEquals(1,rbt1.getHeight(5));
		assertEquals(4,rbt1.getHeight(6));
		assertEquals(5,rbt1.getHeight(7));
		setUp3();
		assertEquals(1,rbt2.getHeight("cinco"));
		assertEquals(3,rbt2.getHeight("dos"));
		assertEquals(4,rbt2.getHeight("diez"));
	}
	
}	
//	@Test
//	public void containsTest() {
//		setUp1();
//		assertEquals(true,rbt1.contains(1));
//		assertEquals(true,rbt1.contains(3));
//		assertEquals(true,rbt1.contains(5));
//		assertEquals(true,rbt1.contains(10));
//		assertEquals(false,rbt1.contains(12));
//		assertEquals(false,rbt1.contains(100));
//		assertEquals(false,rbt1.contains(-1));
//		assertEquals(false,rbt1.contains(30));
//	}
//	
//	@Test
//	public void heightTest() {
//		setUp1();
//		assertEquals(5,rbt1.height());
//		setUp3();
//		assertEquals(4,rbt2.height());
//	}
//	
//	@Test
//	public void minTest() {
//		setUp1();
//		assertEquals(0,rbt1.min());
//		setUp3();
//		assertEquals("cinco",rbt2.min());
//		setUp2();
//		assertEquals("cero",rbt2.min());
//		setUp4();
//		assertEquals(null,rbt1.min());
//	}
//	
//	@Test
//	public void maxTest() {
//		setUp1();
//		assertEquals(10,rbt1.max());
//		setUp3();
//		assertEquals("uno",rbt2.max());
//		setUp2();
//		assertEquals("uno",rbt2.max());
//		setUp4();
//		assertEquals(null,rbt1.max());
//	}
//	
//	@Test
//	public void keySetTest() {
//		setUp1();
//		assertEquals(true,rbt1.keySet().contains(1));
//		assertEquals(true,rbt1.keySet().contains(10));
//		assertEquals(11,rbt1.keySet().size());
//		
//		setUp2();
//		assertEquals(true,rbt2.keySet().contains("uno"));
//		assertEquals(true,rbt2.keySet().contains("cero"));
//		assertEquals(11,rbt2.keySet().size());
//		
//		setUp3();
//		assertEquals(true,rbt2.keySet().contains("uno"));
//		assertEquals(true,rbt2.keySet().contains("diez"));
//		assertEquals(5,rbt2.keySet().size());
//		
//		setUp4();
//		assertEquals(null,rbt1.keySet());
//		
//	}
//	
//	@Test
//	public void keysInRangeTest() {
//		setUp1();
//		assertEquals(true,rbt1.keysInRange(3,5).contains(4));
//		assertEquals(true,rbt1.keysInRange(3,5).contains(3));
//		assertEquals(false,rbt1.keysInRange(3,5).contains(1));
//		assertEquals(false,rbt1.keysInRange(3,5).contains(10));
//		assertEquals(false,rbt1.keysInRange(3,5).contains(6));
//		assertEquals(5,rbt1.keysInRange(3,7).size());
//	}
//	
//	@Test
//	public void valuesInRange() {
//		setUp1();
//		assertEquals(true,rbt1.valuesInRange(3,5).contains("cuatro"));
//		assertEquals(true,rbt1.valuesInRange(3,5).contains("cinco"));
//		assertEquals(false,rbt1.valuesInRange(3,5).contains("uno"));
//		assertEquals(false,rbt1.valuesInRange(3,5).contains("diez"));
//		assertEquals(false,rbt1.valuesInRange(3,5).contains("seis"));
//		assertEquals(6,rbt1.valuesInRange(3,7).size());
//	}
//	
//
//	@Test
//	public void revisarConRepeticiones() {
//		setUp6();
//		assertEquals(1,rbt2.height());
//		assertEquals(true,rbt2.valuesInRange(4,6).contains("1,5"));
//		assertEquals(true,rbt2.valuesInRange(4,6).contains("3,5"));
//		assertEquals(true,rbt2.valuesInRange(4,6).contains("5,5"));
//		
//	}
//	
//	
//	
//}
