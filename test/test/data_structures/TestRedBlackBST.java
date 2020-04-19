package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.RedBlackBST;

public class TestRedBlackBST 
{
	/**
	 * Atributo de la clase HashSeparateChaining implementado en las pruebas.
	 */
	private RedBlackBST<Integer, Integer> rb;



	/**
	 * Escenario 1 de prueba.
	 */
	@Before
	public void setUp1() {
		rb= new RedBlackBST<Integer, Integer>();
	}

	/**
	 * Escenario 2 de prueba.
	 */
	public void setUp2() {
		rb= new RedBlackBST<Integer, Integer>();
		for (Integer i = 0; i < 500; ++i) 
		{
			rb.put(i, i);;
		}
	}

	/**
	 * Prueba del método constructor de la clase BlackRedBST.
	 */
	@Test
	public void testConstructor()
	{
		//Revisa el contructor
		assertNotNull("Debería existir la cola",rb);
		assertEquals(0, rb.size());

		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La tabla debería existir", rb);
		assertEquals(500, rb.size());
	}
	
	/**
	 * Prueba del método size() de la clase BlackRedBST.
	 */
	@Test
	public void testSize()
	{
		//Revisa el contructor
		assertNotNull("Debería existir la cola",rb);
		assertEquals(0, rb.size());
		
		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La tabla debería existir", rb);
		assertEquals(500, rb.size());
	}
	
	/**
	 * Prueba del método isEmpty() de la clase BlackRedBST.
	 */
	@Test
	public void testIsEmpty()
	{
		//Revisa el contructor
		assertTrue(rb.isEmpty());
		rb.put(1, 1);
		assertFalse(rb.isEmpty());
		
		//Se comprueba con el escenario 2.
		setUp2();
		assertFalse(rb.isEmpty());
		
	}
	

	@Test
	public void testGet()
	{
		setUp2();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		for (Integer i = 0; i < 500; ++i) 
		{
			assertNotNull("Debería existir", rb.get(i));
			assertEquals(i,rb.get(i));
		}
		rb.put(500, 500);
		assertNotNull("Debería existir", rb.get(500));
		assertEquals(500,rb.get(500),0);
	}
	

	@Test
	public void testGetHeight()
	{
		rb.put(0, 0);
		rb.put(1,1);
		rb.put(2, 2);
		
		assertEquals(1, rb.getHeight(0));
		assertEquals(0, rb.getHeight(1));
		assertEquals(1, rb.getHeight(2));
		
		assertEquals(rb.height(),rb.getHeight(0));
		assertEquals(rb.height(),rb.getHeight(2));
		setUp2();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		
		assertEquals(rb.height(), rb.getHeight(0));
		assertEquals(8, rb.getHeight(0));
		assertEquals(7, rb.getHeight(1));
		assertEquals(8, rb.getHeight(2));
		assertEquals(6, rb.getHeight(3));
		assertEquals(8, rb.getHeight(4));
		assertEquals(7, rb.getHeight(5));
		assertEquals(8, rb.getHeight(6));
		assertEquals(5, rb.getHeight(7));
		assertEquals(8, rb.getHeight(8));
		assertEquals(7, rb.getHeight(9));
		assertEquals(8, rb.getHeight(10));

		rb.put(500, 500);
		assertNotNull("Debería existir", rb.get(500));
		assertEquals(8,rb.getHeight(500),0);
		assertEquals(rb.height(), rb.getHeight(500));
	}
	
	@Test
	public void testContanis()
	{
		assertFalse(rb.contains(1));
		rb.put(1, 1);
		assertNotNull("Debería existir", rb.get(1));
		assertTrue(rb.contains(1));
		
		setUp2();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		for (Integer i = 0; i < 500; ++i) 
		{
			assertTrue(rb.contains(i));
		}
		rb.put(500, 500);
		assertNotNull("Debería existir", rb.get(500));
		assertTrue(rb.contains(500));
	}

