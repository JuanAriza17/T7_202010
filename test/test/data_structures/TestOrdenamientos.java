package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.IListaEncadenada;
import model.data_structures.ListaEncadenada;
import model.logic.Ordenamientos;

public class TestOrdenamientos 
{
	/**
	 * Atributo de la clase lista que será utilizado en las pruebas.
	 */
	private IListaEncadenada<Integer> lista;
	
	/**
	 * Escenario 1 de pruebas.
	 * Genera una lista de 5000 datos barajada aleatoriamente.
	 */
	@Before
	public void setUpAleatorio() 
	{
		lista = new ListaEncadenada<Integer>();
		for (int i = 0; i < 5000; i++) 
		{
			int numero = (int)Math.random()*5000 +1;
			lista.agregarFinal(numero);
		}
	}

	/**
	 * Escenario 2 de pruebas.
	 * Genera una lista de 5000 datos ordenada ascendentemente.
	 */
	public void setUpAscendente() 
	{
		lista = new ListaEncadenada<Integer>();

		for (int i = 0; i < 5000; i++) 
		{
			lista.agregarFinal(i);
		}
	}
	
	/**
	 * Escenario 3 de pruebas.
	 * Genera una lista de 5000 datos ordenada descendentemente.
	 */
	public void setUpDescendentemente() {
		lista = new ListaEncadenada<Integer>();

		for (int i = 4999; i >= 0; i--) 
		{
			lista.agregarFinal(i);
		}
	}
	
	
	/**
	 * Test del algoritmo de ordenamiento ShellSort de la clase Ordenamientos.
	 */
	@Test
	public void testShell()
	{
		assertEquals(5000,lista.darLongitud());
		Comparable[] a = lista.darArreglo();
		long startTime = System.currentTimeMillis();
		Ordenamientos.shellSort(a);
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("Tiempo shell en aleatorio con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpAscendente();
		
		a = lista.darArreglo();
		
		startTime = System.currentTimeMillis();
		Ordenamientos.shellSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo shell en ascendente con 5000 datos: "+duration+" milisegundos" );
 		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpDescendentemente();
		
		a = lista.darArreglo();
		startTime = System.currentTimeMillis();
		Ordenamientos.shellSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo shell en descendente con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
	}

	/**
	 * Test del algoritmo de ordenamiento MergeSort de la clase Ordenamientos.
	 */
	@Test
	public void testMerge()
	{
		assertEquals(5000,lista.darLongitud());
		Comparable[] a = lista.darArreglo();
		long startTime = System.currentTimeMillis();
		Ordenamientos.mergeSort(a);
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("Tiempo merge en aleatorio con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpAscendente();
		
		a = lista.darArreglo();
		
		startTime = System.currentTimeMillis();
		Ordenamientos.mergeSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo merge en ascendente con 5000 datos: "+duration+" milisegundos" );
 		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpDescendentemente();
		
		a = lista.darArreglo();
		startTime = System.currentTimeMillis();
		Ordenamientos.mergeSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo merge en descendente con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
	}
	
	/**
	 * Test del algoritmo de ordenamiento QuickSort de la clase Ordenamientos.
	 */
	@Test
	public void testQuick()
	{
		assertEquals(5000,lista.darLongitud());
		Comparable[] a = lista.darArreglo();
		long startTime = System.currentTimeMillis();
		Ordenamientos.quickSort(a);
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("Tiempo quick en aleatorio con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpAscendente();
		
		a = lista.darArreglo();
		
		startTime = System.currentTimeMillis();
		Ordenamientos.quickSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo quick en ascendente con 5000 datos: "+duration+" milisegundos" );
 		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
		
		setUpDescendentemente();
		
		a = lista.darArreglo();
		startTime = System.currentTimeMillis();
		Ordenamientos.quickSort(a);
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		System.out.println("Tiempo quick en descendente con 5000 datos: "+duration+" milisegundos" );
		for (int i = 0; i < a.length-1; i++) 
		{
			if(a[i].compareTo(a[i+1])>0)
				fail("Esta mal ordenada");
		}
	}


}
