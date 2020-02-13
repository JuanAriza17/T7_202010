package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Stack;
import model.logic.Comparendo;

public class TestStack 
{
	/**
	 * Stack utilizada para las pruebas.
	 */
	private Stack<Comparendo> stack;

	/**
	 * Escenario 1 de prueba, declara un stack vacío.
	 */
	@Before
	public void setUp1() {
		stack= new Stack<Comparendo>();
	}

	/**
	 * Escenario 2 de prueba, declara un stack vacío y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		stack = new Stack<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			stack.push(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}

	/**
	 * Prueba del método constructor de la clase Stack.
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Debería existir la lista",stack);
		assertNull("No debería existir", stack.peek());
		assertNull("No debería existir", stack.pop());
		
		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La lista debería existir", stack);
		assertEquals(new Comparendo(9, "", "", "", "", "", "", null).darId(), stack.peek().darId());
		assertEquals(10, stack.size());
		assertEquals(false, stack.isEmpty());
	}

	/**
	 * Prueba del método push de la clase Stack.
	 */
	@Test
	public void TestPush()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		stack.push(new Comparendo(0, "", "", "", "", "", "", null));

		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), stack.peek().darId());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, "", "", "", "", "", "", null).darId(), stack.peek().darId());
	}

	/**
	 * Prueba del método pop de la clase Stack.
	 */
	@Test
	public void TestPop()
	{
		//Comprobamos tamaño y que la lista este vacía
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		assertNull(stack.pop());

		//Agregamos un elemento y posteriormente realizamos pop
		stack.push(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), stack.pop().darId());
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());

		setUp2();
		//Comprueba el pop para cualquiero otro caso de la lista
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, "", "", "", "", "", "", null).darId(), stack.pop().darId());
		assertEquals(9, stack.size());
	}

	/**
	 * Prueba del método peek de la clase Stack.
	 */
	@Test
	public void TestPeek()
	{
		//Comprobamos tamaño, el tamaño no se modifica al usar peek
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		assertNull(stack.peek());

		//Agregamos un elemento y posteriormente realizamos peek
		stack.push(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, "", "", "", "", "", "", null).darId(), stack.peek().darId());
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		
		//Comprobamos para cualquier otro caso que no sea el último
		setUp2();
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, "", "", "", "", "", "", null).darId(), stack.peek().darId());
		assertEquals(10, stack.size());
	}
	
	/**
	 * Prueba del método size de la clase Stack.
	 */
	@Test
	public void TestSize()
	{
		//Se comprueba que se agrega un elemento al inicio.
		assertEquals(0,stack.size());
		stack.push(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(1, stack.size());
		
		//Se comprueba que el tamaño disminuye si se elimina el único elemento en la lista.
		assertEquals(1, stack.size());
		stack.pop();
		assertEquals(0, stack.size());
		
		setUp2();
		//Comprobamos con más datos.
		assertEquals(10,stack.size());
		stack.pop();
		assertEquals(9, stack.size());
		stack.push(new Comparendo(11, "", "", "", "", "", "", null));
		assertEquals(10, stack.size());
	}
	
	/**
	 * Prueba del método isEmpty de la clase Stack.
	 */
	@Test
	public void TestIsEmpty()
	{
		//Se comprueba si la lista está vacía.
		assertEquals(true, stack.isEmpty());
		
		//Se agrega un elemento para comprobar si deja de estar vacía
		stack.push(new Comparendo(0, "", "", "", "", "", "", null));
		assertEquals(false, stack.isEmpty());
		
		//Se elimina para comprobar el cambio de lista NO vacía a vacía.
		stack.pop();
		assertEquals(true, stack.isEmpty());
		
		//Se comprueba con muchos elementos.
		setUp2();
		assertEquals(false, stack.isEmpty());
		stack.pop();
		assertEquals(false, stack.isEmpty());
	}
}