	/**
	 * Prueba del método put() de la clase HashSeparateChaining.
	 */
	@Test
	public void testPut()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(0, rb.size());
		rb.put(1, 1);
		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la tabla.
		assertEquals(1, rb.size());
		assertNotNull("Debería existir", rb.get(1));
		
		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		for (Integer i = 0; i < 500; ++i) 
		{
			assertNotNull("Debería existir", rb.get(i));
		}
	}
	
	/**
	 * Prueba del método height() de la clase BlackRedBST.
	 */
	@Test
	public void testHeight()
	{
		assertEquals(0, rb.height());
		rb.put(1, 1);
		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la tabla.
		assertEquals(1, rb.size());
		assertNotNull("Debería existir", rb.get(1));
		assertEquals(0,rb.height());
		rb.put(2, 2);
		assertEquals(2, rb.size());
		assertNotNull("Debería existir", rb.get(2));
		assertEquals(1,rb.height());
		setUp2();
		assertEquals(8,rb.height());
	}
	
	/**
	 * Prueba del método min() de la clase BlackRedBST.
	 */
	@Test
	public void testMin()
	{
		assertNull(rb.min());
		rb.put(1, 1);
		assertEquals(1,rb.min(),0);
		rb.put(2, 2);
		assertEquals(1, rb.min(),0);
		rb.put(0, 0);
		assertEquals(0, rb.min(),0);
		setUp2();
		assertNotNull(rb.min());
		assertEquals(0, rb.min(),0);
		rb.put(-1, -1);
		assertEquals(-1, rb.min(),0);
		
	}
	
	/**
	 * Prueba del método max() de la clase BlackRedBST.
	 */
	@Test
	public void testMax()
	{
		assertNull(rb.max());
		rb.put(1, 1);
		assertEquals(1,rb.max(),0);
		rb.put(2, 2);
		assertEquals(2, rb.max(),0);
		rb.put(0, 0);
		assertEquals(2, rb.max(),0);
		setUp2();
		assertNotNull(rb.max());
		assertEquals(499, rb.max(),0);
		rb.put(500, 500);
		assertEquals(500, rb.max(),0);
	}
	
	/**
	 * Prueba del método check de la clase BlackRedBST.
	 */
	@Test
	public void testCheck()
	{
		assertTrue(rb.check());
		rb.put(1, 1);
		assertTrue(rb.check());
		rb.put(2, 2);
		rb.put(4,4);
		rb.put(5, 5);
		rb.put(3, 3);
		assertTrue(rb.check());
		rb.rotateLeft(rb.darNodo(4));
		assertFalse(rb.check());
		
		setUp2();
		assertTrue(rb.check());
		rb.rotateLeft(rb.darNodo(123));
		assertFalse(rb.check());
		
		
	}
	
	/**
	 * Prueba del método keys de la clase BlackRedBST.
	 */
	@Test
	public void testKeys()
	{
		//Se comprueba el iterador.
		setUp2();
		Iterator<Integer>nuevo=rb.keys();
		Integer i=0;
		while(nuevo.hasNext())
		{
			assertEquals(i, nuevo.next());
			++i;
		}
		
		assertEquals(rb.size(),i,0);
	}
	
	/**
	 * Prueba del método valuesInRange de la clase BlackRedBST.
	 */
	@Test
	public void testValuesInRange()
	{
		setUp2();
		Iterator<Integer>nuevo=rb.valuesInRange(40, 230);
		Integer i=0;
		Integer j = 0;
		while(nuevo.hasNext())
		{
			Integer x = nuevo.next();
			assertNotEquals(i, x);
			assertEquals(i+40,x,0);
			++i;
		}
		
		assertEquals(230-40,i-1,0);
	}
	
	/**
	 * Prueba del método keysInRange de la clase BlackRedBST.
	 */
	@Test
	public void testKeysInRange()
	{
		setUp2();
		Iterator<Integer>nuevo=rb.keysInRange(40, 230);
		Integer i=0;
		Integer j = 0;
		while(nuevo.hasNext())
		{
			Integer x = nuevo.next();
			assertNotEquals(i, x);
			assertEquals(i+40,x,0);
			++i;
		}
		
		assertEquals(230-40,i-1,0);
	}
}
