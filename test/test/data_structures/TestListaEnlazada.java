package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jdk.nashorn.internal.runtime.ListAdapter;
import model.data_structures.ListaEncadenada;
import model.data_structures.NodoLista;
import model.logic.Comparendo;

public class TestListaEnlazada 
{
	private ListaEncadenada<Comparendo> lista;
	
	@Before
	public void setUp1() {
		lista= new ListaEncadenada<Comparendo>();
	}

	public void setUp2() {
		lista = new ListaEncadenada<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			lista.agregar(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}

	@Test
	public void testListaEnlazada() {
		
		assertNotNull("El arreglo debería estar creado",lista);
		assertEquals(0, lista.darLongitud());
	}
	
	@Test
	public void testAgregar()
	{
		for (int i = 0; i < 10; i++) 
		{
			lista.agregar(new Comparendo(i, "", "", "", "", "", "", null));
		}
		assertNotNull("El arreglo debería existir",lista);
		assertEquals(10, lista.darLongitud());
		
		assertNotNull("No debería ser null", lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
		assertNull("Debería ser null", lista.buscar(new Comparendo(2000, "", "", "", "", "", "", null)));
		
		assertTrue("Debería retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).darId()+".",lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).compareTo(new Comparendo(1, "", "", "", "", "", "", null))==0);

	}
	
	@Test
	public void testAgregarFinal()
	{
		for (int i = 0; i < 10; i++) 
		{
			lista.agregarFinal(new Comparendo(i, "", "", "", "", "", "", null));
		}
		assertNotNull("El arreglo debería existir",lista);
		assertEquals(10, lista.darLongitud());
		
		assertNotNull("No debería ser null", lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
		assertNull("Debería ser null", lista.buscar(new Comparendo(2000, "", "", "", "", "", "", null)));
		
		assertTrue("Debería retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).darId()+".",lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).compareTo(new Comparendo(1, "", "", "", "", "", "", null))==0);
		assertEquals(lista.darElemento(0), lista.darPrimero());
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo());
	}
	
	@Test
	public void testAgregarInicio()
	{
		for (int i = 0; i < 10; i++) 
		{
			lista.agregarInicio(new Comparendo(i, "", "", "", "", "", "", null));
		}
		assertNotNull("El arreglo debería existir",lista);
		assertEquals(10, lista.darLongitud());
		
		assertNotNull("No debería ser null", lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
		assertNull("Debería ser null", lista.buscar(new Comparendo(2000, "", "", "", "", "", "", null)));
		
		assertTrue("Debería retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).darId()+".",lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).compareTo(new Comparendo(1, "", "", "", "", "", "", null))==0);
		assertEquals(lista.darElemento(0), lista.darPrimero());
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo());
	}
	
	@Test
	public void testDarPrimero()
	{
		setUp2();
		assertEquals(lista.darElemento(0), lista.darPrimero());
		Comparendo c = new Comparendo(0, "", "", "", "", "", "", null);
		lista.agregarInicio(c);
		assertEquals(c, lista.darPrimero());

	}
	
	@Test
	public void testDarUltimo()
	{
		setUp2();
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo());
		Comparendo c = new Comparendo(0, "", "", "", "", "", "", null);
		lista.agregarFinal(c);
		assertEquals(c, lista.darUltimo());
		
	}
	
	@Test
	public void testdarLongitud()
	{
		assertEquals(0, lista.darLongitud());
		setUp2();
		assertEquals(10, lista.darLongitud());
	}
	
	@Test
	public void testBuscar()
	{
		setUp2();
		assertNotNull("El arreglo debería existir",lista);
		assertEquals(10, lista.darLongitud());
		
		assertNotNull("No debería ser null", lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)));
		assertNull("Debería ser null", lista.buscar(new Comparendo(2000, "", "", "", "", "", "", null)));
		
		assertTrue("Debería retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).darId()+".",lista.buscar(new Comparendo(1, "", "", "", "", "", "", null)).compareTo(new Comparendo(1, "", "", "", "", "", "", null))==0);


	}
	
	@Test
	public void testDarElemento()
	{
		setUp2();
		assertNotNull("El arreglo debería estar creado",lista);
		assertEquals(10, lista.darLongitud());
		
		assertNull("Debería ser null",lista.darElemento(200));
		
		Comparendo elemento = null;		
		for (int i = 0; i < lista.darLongitud(); i++) 
		{
			elemento = lista.darElemento(i);
			assertNotNull("Debería existir",elemento);
		}
		
	}
	
	@Test
	public void testElementoActual()
	{
		setUp2();
		assertNull("Debería ser Null",lista.elementoActual());
		lista.iniciarRecorrido();
		assertTrue("Actual debería ser el primero", lista.elementoActual()==lista.darPrimero());
		assertNotNull("No debería ser Null",lista.elementoActual());
	}
	
	@Test
	public void testIniciarRecorrido()
	{
		setUp2();
		lista.iniciarRecorrido();
		assertTrue("Actual debería ser el primero", lista.elementoActual()==lista.darPrimero());
	}
	
	@Test
	public void testAvanzarActual()
	{
		setUp2();
		
		Comparendo c = lista.darPrimero();
		lista.avanzarActual();
		assertNull("Debería ser null",lista.elementoActual());
		lista.iniciarRecorrido();
		lista.avanzarActual();
		assertTrue("Actual debería ser el siguiente del primero", lista.elementoActual()==lista.darElemento(1));
	}
	
	@Test
	public void testRetrocederActual()
	{
		Comparendo c = lista.darPrimero();
		lista.avanzarActual();
		assertNull("Debería ser null",lista.elementoActual());
		lista.iniciarRecorrido();
		lista.retrocederActual();
		assertNull("Debería ser null",lista.elementoActual());
		lista.iniciarRecorrido();
		lista.avanzarActual();
		lista.avanzarActual();
		lista.retrocederActual();
		assertTrue("Actual debería ser el siguiente del primero", lista.elementoActual()==lista.darElemento(1));

	}

}
