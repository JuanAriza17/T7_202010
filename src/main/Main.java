package main;
import controller.Controller;

public class Main {

	/**
	 * Método main, ejecuta el método run que inicia la aplicación.
	 * @param args 
	 */
	public static void main(String[] args) 
	{
		Controller controler = new Controller();
		controler.run();
	}
}
