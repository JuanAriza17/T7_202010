package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.HashSeparateChaining;
import model.data_structures.ListaEncadenada;
import model.data_structures.MaxColaCP;
import model.logic.Comparendo;
import model.logic.Modelo;
import model.logic.Ordenamientos;

public class TestSeparateChaining 
{
	/**
	 * Atributo de la clase módelo
	 */
	private Modelo modelo;
	
	/**
	 * Atributo de la clase HashSeparateChaining implementado en las pruebas.
	 */
	private HashSeparateChaining<Integer,Integer> lp;

	/**
	 * Atributo del tamaño.
	 */
	private static int TAMANO=500; 

	/**
	 * Escenario 1 de prueba.
	 */
	@Before
	public void setUp1() {
		lp= new HashSeparateChaining<Integer,Integer>(TAMANO);
	}

	/**
	 * Escenario 2 de prueba.
	 */
	public void setUp2() {
		lp= new HashSeparateChaining<Integer,Integer>(TAMANO);
		for (Integer i = 0; i < TAMANO; ++i) 
		{
			lp.putInSet(i, i);
		}
	}

	/**
	 * Prueba del método constructor de la clase HashSeparateChaining.
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Debería existir la cola",lp);
		assertEquals(0, lp.darNumPares());
		assertEquals(500, lp.darTamano());

		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La tabla debería existir", lp);
		assertEquals(500, lp.darNumPares());
	}

	/**
	 * Prueba del método put() de la clase HashSeparateChaining.
	 */
	@Test
	public void TestPut()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(0, lp.darNumPares());
		lp.putInSet(1, 1);
		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la tabla.
		assertEquals(1, lp.darNumPares());
		assertNotNull("Debería existir", lp.getSet(1));
		
		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		for (Integer i = 0; i < TAMANO; ++i) 
		{
			assertNotNull("Debería existir", lp.getSet(i));
		}
	}

	/**
	 * Prueba del método getSet() de la clase HashSeparateChaining.
	 */
	@Test
	public void TestGet()
	{
		setUp2();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		for (Integer i = 0; i < TAMANO; ++i) 
		{
			assertNotNull("Debería existir", lp.getSet(i));
			ListaEncadenada<Integer> lista=new ListaEncadenada<Integer>();
			lista.agregarFinal(i);
			Iterator<Integer> it=lista.iterator();
			assertEquals(it.next(), lp.getSet(i).next() );
		}
		lp.putInSet(500, 500);
		assertNotNull("Debería existir", lp.getSet(500));
		ListaEncadenada<Integer> lista=new ListaEncadenada<Integer>();
		lista.agregarFinal(500);
		Iterator<Integer> it=lista.iterator();
		assertEquals(it.next(), lp.getSet(500).next() );
	}

	/**
	 * Prueba del método delete() de la clase HashSeparateChaining.
	 */
	@Test
	public void TestDelete()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(0,lp.darNumPares());
		lp.putInSet(1,1);
		assertEquals(1, lp.darNumPares());
		Iterator<Integer> nuevo=lp.deleteSet(1);
		assertEquals(0, lp.darNumPares());
		assertNull("No debería existir", lp.getSet(1));
		ListaEncadenada<Integer> lista=new ListaEncadenada<Integer>();
		lista.agregarFinal(1);
		Iterator<Integer> it=lista.iterator();
		assertEquals(it.next(), nuevo.next());
		setUp2();
		//Comprobamos tamaño
		for (Integer i = 0; i < TAMANO; ++i) 
		{
			nuevo=lp.deleteSet(i);
			assertEquals(TAMANO-1-i, lp.darNumPares());
			assertNull("No debería existir", lp.getSet(i));
			lista=new ListaEncadenada<Integer>();
			lista.agregarFinal(i);
			it=lista.iterator();
			assertEquals(it.next(), nuevo.next() );
		}
	}
	
	/**
	 * Prueba del método rehash() de la clase HashSeparateChaining.
	 */
	@Test
	public void TestRehash()
	{
		//Comprobamos factor de carga.
		setUp2();
		lp.rehash(997);
		assertEquals(997, lp.darTamano());
		assertEquals(500, lp.darNumPares());
		assertEquals(1, lp.darNumeroRehashes());
		double valor=(double)500/997;
		double factor=(double)lp.darNumPares()/lp.darTamano();
		assertEquals(valor, factor,0);
		
	}

	/**
	 * Prueba del método keys() de la clase HashSeparateChaining.
	 */
	@Test
	public void TestIterator()
	{
		//Se comprueba el iterador.
		setUp2();
		Iterator<Integer>nuevo=lp.keys();
		Integer i=0;
		while(nuevo.hasNext())
		{
			assertEquals(i, nuevo.next());
			++i;
		}
	}
}