package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Queue;
import model.logic.Comparendo;
public class TestQueue {

	/**
	 * Queue utilizada para las pruebas.
	 */
	private Queue<Comparendo> queue;

	/**
	 * Escenario 1 de prueba, declara un queue vac�o.
	 */
	@Before
	public void setUp1() {
		queue= new Queue<Comparendo>();
	}

	/**
	 * Escenario 2 de prueba, declara un queue vac�o y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		queue = new model.data_structures.Queue<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			queue.enqueue(new Comparendo(i, null, "", "", "", "", "", null, null, i));
		}
	}

	/**
	 * Prueba el m�todo constructor de la clase Queue
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Deber�a existir la lista",queue);
		assertNull("No deber�a existir", queue.peek());
		assertNull("No deber�a existir", queue.dequeue());

		//Revisa el segundo escenario.
		setUp2();
		assertNotNull("La lista deber�a existir", queue);
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), queue.peek().darId());
		assertEquals(10, queue.size());
		assertEquals(false, queue.isEmpty());
	}

	/**
	 * Prueba del m�todo enqueue en la clase Queue
	 */
	@Test
	public void TestEnqueue()
	{
		//Caso lista vac�a.
		assertNull(queue.dequeue());

		//Caso agregando otros elementos.
		setUp2();
		queue.enqueue(new Comparendo(11, null, "", "", "", "", "", null, null, 0));
		assertEquals(11,queue.size());

	}

	/**
	 * Prueba del m�todo dequeue en la clase Queue
	 */
	@Test
	public void TestDequeue()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(true, queue.isEmpty());
		assertEquals(0, queue.size());
		queue.enqueue(new Comparendo(0, null, "", "", "", "", "", null, null, 0));

		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), queue.peek().darId());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(false, queue.isEmpty());
		assertEquals(10, queue.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), queue.peek().darId());
	}
	
	/**
	 * Prueba del m�todo isEmpty de la clase Queue.
	 */
	@Test
	public void TestIsEmpty()
	{
		//Se comprueba si la lista est� vac�a.
		assertEquals(true, queue.isEmpty());
		
		//Se agrega un elemento para comprobar si deja de estar vac�a
		queue.enqueue(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(false, queue.isEmpty());
		
		//Se elimina para comprobar el cambio de lista NO vac�a a vac�a.
		queue.dequeue();
		assertEquals(true, queue.isEmpty());
		
		//Se comprueba con muchos elementos.
		setUp2();
		assertEquals(false, queue.isEmpty());
		queue.dequeue();
		assertEquals(false, queue.isEmpty());
	}
	
	/**
	 * Prueba del m�todo size de la clase Queue.
	 */
	@Test
	public void TestSize()
	{
		//Se comprueba que se agrega un elemento al inicio.
		assertEquals(0,queue.size());
		queue.enqueue(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(1, queue.size());
		
		//Se comprueba que el tama�o disminuye si se elimina el �nico elemento en la lista.
		assertEquals(1, queue.size());
		queue.dequeue();
		assertEquals(0, queue.size());
		
		setUp2();
		//Comprobamos con m�s datos.
		assertEquals(10,queue.size());
		queue.dequeue();
		assertEquals(9, queue.size());
		queue.enqueue(new Comparendo(11, null, "", "", "", "", "", null, null, 0));
		assertEquals(10, queue.size());
	}
	
	/**
	 * Prueba del m�todo peek de la clase Queue.
	 */
	@Test
	public void TestPeek()
	{
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		assertEquals(true, queue.isEmpty());
		assertEquals(0, queue.size());
		assertNull(queue.peek());

		//Agregamos un elemento y posteriormente realizamos peek
		queue.enqueue(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), queue.peek().darId());
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		
		//Comprobamos para cualquier otro caso que no sea el �ltimo
		setUp2();
		assertEquals(false, queue.isEmpty());
		assertEquals(10, queue.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), queue.peek().darId());
		assertEquals(10, queue.size());
	}

}
