package test.data_structures;

import org.junit.Before;

import model.data_structures.ListaEncadenada;
import model.logic.Comparendo;

public class TestStack 
{
private ListaEncadenada<Comparendo> lista;
	
	public void setUp1() {
		lista= new ListaEncadenada<Comparendo>();
	}

	public void setUp2() {
		lista = new ListaEncadenada<Comparendo>();
		for (int i = 0; i < 10; i++) 
		{
			lista.agregar(new Comparendo(i, "", "", "", "", "", "", null));
		}
	}
}
