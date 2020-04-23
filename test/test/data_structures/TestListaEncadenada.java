package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ListaEncadenada;
import model.logic.Comparendo;

public class TestListaEncadenada 
{
	/**
	 * Lista de comparendos que va a ser utilizada en las pruebas.
	 */
	private ListaEncadenada<Comparendo> lista;

	/**
	 * M�todo que maneja las pruebas de coordenadas.
	 * @return Arreglo de tama�o fijo double que maneja las coordenadas ingresadas por par�metro.
	 */
	public double[] coordenadas(double pLongitud, double pLatitud)
	{
		double[] coordenadas=new double[2];
		coordenadas[0]=pLongitud;
		coordenadas[1]=pLatitud;
		return coordenadas;
	}
	/**
	 * Escenario 1 de pruebas.
	 */
	@Before
	public void setUp1() {
		lista= new ListaEncadenada<Comparendo>();
	}

	/**
	 * Escenario 2 de pruebas.
	 */
	public void setUp2() {
		lista = new ListaEncadenada<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			lista.agregar(new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i),""));
		}
	}

	/**
	 * Prueba del m�todo constructur de las clase ListaEncadenada.
	 */
	@Test
	public void testListaEnlazada() {

		assertNotNull("El arreglo deber�a estar creado",lista);
		assertEquals(0, lista.darLongitud());
	}

	/**
	 * Prueba del m�todo agregarFinal de la clase ListaEncadenada.
	 */
	@Test
	public void testAgregarFinal()
	{
		//Agrega muchos elementos al final para comprobar si fueron agregados.
		for (int i = 0; i < 10; i++) 
		{
			lista.agregarFinal(new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i),""));
		}
		assertNotNull("El arreglo deber�a existir",lista);
		assertEquals(10, lista.darLongitud());

		//Comprueba un elemento que existe y otro que no.
		assertNotNull("No deber�a ser null", lista.buscar(new Comparendo(1, null, "", "", "", "", "", coordenadas(1,1),"")));
		assertNull("Deber�a ser null", lista.buscar(new Comparendo(2000, null, "", "", "", "", "", coordenadas(-1,-1),"")));

		//Comprueba el primer y el �ltimo elemento.
		assertTrue("Deber�a retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, null, "", "", "", "", "", null,"")).darId()+".",lista.buscar(new Comparendo(1, null, "", "", "", "", "", null,"")).compareTo(new Comparendo(1, null, "", "", "", "", "", null,""))==0);
		assertEquals(lista.darElemento(0), lista.darPrimero().darElemento());
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo().darElemento());
	}

	/**
	 * Prueba del m�todo agregar de la clase ListaEncadenada.
	 */
	@Test
	public void testAgregar()
	{
		for (int i = 0; i < 10; i++) 
		{
			lista.agregar(new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i),""));
		}
		assertNotNull("El arreglo deber�a existir",lista);
		assertEquals(10, lista.darLongitud());

		//Comprueba un elemento que existe y otro que no.
		assertNotNull("No deber�a ser null", lista.buscar(new Comparendo(1, null, "", "", "", "", "", coordenadas(1,1),"")));
		assertNull("Deber�a ser null", lista.buscar(new Comparendo(2000, null, "", "", "", "", "", coordenadas(-1,-1),"")));

		//Comprueba el primer y el �ltimo elemento.
		assertTrue("Deber�a retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, null, "", "", "", "", "", null,"")).darId()+".",lista.buscar(new Comparendo(1, null, "", "", "", "", "", null,"")).compareTo(new Comparendo(1, null, "", "", "", "", "", null,""))==0);
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo().darElemento());
	}

	/**
	 * Prueba del m�todo agregarInicio de la clase ListaEncadenada
	 */
	@Test
	public void testAgregarInicio()
	{
		//Comprueba el caso en que la lista est� vac�a.
		assertNull(lista.darPrimero());
		lista.agregarInicio(new Comparendo(1, null, "", "", "", "", "", null, null));
		assertNotNull(lista.darPrimero().darElemento());
		assertEquals(lista.darElemento(0), lista.darPrimero().darElemento());

		setUp2();

		assertNotNull("El arreglo deber�a existir",lista);
		assertEquals(10, lista.darLongitud());

		//Comprueba un elemento que existe y otro que no.
		assertNotNull("No deber�a ser null", lista.buscar(new Comparendo(1, null, "", "", "", "", "", coordenadas(1,1), null)));
		assertNull("Deber�a ser null", lista.buscar(new Comparendo(2000, null, "", "", "", "", "", coordenadas(-1,-1), null)));

		//Comprueba el primer y el �ltimo elemento.
		assertTrue("Deber�a retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).darId()+".",lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).compareTo(new Comparendo(1, null, "", "", "", "", "", null, null))==0);
		assertEquals(lista.darElemento(0), lista.darPrimero().darElemento());
		assertEquals(lista.darElemento(lista.darLongitud()-1),lista.darUltimo().darElemento());
	}

	/**
	 * Prueba el m�todo buscar en la clase ListaEncadenada.
	 */
	@Test
	public void testBuscar()
	{
		//Prueba de buscar si no hay nada en la lista.
		assertNull("Deber�a ser null", lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)));

		setUp2();
		assertNotNull("El arreglo deber�a existir",lista);
		assertEquals(10, lista.darLongitud());

		//Comprueba un elemento que existe y otro que no.
		assertNotNull("No deber�a ser null", lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)));
		assertNull("Deber�a ser null", lista.buscar(new Comparendo(2000, null, "", "", "", "", "", coordenadas(-1,-1), null)));

		//Comprueba otros casos.
		assertTrue("Deber�a retornar el comparento con el ID 1 pero retorna el comparendo con ID "+lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).darId()+".",lista.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).compareTo(new Comparendo(1, null, "", "", "", "", "", null, null))==0);
	}

	/**
	 * Prueba del m�todo dar longitud de la clase ListaEncadenada.
	 */
	@Test
	public void testDarLongitud()
	{
		//Prueba la longitud cuando no hay elementos.
		assertEquals(0, lista.darLongitud());

		//Prueba la longitud agregando un elemento.
		Comparendo c1 = new Comparendo(0, null, "", "", "", "", "", null, null);
		lista.agregar(c1);
		assertEquals(1, lista.darLongitud());

		//Prueba la longitud eliminando un elemento.
		lista.eliminar(c1);
		assertEquals(0, lista.darLongitud());

		//Prueba la longitud despu�s de agregar muchos elementos.
		setUp2();
		assertEquals(10, lista.darLongitud());
	}

	/**
	 * Prueba del m�todo eliminar de la clase ListaEncadenada.
	 */
	@Test
	public void testEliminar()
	{
		//Prueba en caso de que la lista est� vac�a.
		assertNull(lista.darPrimero());

		//Agregamos 4 comparendos.
		Comparendo c0 = new Comparendo(0, null, "", "", "", "", "", coordenadas(0,0), null);
		Comparendo c1 = new Comparendo(1, null, "", "", "", "", "", coordenadas(1,1), null);
		Comparendo c2 = new Comparendo(2, null, "", "", "", "", "", coordenadas(2,2), null);
		Comparendo c3 = new Comparendo(3, null, "", "", "", "", "", coordenadas(3,3), null);

		lista.agregar(c0);
		lista.agregar(c1);
		lista.agregar(c2);
		lista.agregar(c3);
		assertEquals(4, lista.darLongitud());
		
		//Eliminamos uno de la mitad.
		lista.eliminar(c1);
		assertEquals(3, lista.darLongitud());
		assertNull(lista.buscar(c1));

		//Eliminamos al final.
		lista.eliminar(c3);
		assertEquals(2, lista.darLongitud());
		assertNull(lista.buscar(c3));

		//Eliminamos al inicio.
		lista.eliminar(c0);
		assertEquals(1, lista.darLongitud());
		assertNull(lista.buscar(c0));

		//Eliminamos el �nico elemento de la lista.
		lista.eliminar(c2);
		assertEquals(0, lista.darLongitud());
		assertNull(lista.buscar(c2));

	}

	/**
	 * Prueba del m�todo dar primero de la clase ListaEncadenada.
	 */
	@Test
	public void testDarPrimero()
	{
		//Lista vac�a.
		assertNull(lista.darPrimero());

		//Cuando hay un solo elemento en la lista.
		Comparendo c = new Comparendo(0, null, "", "", "", "", "", null, null);
		lista.agregar(c);
		assertNotNull(lista.darPrimero());

		//Prueba en el resto de casos
		setUp2();
		assertEquals(lista.darElemento(0), lista.darPrimero().darElemento());
		Comparendo c1 = new Comparendo(0, null, "", "", "", "", "", null, null);
		lista.agregarInicio(c1);
		assertEquals(c1, lista.darPrimero().darElemento());

	}

	/**
	 * Prueba del m�todo dar �ltimo de la clase ListaEncadenada
	 */
	@Test
	public void testDarUltimo()
	{
		//Lista vac�a.
		assertNull(lista.darUltimo());

		//Cuando hay un solo elemento en la lista.
		Comparendo c = new Comparendo(0, null, "", "", "", "", "", null, null);
		lista.agregar(c);
		assertNotNull(lista.darUltimo());

		//Comprueba el resto de casos.
		setUp2();
		Comparendo c1 = new Comparendo(9, null, "", "", "", "", "", null, null);
		assertEquals(c1.darId(), lista.darUltimo().darElemento().darId());
	}

	/**
	 * Prueba del m�todo dar elemento de la clase ListaEncadenada
	 */

	@Test
	public void testDarElemento()
	{
		//Caso de lista vac�a.
		assertNull(lista.darElemento(0));
		setUp2();
		assertNotNull("El arreglo deber�a estar creado",lista);
		assertEquals(10, lista.darLongitud());

		assertNull("Deber�a ser null",lista.darElemento(200));

		//Comprueba todos los elementos del for
		Comparendo elemento = null;		
		for (int i = 0; i < lista.darLongitud(); i++) 
		{
			elemento = lista.darElemento(i);
			assertNotNull("Deber�a existir",elemento);
		}

	}

	/**
	 * Prueba del m�todo dar elemento actual de la clase ListaEncadenada
	 */
	@Test
	public void testElementoActual()
	{
		//Caso lista vac�a.
		assertNull(lista.elementoActual());
		
		//Resto de casos
		setUp2();
		assertNull("Deber�a ser Null",lista.elementoActual());
		lista.iniciarRecorrido();
		assertTrue("Actual deber�a ser el primero", lista.elementoActual()==lista.darPrimero().darElemento());
		assertNotNull("No deber�a ser Null",lista.elementoActual());
	}

	/**
	 * Prueba del m�todo iniciar recorrido de la clase ListaEnlanzada.
	 */
	@Test
	public void testIniciarRecorrido()
	{
		//Caso lista vac�a.
		lista.iniciarRecorrido();
		assertNull(lista.darPrimero());
		
		//Resto de casos.
		setUp2();
		lista.iniciarRecorrido();
		assertTrue("Actual deber�a ser el primero", lista.elementoActual()==lista.darPrimero().darElemento());
	}

	/**
	 * Prueba del m�todo avanzar actual de la clase ListaEnlazada.
	 */
	@Test
	public void testAvanzarActual()
	{
		//Caso lista vac�a.
		lista.avanzarActual();
		assertNull(lista.darPrimero());
		
		//Resto de casos.
		setUp2();
		Comparendo c = lista.darPrimero().darElemento();
		lista.avanzarActual();
		assertNull("Deber�a ser null",lista.elementoActual());
		lista.iniciarRecorrido();
		lista.avanzarActual();
		assertTrue("Actual deber�a ser el siguiente del primero", lista.elementoActual()==lista.darElemento(1));
	}

	/**
	 * Prueba del m�todo retroceder actual.
	 */
	@Test
	public void testRetrocederActual()
	{
		//Caso lista vac�a.
		lista.avanzarActual();
		assertNull("Deber�a ser null",lista.elementoActual());
		
		//
		lista.iniciarRecorrido();
		lista.retrocederActual();
		assertNull("Deber�a ser null",lista.elementoActual());
		lista.iniciarRecorrido();
		lista.avanzarActual();
		lista.avanzarActual();
		lista.retrocederActual();
		assertTrue("Actual deber�a ser el siguiente del primero", lista.elementoActual()==lista.darElemento(1));

	}
	
	

}
