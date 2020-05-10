package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Arco;
import model.data_structures.GrafoNoDirigido;
import model.data_structures.IGrafoNoDirigido;
import model.data_structures.Vertice;
import model.logic.Modelo;
import model.logic.UbicacionGeografica;

public class TestGrafoNoDirigido 
{
	/**
	 * Atributo de la clase GrafoNoDirigido.
	 */
	IGrafoNoDirigido<Integer,Vertice<Integer,UbicacionGeografica>>grafo;
	
	/**
	 * Atributo del tamaño.
	 */
	private final static int TAMANO_GRAFO=100;

	/**
	 * Atributo de la clase modelo.
	 */
	Modelo modelo=new Modelo();
	
	/**
	 * Escenario 1 de prueba.
	 */
	@Before
	public void setUp1()
	{
		grafo=new GrafoNoDirigido<Integer, Vertice<Integer,UbicacionGeografica>>(TAMANO_GRAFO);
	}

	/**
	 * Escenario 2 de prueba.
	 */
	public void setUp2() 
	{
		grafo=new GrafoNoDirigido<Integer, Vertice<Integer,UbicacionGeografica>>(TAMANO_GRAFO);
		for(int i=0; i<1000;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=new Vertice<Integer, UbicacionGeografica>(i, new UbicacionGeografica(Math.random()*100,Math.random()*10));
			grafo.addVertex(i, vertice);
		}
		for(int i=0; i<3000; ++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice1= grafo.getInfoVertex((int) (Math.random()*1000));
			Vertice<Integer,UbicacionGeografica>vertice2= grafo.getInfoVertex((int) (Math.random()*1000));
			if(!Integer.toString(vertice1.darId()).equals(Integer.toString(vertice2.darId())))
			{
				grafo.addEdge(vertice1.darId(), vertice2.darId(), modelo.distanceHaversine(vertice1.darInfo().darLatidud(), vertice1.darInfo().darLongitud(), vertice2.darInfo().darLatidud(), vertice2.darInfo().darLatidud()));
			}
		}
	}

	/**
	 * Prueba del método constructor de la clase HashLinearProbing.
	 */
	@Test
	public void TestConstructor()
	{
		int aleatorio=(int) (Math.random()*1000);
		assertNotNull(grafo.darTabla());
		assertEquals(0,grafo.E());
		assertEquals(0,grafo.V());
		setUp2();
		grafo.dfs(aleatorio);
		assertNotNull(grafo.getCC(aleatorio));
		assertNotNull(grafo.getInfoVertex(aleatorio));
		assertNotNull(grafo.adj(aleatorio));
		assertNotNull(grafo.darTabla());
		assertNotNull(grafo.cc());
		assertEquals(1000,grafo.V());
		assertNotNull(grafo.E());
	}
	
	/**
	 * Prueba del método V() de la clase GrafoNoDirigido.
	 */
	@Test 
	public void testV()
	{
		assertEquals(0, grafo.V());
		setUp2();
		assertEquals(1000, grafo.V());
		Vertice<Integer, UbicacionGeografica>vertice=new Vertice<Integer,UbicacionGeografica>(1000,new UbicacionGeografica(1000,1000));
		grafo.addVertex(1000,vertice);
		assertEquals(1001, grafo.V());
	}
	
	/**
	 * Prueba del método E() de la clase GrafoNoDirigido.
	 */
	public void testE()
	{
		assertEquals(0, grafo.E());
		setUp2();
		assertTrue(grafo.V()<=3000);
		for(int i=0; i<10;++i)
		{
			grafo.addEdge(i, 1000, i);
		}
		assertTrue(grafo.V()<=3010);
		assertFalse(grafo.V()<2800);
	}
	
	/**
	 * Prueba del método addEdge() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testAddEdge()
	{
		Vertice<Integer, UbicacionGeografica>vertice1=new Vertice<Integer,UbicacionGeografica>(1000,new UbicacionGeografica(1000,1000));
		Vertice<Integer, UbicacionGeografica>vertice2=new Vertice<Integer,UbicacionGeografica>(1001,new UbicacionGeografica(1001,1001));
		grafo.addVertex(1000, vertice1);
		grafo.addVertex(1001, vertice2);
		grafo.addEdge(1000, 1001, 1);
		assertEquals(2,grafo.V());
		assertEquals(1,grafo.E());
		setUp2();
		vertice1=new Vertice<Integer,UbicacionGeografica>(1000,new UbicacionGeografica(1000,1000));
		vertice2=new Vertice<Integer,UbicacionGeografica>(1001,new UbicacionGeografica(1001,1001));
		grafo.addVertex(1000, vertice1);
		grafo.addVertex(1001, vertice2);
		assertTrue(grafo.E()<=3000);
		int numArcos=grafo.E()+1;
		for(int i =0; i<1000;++i)
		{
			grafo.addEdge(1000, 1001, i);
			assertEquals(numArcos, grafo.E());
		}
		grafo.addEdge(1100, 1101, 1);
		assertEquals(numArcos, grafo.E());
	}
	
	/**
	 * Prueba del método getInfoVertex() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testGetInfoVertex()
	{
		assertNull(grafo.getInfoVertex(1));
		assertEquals(0, grafo.V());
		setUp2();
		for(int i=0; i< grafo.V();++i)
		{
			assertNotNull(grafo.getInfoVertex(i));
		}
		assertEquals(1000,grafo.V());
		Vertice<Integer, UbicacionGeografica>vertice1=new Vertice<Integer,UbicacionGeografica>(1000,new UbicacionGeografica(1000,1000));
		grafo.addVertex(1000, vertice1);
		assertEquals(1000, grafo.getInfoVertex(1000).darInfo().darLatidud(),0);
		assertEquals(1000, grafo.getInfoVertex(1000).darInfo().darLongitud(),0);
	}
	
	/**
	 * Prueba del método setInfoVertex() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testSetInfoVertex()
	{
		setUp2();
		for(int i=0; i<grafo.V();++i)
		{
			Vertice<Integer, UbicacionGeografica>vertice=new Vertice<Integer, UbicacionGeografica>(i,new UbicacionGeografica(i,i));
			grafo.setInfoVertex(i, vertice);
			assertEquals(i, grafo.getInfoVertex(i).darInfo().darLatidud(),0);
			assertEquals(i, grafo.getInfoVertex(i).darInfo().darLongitud(),0);
		}
		Vertice<Integer, UbicacionGeografica>vertice=new Vertice<Integer,UbicacionGeografica>(1000,new UbicacionGeografica(1000,1000));
		grafo.addVertex(1000, vertice);
	
	}
}
