package test.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import model.logic.Comparendo;
import model.logic.Modelo;

public class TestModelo {
	
	/**
	 * Atributo con la clase módelo.
	 */
	private Modelo modelo;
	
	/**
	 * Constante que maneja la ruta del archivo que carga los comparendos.
	 */
	public final static String RUTA = "./data/comparendos_dei_2018_small.geojson";
	
	/**
	 * Escenario 1
	 */
	@Before
	public void setUp1() {
		modelo= new Modelo();
	}

	/**
	 * Escenario 2
	 * @throws FileNotFoundException Si no encuentra el archivo
	 */
	public void setUp2() throws FileNotFoundException {
		modelo = new Modelo();
		modelo.cargarComparendos(RUTA);
	}
	
	/**
	 * Escenario 3
	 */
	public void setUp3() {
		modelo = new Modelo();
		for (int i = 0; i < 10; i++) 
		{
			modelo.agregarQueue(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}
	
	/**
	 * Escenario 4
	 */
	public void setUp4() {
		modelo = new Modelo();
		for (int i = 0; i < 10; i++) 
		{
			modelo.agregarStack(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}

	/**
	 * Prueba del constructor de la clase Modelo.
	 */
	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamanoCola());  
		assertEquals(0, modelo.darTamanoPila());  
	}

	/**
	 * Prueba del método dar longitud de la clase Modelo
	 */
	@Test
	public void testDarTamano() {
		assertEquals(0, modelo.darTamanoCola());
		
		try
		{
			setUp2();
			assertNotNull("El arreglo debería existir",modelo);
			assertEquals(20, modelo.darTamanoCola());
			assertEquals(20, modelo.darTamanoPila());
	

		}
		catch(FileNotFoundException e)
		{
			assertEquals(0, modelo.darTamanoCola());
			assertEquals(0, modelo.darTamanoPila());
		}
		
		setUp3();
		assertNotNull("El arreglo debería existir",modelo);
		assertEquals(10, modelo.darTamanoCola());
		
		setUp4();
		assertNotNull("El arreglo debería existir",modelo);
		assertEquals(10, modelo.darTamanoPila());
	}

	/**
	 * Prueba del método agregar de la clase Modelo.
	 */
	@Test
	public void testAgregar() {
		// TODO Completar la prueba
		assertEquals(0, modelo.darTamanoCola());
		
		Comparendo c = new Comparendo(0, "", "", "", "", "", "", new double[2]);
		modelo.agregarQueue(c);
		modelo.agregarStack(c);
		assertEquals(1,modelo.darTamanoCola());
		assertEquals(1, modelo.darTamanoPila());
		assertTrue("Debería retornar el comparendo pero retorna "+modelo.darUltimoComparendo()+".",modelo.darUltimoComparendo().compareTo(c)==0);
		assertTrue("Debería retornar el comparendo pero retorna "+modelo.darPrimerComparendo()+".",modelo.darPrimerComparendo().compareTo(c)==0);


		for (int i = 1; i < 200; i++) 
		{
			c = new Comparendo(i, "", "", "", "", "", "",  new double[2]);
			modelo.agregarQueue(c);
			modelo.agregarStack(c);
			assertEquals(i+1,modelo.darTamanoCola());
			assertEquals(i+1,modelo.darTamanoPila());
			assertTrue("Debería retornar "+i+ " pero retorna "+modelo.darUltimoComparendo()+".",modelo.darUltimoComparendo().compareTo(c)==0);
			assertTrue("Debería retornar "+0+ " pero retorna "+modelo.darPrimerComparendo()+".",modelo.darPrimerComparendo().compareTo(new Comparendo(0, "", "", "", "", "", "", null))==0);
		}
		
	}
	
	/**
	 *Prueba del método eliminar de la clase Modelo.
	 */
	@Test
	public void testEliminar() {
		// TODO Completar la prueba
		
		try
		{
			setUp2();

			assertNotNull("El arreglo debería existir",modelo);
			assertEquals(20, modelo.darTamanoCola());
			assertEquals(20, modelo.darTamanoPila());
			
			Comparendo c = new Comparendo(0, "", "", "", "", "", "", null);

			Comparendo eliminar = modelo.eliminarStack();
			assertNotNull("No debería ser null", eliminar);
			assertEquals(19, modelo.darTamanoPila());
			assertTrue("No retorna el comparendo esperado", eliminar.compareTo(new Comparendo(209146,"","","","","","",null))==0);

			eliminar = modelo.eliminarQueue();
			assertNotNull("No debería ser null", eliminar);
			assertEquals(19, modelo.darTamanoCola());
			assertTrue("No retorna el comparendo esperado", eliminar.compareTo(new Comparendo(29042,"","","","","","",null))==0);

		}
		catch(Exception e)
		{
			assertEquals(0, modelo.darTamanoCola());  
			assertEquals(0, modelo.darTamanoPila());  
		}
		
		
	}

}
