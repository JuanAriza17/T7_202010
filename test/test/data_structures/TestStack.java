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
	 * Escenario 1 de prueba, declara un stack vac�o.
	 */
	@Before
	public void setUp1() {
		stack= new Stack<Comparendo>();
	}

	/**
	 * Escenario 2 de prueba, declara un stack vac�o y agrega 10 comparendos a la prueba.
	 */
	public void setUp2() {
		stack = new Stack<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			stack.push(new Comparendo(i, null, "", "", "", "", "", null, null, i));
		}
	}

	/**
	 * Prueba del m�todo constructor de la clase Stack.
	 */
	@Test
	public void TestConstructor()
	{
		//Revisa el contructor
		assertNotNull("Deber�a existir la lista",stack);
		assertNull("No deber�a existir", stack.peek());
		assertNull("No deber�a existir", stack.pop());
		
		//Se comprueba con el escenario 2.
		setUp2();
		assertNotNull("La lista deber�a existir", stack);
		assertEquals(new Comparendo(9, null, "", "", "", "", "", null, null, 0).darId(), stack.peek().darId());
		assertEquals(10, stack.size());
		assertEquals(false, stack.isEmpty());
	}

	/**
	 * Prueba del m�todo push de la clase Stack.
	 */
	@Test
	public void TestPush()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		stack.push(new Comparendo(0, null, "", "", "", "", "", null, null, 0));

		//Comprobamos que se haya agregado correctamente, se comprueba el caso de un solo elemento en la lista.
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), stack.peek().darId());

		//Comprobamos que se haya agregado correctamente, se comprueba cualquier otro caso de la lista.
		setUp2();
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, null, "", "", "", "", "", null, null, 0).darId(), stack.peek().darId());
	}

	/**
	 * Prueba del m�todo pop de la clase Stack.
	 */
	@Test
	public void TestPop()
	{
		//Comprobamos tama�o y que la lista este vac�a
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		assertNull(stack.pop());

		//Agregamos un elemento y posteriormente realizamos pop
		stack.push(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), stack.pop().darId());
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());

		setUp2();
		//Comprueba el pop para cualquiero otro caso de la lista
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, null, "", "", "", "", "", null, null, 0).darId(), stack.pop().darId());
		assertEquals(9, stack.size());
	}

	/**
	 * Prueba del m�todo peek de la clase Stack.
	 */
	@Test
	public void TestPeek()
	{
		//Comprobamos tama�o, el tama�o no se modifica al usar peek
		assertEquals(true, stack.isEmpty());
		assertEquals(0, stack.size());
		assertNull(stack.peek());

		//Agregamos un elemento y posteriormente realizamos peek
		stack.push(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(new Comparendo(0, null, "", "", "", "", "", null, null, 0).darId(), stack.peek().darId());
		assertEquals(false, stack.isEmpty());
		assertEquals(1, stack.size());
		
		//Comprobamos para cualquier otro caso que no sea el �ltimo
		setUp2();
		assertEquals(false, stack.isEmpty());
		assertEquals(10, stack.size());
		assertEquals(new Comparendo(9, null, "", "", "", "", "", null, null, 0).darId(), stack.peek().darId());
		assertEquals(10, stack.size());
	}
	
	/**
	 * Prueba del m�todo size de la clase Stack.
	 */
	@Test
	public void TestSize()
	{
		//Se comprueba que se agrega un elemento al inicio.
		assertEquals(0,stack.size());
		stack.push(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(1, stack.size());
		
		//Se comprueba que el tama�o disminuye si se elimina el �nico elemento en la lista.
		assertEquals(1, stack.size());
		stack.pop();
		assertEquals(0, stack.size());
		
		setUp2();
		//Comprobamos con m�s datos.
		assertEquals(10,stack.size());
		stack.pop();
		assertEquals(9, stack.size());
		stack.push(new Comparendo(11, null, "", "", "", "", "", null, null, 0));
		assertEquals(10, stack.size());
	}
	
	/**
	 * Prueba del m�todo isEmpty de la clase Stack.
	 */
	@Test
	public void TestIsEmpty()
	{
		//Se comprueba si la lista est� vac�a.
		assertEquals(true, stack.isEmpty());
		
		//Se agrega un elemento para comprobar si deja de estar vac�a
		stack.push(new Comparendo(0, null, "", "", "", "", "", null, null, 0));
		assertEquals(false, stack.isEmpty());
		
		//Se elimina para comprobar el cambio de lista NO vac�a a vac�a.
		stack.pop();
		assertEquals(true, stack.isEmpty());
		
		//Se comprueba con muchos elementos.
		setUp2();
		assertEquals(false, stack.isEmpty());
		stack.pop();
		assertEquals(false, stack.isEmpty());
	}
}
