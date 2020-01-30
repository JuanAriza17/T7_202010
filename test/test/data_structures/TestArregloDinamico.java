package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(""+i);
		}
	}

	@Test
	public void testArregloDinamico() {
		// TODO
		
		assertNotNull("El arreglo debería estar creado",arreglo);
		assertEquals(100, arreglo.darCapacidad());
		assertEquals(0,arreglo.darTamano());
	}

	@Test
	public void testDarElemento() {
		setUp2();
		// TODO
		assertNotNull("El arreglo debería estar creado",arreglo);
		assertEquals(200, arreglo.darCapacidad());
		assertEquals(200,arreglo.darTamano());
		
		assertNull("Debería ser null",arreglo.darElemento(200));
		
		String elemento = null;		
		for (int i = 0; i < arreglo.darTamano(); i++) 
		{
			elemento = (String)arreglo.darElemento(i);
			assertTrue("El elemento en la posición "+i+" debería ser "+i+" pero es "+elemento+".", elemento.equals(""+i));
		}
		
	}

}
