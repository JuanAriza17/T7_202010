package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.logic.Comparendo;
public class TestQueue {

	/**
	 * Queue utilizada para las pruebas.
	 */
	private model.data_structures.Queue<Comparendo> queue;

	/**
	 * Escenario 1 de prueba, declara un queue vacío.
	 */
	@Before
	public void setUp1() {
		queue= new model.data_structures.Queue<Comparendo>();
	}

	/**
	 * Escenario 2 de prueba, declara un queue vacío y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		queue = new model.data_structures.Queue<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			queue.enqueue(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}

	/**
	 * Prueba el método constructor de la clase Queue
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Debería existir la lista",queue);
		assertNull("No debería existir", queue.peek());
		assertNull("No debería existir", queue.dequeue());

		//Revisa el segundo escenario.
		setUp2();
		assertNotNull("La lista debería existir", queue);
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), queue.peek().darId());
		assertEquals(10, queue.size());
		assertEquals(false, queue.isEmpty());
	}

	/**
	 * Prueba del método enqueue en la clase Queue
	 */
	@Test
	public void TestEnqueue()
	{
		//Caso lista vacía.
		assertNull(queue.dequeue());

		//Caso agregando otros elementos.
		setUp2();
		queue.enqueue(new Comparendo(11, "", "", "", "", "", "", null));
		assertEquals(11,queue.size());

	}

	/**
	 * Prueba del método dequeue en la clase Queue
	 */
	@Test
	public void TestDequeue()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(true, queue.isEmpty());
		assertEquals(0, queue.size());
		queue.enqueue(new Comparendo(0, "", "", "", "", "", "", null));

		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), queue.peek().darId());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(false, queue.isEmpty());
		assertEquals(10, queue.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), queue.peek().darId());
	}
	
	/**
	 * Prueba del método isEmpty de la clase Queue.
	 */
	@Test
	public void TestIsEmpty()
	{
		//Se comprueba si la lista está vacía.
		assertEquals(true, queue.isEmpty());
		
		//Se agrega un elemento para comprobar si deja de estar vacía
		queue.enqueue(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(false, queue.isEmpty());
		
		//Se elimina para comprobar el cambio de lista NO vacía a vacía.
		queue.dequeue();
		assertEquals(true, queue.isEmpty());
		
		//Se comprueba con muchos elementos.
		setUp2();
		assertEquals(false, queue.isEmpty());
		queue.dequeue();
		assertEquals(false, queue.isEmpty());
	}
	
	/**
	 * Prueba del método size de la clase Queue.
	 */
	@Test
	public void TestSize()
	{
		//Se comprueba que se agrega un elemento al inicio.
		assertEquals(0,queue.size());
		queue.enqueue(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(1, queue.size());
		
		//Se comprueba que el tamaño disminuye si se elimina el único elemento en la lista.
		assertEquals(1, queue.size());
		queue.dequeue();
		assertEquals(0, queue.size());
		
		setUp2();
		//Comprobamos con más datos.
		assertEquals(10,queue.size());
		queue.dequeue();
		assertEquals(9, queue.size());
		queue.enqueue(new Comparendo(11, "", "", "", "", "", "", null));
		assertEquals(10, queue.size());
	}
	
	/**
	 * Prueba del método peek de la clase Queue.
	 */
	@Test
	public void TestPeek()
	{
		//Comprobamos tamaño, el tamaño no se modifica al usar peek
		assertEquals(true, queue.isEmpty());
		assertEquals(0, queue.size());
		assertNull(queue.peek());

		//Agregamos un elemento y posteriormente realizamos peek
		queue.enqueue(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), queue.peek().darId());
		assertEquals(false, queue.isEmpty());
		assertEquals(1, queue.size());
		
		//Comprobamos para cualquier otro caso que no sea el último
		setUp2();
		assertEquals(false, queue.isEmpty());
		assertEquals(10, queue.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), queue.peek().darId());
		assertEquals(10, queue.size());
	}

}
