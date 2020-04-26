package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxColaCP;
import model.logic.Comparendo;
import model.logic.Ordenamientos;

public class TestMaxColaCP 
{
	/**
	 * Arreglo comparable de comparendos que ser� implementado en las pruebas.
	 */
	Comparable[] arreglo=new Comparable[100];

	/**
	 * Cola de prioridad utilizada para las pruebas.
	 */
	private MaxColaCP<Comparendo> cola;

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
	 * Escenario 1 de prueba, declara un stack vac�o.
	 */
	@Before
	public void setUp1() {
		cola= new MaxColaCP<Comparendo>();
	}

	/**
	 * Escenario 2 de prueba, declara un stack vac�o y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		cola = new MaxColaCP<Comparendo>();


		for (int i = 0; i < 100; ++i) 
		{

			arreglo[i]=new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i), null, i);
		}
		Ordenamientos.shuffle(arreglo);
		for(int i=0; i<arreglo.length;++i)
		{
			cola.agregar((Comparendo )arreglo[i]);
		}
	}

	/**
	 * Prueba del m�todo constructor de la clase MaxColaCP.
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Deber�a existir la cola",cola);
		assertEquals(0,cola.darNumElementos());
		assertEquals(true,cola.esVacia());
		assertNull("No deber�a existir", cola.darMax());
		assertNotNull("Deber�a existir", cola.darCola());
		assertNull("No deber�a existir", cola.sacarMax());
		

		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La lista deber�a existir", cola);
		assertEquals(100, cola.darNumElementos());
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), cola.darMax().darLatitud(),0);
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), cola.sacarMax().darLatitud(),0);
		assertEquals(false, cola.esVacia());
		assertNotNull("No deber�a existir", cola.darListaCola());
		assertNotNull("No deber�a existir", cola.darCola());
	}

	/**
	 * Prueba del m�todo darNumElementos() de la clase MaxColaCP.
	 */
	@Test
	public void TestDarNumElementos()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,cola.darNumElementos());
		cola.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(1, cola.darNumElementos());
		cola.sacarMax();
		assertEquals(0, cola.darNumElementos());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(100, cola.darNumElementos());
	}

	/**
	 * Prueba del m�todo agregar() de la clase MaxColaCP..
	 */
	@Test
	public void TestAgregar()
	{
		setUp2();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		for(int i=0; i<100;++i)
		{
			Comparendo actual=(Comparendo) cola.darCola().darElementos().darElemento(i);
			assertEquals(new Comparendo(100-i, null, "", "", "", "", "", coordenadas(99-i,99-i), null, i).darLatitud(), actual.darLatitud(),0);
		}
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), cola.darMax().darLatitud(),0);
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), cola.sacarMax().darLatitud(),0);
		assertEquals(false, cola.esVacia());
	}

	/**
	 * Prueba del m�todo sacarMax() de la clase MaxColaCP.
	 */
	@Test
	public void TestSacarMax()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,cola.darNumElementos());
		cola.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(1, cola.darNumElementos());
		cola.sacarMax();
		assertEquals(0, cola.darNumElementos());
		setUp2();
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		while(cola.darNumElementos()>0)
		{
			cola.sacarMax();
		}
	}
	
	/**
	 * Prueba del m�todo darMax() de la clase MaxColaCP.
	 */
	@Test
	public void TestDarMax()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,cola.darNumElementos());
		cola.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0).darLatitud(),cola.darMax().darLatitud(),0);
		cola.sacarMax();
		assertEquals(0, cola.darNumElementos());
		setUp2();
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		int valor=cola.darNumElementos();
		while(valor>0)
		{
			assertEquals(new Comparendo(valor-1, null, "", "", "", "", "", coordenadas(valor-1,valor-1), null, valor).darLatitud(),cola.darMax().darLatitud(),0);
			cola.sacarMax();
			valor--;
		}
	}

	/**
	 * Prueba del m�todo esVacia() de la clase MaxColaCP.
	 */
	@Test
	public void TestEsVacia()
	{
		//Comprueba si la lista est� vac�a y agregando y eliminado un dato.
		assertEquals(true, cola.esVacia());
		cola.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(false, cola.esVacia());
		cola.sacarMax();
		assertEquals(true, cola.esVacia());
		//Comprobamos con m�s datos.
		setUp2();
		assertEquals(false, cola.esVacia());
		while(cola.darNumElementos()>0)
		{
			assertEquals(false, cola.esVacia());
			cola.sacarMax();
		}
		assertEquals(true, cola.esVacia());
	}
}
