package test.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.logic.Comparendo;
import model.logic.Modelo;

public class TestModelo {
	
	//TODO ARIZA Todo está hecho menos esta clase, porque no comprendo muy bien si se debe probar tanto con la pila como con la cola. El resto de clases están completamente documentadas y finalizadas (a excepción de los otros dos todos que dejé).
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
		// TODO
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
		assertTrue("Debería retornar el comparendo pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).compareTo(c)==0);

		for (int i = 1; i < 200; i++) 
		{
			c = new Comparendo(i, "", "", "", "", "", "",  new double[2]);
			modelo.agregar(c);
			assertEquals(i+1,modelo.darLongitud());
			assertTrue("Debería retornar "+i+ " pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).compareTo(c)==0);
		}
		
	}

	/**
	 * Prueba del método buscar de la clase Modelo.
	 */
	@Test
	public void testBuscar() {
		// TODO Completar la prueba
		try
		{
			setUp2();
			assertNotNull("El arreglo debería existir",modelo);
			assertEquals(20, modelo.darLongitud());
			
			assertNull("Debería ser null", modelo.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
			
			Comparendo c = new Comparendo(29042, "", "", "", "", "", "", null);
			assertNotNull("No debería ser null", modelo.buscar(c));

			assertTrue("Debería retornar el comparento con el ID 29042 pero retorna el comparendo con ID "+modelo.buscar(c).darId()+".",modelo.buscar(c).compareTo(c)==0);
			c = new Comparendo(29042, "", "", "", "", "", "", null);
			assertTrue("Debería retornar el comparento con el ID 29042 pero retorna el comparendo con ID "+modelo.buscar(c).darId()+".",modelo.buscar(c).compareTo(c)==0);
		}
		catch(Exception e)
		{
			assertEquals(0, modelo.darLongitud());
		}
		
		setUp3();
		assertNotNull("El arreglo debería existir",modelo);
		assertEquals(10, modelo.darLongitud());
		
		assertNotNull("No debería ser null", modelo.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
		assertNull("Debería ser null", modelo.buscar(new Comparendo(2000, "", "", "", "", "", "", null)));
		
		assertTrue("Debería retornar el comparento con el ID 1 pero retorna el comparendo con ID "+modelo.buscar(new Comparendo(1, "", "", "", "", "", "", null)).darId()+".",modelo.buscar(new Comparendo(1, "", "", "", "", "", "", null)).compareTo(new Comparendo(1, "", "", "", "", "", "", null))==0);



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
			assertEquals(20, modelo.darLongitud());
			
			Comparendo c = new Comparendo(0, "", "", "", "", "", "", null);

			Comparendo eliminar = modelo.eliminar(c);
			assertNull("Debería ser null", eliminar);
			assertEquals(20, modelo.darLongitud());
			
			c = new Comparendo(29049, "", "", "", "", "", "", null); 
			
			Comparendo dato = modelo.buscar(c);
			eliminar = modelo.eliminar(c);
			assertTrue("Deberían ser el mismo dato",dato.equals(eliminar));
			assertEquals(19, modelo.darLongitud());
			assertNull("Debeía ser null", modelo.buscar(c));
			
			Iterator<Comparendo> it = modelo.darLista().iterator();
			
			int i = 0;
			Comparendo co = null;
			while(it.hasNext())
			{
				i++;
				co = it.next();
			}
			
			assertEquals(i, modelo.darLongitud());

		}
		catch(Exception e)
		{
			assertEquals(20, modelo.darLongitud());
		}
		
		
	}

}
