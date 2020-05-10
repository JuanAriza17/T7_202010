package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;

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
	IGrafoNoDirigido<Integer,UbicacionGeografica>grafo;
	
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
		grafo=new GrafoNoDirigido<Integer, UbicacionGeografica>(TAMANO_GRAFO);
	}

	/**
	 * Escenario 2 de prueba.
	 */
	public void setUp2() 
	{
		grafo=new GrafoNoDirigido<Integer, UbicacionGeografica>(TAMANO_GRAFO);
		for(int i=0; i<1000;++i)
		{
			UbicacionGeografica vertice=new UbicacionGeografica(Math.random()*100,Math.random()*10);
			grafo.addVertex(i, vertice);
		}
		for(int i=0; i<3000; ++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice1= grafo.getVertex((int) (Math.random()*1000));
			Vertice<Integer,UbicacionGeografica>vertice2= grafo.getVertex((int) (Math.random()*1000));
			if(!Integer.toString(vertice1.darId()).equals(Integer.toString(vertice2.darId())))
			{
				grafo.addEdge(vertice1.darId(), vertice2.darId(), modelo.distanceHaversine(vertice1.darInfo().darLatidud(), vertice1.darInfo().darLongitud(), vertice2.darInfo().darLatidud(), vertice2.darInfo().darLatidud()));
			}
		}
	}
	
	/**
	 * Escenario 3 de prueba.
	 */
	public void setUp3()
	{
		grafo=new GrafoNoDirigido<Integer, UbicacionGeografica>(TAMANO_GRAFO);
		for(int i=1; i<10;++i)
		{
			grafo.addVertex(i, new UbicacionGeografica(i,i));
		}
		for(int i=1; i<9;++i)
		{
			grafo.addEdge(i, i+1, i);
		}
		for(int i=10; i<20;++i)
		{
			grafo.addVertex(i, new UbicacionGeografica(i,i));
		}
		for(int i=10; i<19;++i)
		{
			grafo.addEdge(i, i+1, i);
		}
		for(int i=20; i<50;++i)
		{
			grafo.addVertex(i, new UbicacionGeografica(i,i));
		}
		for(int i=20; i<49;++i)
		{
			grafo.addEdge(i, i+1, i);
		}
	}

	/**
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
		UbicacionGeografica ug=new UbicacionGeografica(1000,1000);
		grafo.addVertex(1000,ug);
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
		UbicacionGeografica vertice1=new UbicacionGeografica(1000,1000);
		UbicacionGeografica vertice2=new UbicacionGeografica(1001,1001);
		grafo.addVertex(1000, vertice1);
		grafo.addVertex(1001, vertice2);
		grafo.addEdge(1000, 1001, 1);
		assertEquals(2,grafo.V());
		assertEquals(1,grafo.E());
		setUp2();
		vertice1=new UbicacionGeografica(1000,1000);
		vertice2=new UbicacionGeografica(1001,1001);
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
		UbicacionGeografica vertice1=new UbicacionGeografica(1000,1000);
		grafo.addVertex(1000, vertice1);
		assertEquals(1000, grafo.getInfoVertex(1000).darLatidud(),0);
		assertEquals(1000, grafo.getInfoVertex(1000).darLongitud(),0);
		grafo.setInfoVertex(1000, new UbicacionGeografica(1001,1001));
		assertEquals(1001, grafo.getInfoVertex(1000).darLatidud(),0);
		assertEquals(1001, grafo.getInfoVertex(1000).darLongitud(),0);
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
			UbicacionGeografica vertice=new UbicacionGeografica(i,i);
			grafo.setInfoVertex(i, vertice);
			assertEquals(i, grafo.getInfoVertex(i).darLatidud(),0);
			assertEquals(i, grafo.getInfoVertex(i).darLongitud(),0);
		}
		UbicacionGeografica vertice=new UbicacionGeografica(1000,1000);
		UbicacionGeografica modificacion=new UbicacionGeografica(1001,1001);
		grafo.addVertex(1000, vertice);
		assertEquals(1000, grafo.getInfoVertex(1000).darLatidud(),0);
		assertEquals(1000, grafo.getInfoVertex(1000).darLongitud(),0);
		assertEquals(vertice, grafo.getInfoVertex(1000));
		grafo.setInfoVertex(1000, modificacion);
		assertEquals(modificacion, grafo.getInfoVertex(1000));
		assertEquals(1001, grafo.getInfoVertex(1000).darLatidud(),0);
		assertEquals(1001, grafo.getInfoVertex(1000).darLongitud(),0);
	}
	
	/**
	 * Prueba del método getCostArc() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testGetCostArc()
	{
		assertEquals(-1.0, grafo.getCostArc(0, 1000),0);
		setUp2();
		for(int i=1; i<grafo.V();++i)
		{
			Vertice<Integer, UbicacionGeografica>actual=grafo.getVertex(i);
			Iterator<Arco<Integer,UbicacionGeografica>>arcos=actual.darAdyacentes();
			while(arcos.hasNext())
			{
				Arco<Integer,UbicacionGeografica>arcActual=arcos.next();
				assertNotEquals(-1.0, grafo.getCostArc(arcActual.darOrigen().darId(), arcActual.darDestino().darId()));
			}
		}
		UbicacionGeografica ver1= new UbicacionGeografica(1000, 1000);
		UbicacionGeografica ver2= new UbicacionGeografica(1001, 1001);
		Vertice<Integer, UbicacionGeografica>vertice1=new Vertice<Integer,UbicacionGeografica>(1000,ver1);
		Vertice<Integer, UbicacionGeografica>vertice2=new Vertice<Integer,UbicacionGeografica>(1001,ver2);
		grafo.addVertex(1000, ver1);
		grafo.addVertex(1001, ver2);
		grafo.addEdge(vertice1.darId(), vertice2.darId(), 1);
		assertEquals(1, grafo.getCostArc(vertice1.darId(), vertice2.darId()),0);
		for(int i=2;i<1000;++i)
		{
			grafo.setCostArc(vertice1.darId(), vertice2.darId(), i);
			assertEquals(i, grafo.getCostArc(vertice1.darId(), vertice2.darId()),0);
		}
	}
	
	/**
	 * Prueba del método setCostArc() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testCostArc()
	{
		setUp2();
		for(int i=0; i<grafo.V();++i)
		{
			Vertice<Integer, UbicacionGeografica>actual=grafo.getVertex(i);
			Iterator<Arco<Integer,UbicacionGeografica>>arcos=actual.darAdyacentes();
			int j=0;
			while(arcos.hasNext())
			{
				++j;
				Arco<Integer,UbicacionGeografica>arcActual=arcos.next();
				grafo.setCostArc(arcActual.darOrigen().darId(), arcActual.darDestino().darId(),j);
				assertEquals(j, grafo.getCostArc(arcActual.darOrigen().darId(), arcActual.darDestino().darId()),0);
			}
		}
	}
	
	/**
	 * Prueba del método addVertex() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testAddVertex()
	{
		for(int i=0; i<1000;++i)
		{
			UbicacionGeografica ug= new UbicacionGeografica(i, i);
			grafo.addVertex(i, ug);
			assertEquals(i+1, grafo.V());
			assertNotNull(grafo.getVertex(i));
			grafo.setInfoVertex(i, ug);
			assertEquals(i,grafo.getInfoVertex(i).darLatidud(),0);
			assertEquals(i,grafo.getInfoVertex(i).darLongitud(),0);
		}
		for(int i=0; i<1000;++i)
		{
			Vertice<Integer,UbicacionGeografica>ver=new Vertice<Integer, UbicacionGeografica>(i, new UbicacionGeografica(i,i));
			assertEquals(ver.darInfo().darLatidud(), grafo.getVertex(i).darInfo().darLatidud(),0);
			assertEquals(ver.darInfo().darLongitud(), grafo.getVertex(i).darInfo().darLongitud(),0);
			assertEquals(1000, grafo.V());
			++i;
		}
	}
	
	/**
	 * Prueba del método adj() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testAdj()
	{
		for(int i=1; i<11; ++i)
		{
			grafo.addVertex(i, new UbicacionGeografica(i,i));
		}
		assertEquals(10,grafo.V());
		for(int i=2; i<11; ++i)
		{
			grafo.addEdge(1, i, i);
		}
		assertNotNull(grafo.getVertex(1).darAdyacentes());
		assertEquals(9,grafo.getVertex(1).darLista().darArreglo().length);
		for(int i=2; i<11;++i)
		{
			assertEquals(1,grafo.getVertex(i).darLista().darArreglo().length);
		}
		assertNull(grafo.adj(100));
		Iterable<Integer> iterable=grafo.adj(1);
		Iterator<Integer> iterator=iterable.iterator();
		int contador=0;
		while(iterator.hasNext())
		{
			int actual=iterator.next();
			assertNotNull(grafo.getVertex(actual));
			++contador;
		}
		assertEquals(9,contador);
	}
	
	/**
	 * Prueba del método uncheck() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testUncheck()
	{
		setUp3();
		grafo.dfs(1);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertFalse(vertice.estaMarcado());
		}
		grafo.dfs(10);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		grafo.dfs(20);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=20; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		grafo.uncheck();
		for(int i=1;i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertFalse(vertice.estaMarcado());
		}
	}
	
	/**
	 * Prueba del método dfs() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testDfs()
	{
		grafo.dfs(1);
		setUp3();
		grafo.dfs(1);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertFalse(vertice.estaMarcado());
		}
		for(int i=20; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertFalse(vertice.estaMarcado());
		}
		grafo.dfs(10);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=20; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertFalse(vertice.estaMarcado());
		}
		grafo.dfs(20);
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=20; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertTrue(vertice.estaMarcado());
		}
		for(int i=1; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertEquals(0,vertice.darColor());
		}
		assertNull(grafo.getVertex(1).darArcoLlegada());
		assertNull(grafo.getVertex(10).darArcoLlegada());
		assertNull(grafo.getVertex(20).darArcoLlegada());
		for(int i=1; i<50;++i)
		{
			if(i!=1 && i!=10 && i!=20)
			{
				assertNotNull(grafo.getVertex(i).darArcoLlegada());
			}
		}
		grafo.dfs(10000);
	}
	
	/**
	 * Prueba del método cc() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testCC()
	{
		assertEquals(0,grafo.cc());
		setUp3();
		assertEquals(3, grafo.cc());
		
		for(int i=50, j=4; i<100;++i,++j)
		{
			grafo.addVertex(i, new UbicacionGeografica(i, i));
			int x=grafo.cc();
			assertEquals(j,x);
		}
		grafo.addEdge(9,10,9);
		assertEquals(52, grafo.cc());
		grafo.addEdge(19,20,19);
		assertEquals(51,grafo.cc());
		for(int i=49; i<100;++i)
		{
			grafo.addEdge(i,i+1,i);
		}
		assertEquals(1,grafo.cc());
		
		setUp3();
		grafo.cc();
		for(int i=1; i<10;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertEquals(0, vertice.darColor());
		}
		for(int i=10; i<20;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertEquals(1, vertice.darColor());
		}
		for(int i=20; i<50;++i)
		{
			Vertice<Integer,UbicacionGeografica>vertice=grafo.getVertex(i);
			assertEquals(2, vertice.darColor());
		}
	}	
	
	/**
	 * Prueba del método getCC() de la clase GrafoNoDirigido.
	 */
	@Test
	public void testGetCC()
	{
		assertNull(grafo.getCC(1));;
		setUp3();
		Iterable<Integer>iterable=grafo.getCC(1);
		Iterator<Integer> iterator=iterable.iterator();
		int contador=1;
		while(iterator.hasNext())
		{
			int actual=iterator.next();
			assertEquals(contador,actual);
			++contador;
		}
		iterable=grafo.getCC(10);
		iterator=iterable.iterator();
		contador=10;
		while(iterator.hasNext())
		{
			int actual=iterator.next();
			assertEquals(contador,actual);
			++contador;
		}
		iterable=grafo.getCC(20);
		iterator=iterable.iterator();
		contador=20;
		while(iterator.hasNext())
		{
			int actual=iterator.next();
			assertEquals(contador,actual);
			++contador;
		}
		grafo.addEdge(9,10,9);
		grafo.addEdge(19,20,19);
		iterable=grafo.getCC(1);
		iterator=iterable.iterator();
		contador=1;
		while(iterator.hasNext())
		{
			int actual=iterator.next();
			assertEquals(contador,actual);
			++contador;
		}
		assertNull(grafo.getCC(10000));
	}
}
