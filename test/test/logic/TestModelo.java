package test.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.logic.Comparendo;
import model.logic.Modelo;

public class TestModelo {


	/**
	 * Atributo del modelo
	 */
	private Modelo modelo;

	/**
	 * Ruta archivo
	 */
	public final static String RUTA = "./data/comparendos_dei_2018_small.geojson";

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
	 * Escenario 1
	 */
	@Before
	public void setUp1() {
		modelo= new Modelo();
	}

	/**
	 * Escenario 2.
	 * Carga de los comparendos
	 * @throws FileNotFoundException
	 * @throws ParseException
	 * @throws UnsupportedEncodingException 
	 */
	public void setUp2() throws FileNotFoundException, ParseException, UnsupportedEncodingException {
		modelo = new Modelo();
		modelo.cargarComparendos(RUTA);
	}

	/**
	 * Escenario 3.
	 * Agrega a la lista comparendos.
	 */
	public void setUp3() {
		modelo = new Modelo();
		for (int i = 0; i < 10; i++) 
		{
			modelo.agregarFinal(new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i), null));
		}
	}

	/**
	 * Test que comprueba la creaci�n del modelo
	 */
	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darLongitud());  // Modelo con 0 elementos presentes.
	}

	/**
	 * Test que comprueba el metodo darLongitud()
	 */
	@Test
	public void testDarLongitud() {
		assertEquals(0, modelo.darLongitud());

		try
		{
			setUp2();
			assertNotNull("El arreglo deber�a existir",modelo);
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
		catch(Exception e)
		{
			e.printStackTrace();
		}

		setUp3();
		assertNotNull("El arreglo deber�a existir",modelo);
		assertEquals(10, modelo.darLongitud());

	}

	/**
	 * Test que comprueba la creaci�n de comparendos.
	 */
	@Test
	public void testAgregar() {
		assertEquals(0, modelo.darLongitud());

		Comparendo c = new Comparendo(0, null, "", "", "", "", "", new double[2], null);
		modelo.agregarFinal(c);
		assertEquals(1,modelo.darLongitud());
		assertTrue("Deber�a retornar el comparendo pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).compareTo(c)==0);

		for (int i = 1; i < 200; i++) 
		{
			c = new Comparendo(i, null, "", "", "", "", "",  new double[2], null);
			modelo.agregar(c);
			assertEquals(i+1,modelo.darLongitud());
			assertTrue("Deber�a retornar "+i+ " pero retorna "+modelo.buscar(c)+".",modelo.buscar(c).compareTo(c)==0);
		}

	}

	/**
	 * Test que comprueba la b�squeda de un comparendo.
	 */
	@Test
	public void testBuscar() {
		try
		{
			setUp2();
			assertNotNull("El arreglo deber�a existir",modelo);
			assertEquals(20, modelo.darLongitud());

			assertNull("Deber�a ser null", modelo.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)));

			Comparendo c = new Comparendo(29042, null, "", "", "", "", "", null, null);
			assertNotNull("No deber�a ser null", modelo.buscar(c));

			assertTrue("Deber�a retornar el comparento con el ID 29042 pero retorna el comparendo con ID "+modelo.buscar(c).darId()+".",modelo.buscar(c).compareTo(c)==0);
			c = new Comparendo(29042, null, "", "", "", "", "", null, null);
			assertTrue("Deber�a retornar el comparento con el ID 29042 pero retorna el comparendo con ID "+modelo.buscar(c).darId()+".",modelo.buscar(c).compareTo(c)==0);
		}
		catch(Exception e)
		{
			assertEquals(0, modelo.darLongitud());
		}

		setUp3();
		assertNotNull("El arreglo deber�a existir",modelo);
		assertEquals(10, modelo.darLongitud());

		assertNotNull("No deber�a ser null", modelo.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)));
		assertNull("Deber�a ser null", modelo.buscar(new Comparendo(2000, null, "", "", "", "", "", coordenadas(-1,-1), null)));

		assertTrue("Deber�a retornar el comparento con el ID 1 pero retorna el comparendo con ID "+modelo.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).darId()+".",modelo.buscar(new Comparendo(1, null, "", "", "", "", "", null, null)).compareTo(new Comparendo(1, null, "", "", "", "", "", null, null))==0);



	}

	/**
	 * Test del m�todo eliminar de la clase Modelo.
	 */
	@Test
	public void testEliminar() {

		try
		{
			setUp2();

			assertNotNull("El arreglo deber�a existir",modelo);
			assertEquals(20, modelo.darLongitud());

			Comparendo c = new Comparendo(0, null, "", "", "", "", "", null, null);

			Comparendo eliminar = modelo.eliminar(c);
			assertNull("Deber�a ser null", eliminar);
			assertEquals(20, modelo.darLongitud());

			c = new Comparendo(29049, null, "", "", "", "", "", null, null); 

			Comparendo dato = modelo.buscar(c);
			eliminar = modelo.eliminar(c);
			assertTrue("Deber�an ser el mismo dato",dato.equals(eliminar));
			assertEquals(19, modelo.darLongitud());
			assertNull("Debe�a ser null", modelo.buscar(c));

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
