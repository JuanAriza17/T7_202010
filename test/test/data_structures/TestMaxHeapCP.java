package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxHeapCP;
import model.logic.Comparendo;
import model.logic.Ordenamientos;
import model.logic.Ordenamientos;

public class TestMaxHeapCP 
{
	/**
	 * Arreglo comparable de comparendos que ser� implementado en las pruebas.
	 */
	Comparable[] arreglo=new Comparable[100];

	/**
	 * Cola de prioridad utilizada para las pruebas.
	 */
	private MaxHeapCP<Comparendo> heap;

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
		heap= new MaxHeapCP<Comparendo>(100);
	}

	/**
	 * Escenario 2 de prueba, declara un stack vac�o y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		heap = new MaxHeapCP<Comparendo>(100);


		for (int i = 0; i < 100; ++i) 
		{

			arreglo[i]=new Comparendo(i, null, "", "", "", "", "", coordenadas(i,i), null, i);
		}
		Ordenamientos.shuffle(arreglo);
		for(int i=0; i<arreglo.length;++i)
		{
			heap.agregar((Comparendo )arreglo[i]);
		}
	}

	/**
	 * Prueba del m�todo constructor de la clase MaxColaCP.
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Deber�a existir la cola",heap);
		assertEquals(0,heap.darNumElementos());
		assertEquals(true,heap.esVacia());
		assertNull("No deber�a existir", heap.darMax());
		assertNotNull("Deber�a existir", heap.darArreglo());
		assertNull("No deber�a existir", heap.sacarMax());
		

		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La lista deber�a existir", heap);
		assertEquals(100, heap.darNumElementos());
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), heap.darMax().darLatitud(),0);
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), heap.sacarMax().darLatitud(),0);
		assertEquals(false, heap.esVacia());
		assertNotNull("No deber�a existir", heap.darArreglo());
	}

	/**
	 * Prueba del m�todo darNumElementos() de la clase MaxColaCP.
	 */
	@Test
	public void TestDarNumElementos()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,heap.darNumElementos());
		heap.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(1, heap.darNumElementos());
		heap.sacarMax();
		assertEquals(0, heap.darNumElementos());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(100, heap.darNumElementos());
	}

	/**
	 * Prueba del m�todo agregar() de la clase MaxColaCP..
	 */
	@Test
	public void TestAgregar()
	{
	 
		for(int i=0; i<100;i++)
		{
			heap.agregar(new Comparendo(100-i, null, "", "", "", "", "", coordenadas(99-i,99-i), null, i));
		}
		Comparable[] a= heap.darArreglo();
		//Comprueba agregar muchos elementos y comprueba que el orden sea el indicado.
		for(int i=0; i<100;++i)
		{
			Comparendo actual= (Comparendo) a[i+1];
			assertEquals(new Comparendo(100-i, null, "", "", "", "", "", coordenadas(99-i,99-i), null, i).darLatitud(), actual.darLatitud(),0);
		}
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), heap.darMax().darLatitud(),0);
		assertEquals(new Comparendo(99, null, "", "", "", "", "", coordenadas(99,99), null, 0).darLatitud(), heap.sacarMax().darLatitud(),0);
		assertEquals(false, heap.esVacia());
	}

	/**
	 * Prueba del m�todo sacarMax() de la clase MaxColaCP.
	 */
	@Test
	public void TestSacarMax()
	{
		
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,heap.darNumElementos());
		heap.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(1, heap.darNumElementos());
		heap.sacarMax();
		assertEquals(0, heap.darNumElementos());
		setUp2();
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		while(heap.darNumElementos()>0)
		{
			heap.sacarMax();
		}
	}
	
	/**
	 * Prueba del m�todo darMax() de la clase MaxColaCP.
	 */
	@Test
	public void TestDarMax()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(0,heap.darNumElementos());
		heap.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0).darLatitud(),heap.darMax().darLatitud(),0);
		heap.sacarMax();
		assertEquals(0, heap.darNumElementos());
		setUp2();
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		int valor=heap.darNumElementos();
		while(valor>0)
		{
			assertEquals(new Comparendo(valor-1, null, "", "", "", "", "", coordenadas(valor-1,valor-1), null, valor).darLatitud(),heap.darMax().darLatitud(),0);
			heap.sacarMax();
			valor--;
		}
	}

	/**
	 * Prueba del m�todo size de la clase Stack.
	 */
	@Test
	public void TestEsVacia()
	{
		//Comprueba si la lista est� vac�a y agregando y eliminado un dato.
		assertEquals(true, heap.esVacia());
		heap.agregar(new Comparendo(100, null, "", "", "", "", "", coordenadas(0,0), null, 0));
		assertEquals(false, heap.esVacia());
		heap.sacarMax();
		assertEquals(true, heap.esVacia());
		//Comprobamos con m�s datos.
		setUp2();
		assertEquals(false, heap.esVacia());
		while(heap.darNumElementos()>0)
		{
			assertEquals(false, heap.esVacia());
			heap.sacarMax();
		}
		assertEquals(true, heap.esVacia());
	}
}
