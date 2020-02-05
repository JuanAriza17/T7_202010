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
	
	private Modelo modelo;
	public final static String RUTA = "./data/comparendos_dei_2018_small.geojson";
	
	@Before
	public void setUp1() {
		modelo= new Modelo();
	}

	public void setUp2() throws FileNotFoundException {
		modelo.cargarComparendos(RUTA);
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darLongitud());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarLongitud() {
		// TODO
		assertEquals(0, modelo.darLongitud());
		
		try
		{
			setUp2();
			assertNotNull("El arreglo debería existir",modelo);
			assertEquals(20, modelo.darLongitud());
			
			Iterator<Comparendo> it = modelo.darLista().iterator();
			
			int i = 0;
			Comparendo c = null;
			while(it.hasNext())
			{
				i++;
				c = it.next();
			}
			
			assertEquals(i, modelo.darLongitud());

		}
		catch(FileNotFoundException e)
		{
			assertEquals(0, modelo.darLongitud());
		}
		
	}

	@Test
	public void testAgregar() {
		// TODO Completar la prueba
		assertEquals(0, modelo.darLongitud());
		
		Comparendo c = new Comparendo(0, "", "", "", "", "", "", null);
		modelo.agregar(c);
		assertEquals(1,modelo.darLongitud());
		assertTrue("Debería retornar 0 pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).equals(c));

		for (int i = 1; i < 200; i++) 
		{
			c = new Comparendo(i, "", "", "", "", "", "", null);
			modelo.agregar(c);
			assertEquals(i+1,modelo.darLongitud());
			assertTrue("Debería retornar "+i+ " pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).compareTo(c)==0);
		}
		
	}

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

	}

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
			assertEquals(0, modelo.darLongitud());
		}
		
		
	}

}
