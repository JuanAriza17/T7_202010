package model.logic;

public class Comparendo implements Comparable<Comparendo>
{
	private int id;
	
	private String fecha;

	private String vehiculo;
	
	private String servicio;
	
	private String infraccion;
	
	private String des_infrac;
	
	private String localidad;
	
	private double[] coordenadas;
	
	
	public Comparendo(int pId, String pFecha, String pVehiculo, String pServicio, String pInfraccion, String pDescripcion, String pLocalidad, double[] pCoordenadas)
	{
		id = pId;
		fecha = pFecha;
		vehiculo = pVehiculo;
		servicio = pServicio;
		infraccion = pInfraccion;
		localidad = pLocalidad;
		coordenadas = pCoordenadas;
	}
	
	public int darId() {
		return id;
	}

	public String darFecha() {
		return fecha;
	}

	public String darVehiculo() {
		return vehiculo;
	}

	public String darServicio() {
		return servicio;
	}

	public String darInfraccion() {
		return infraccion;
	}

	public String darDescripcion() {
		return des_infrac;
	}

	public String darLocalidad() {
		return localidad;
	}

	public double[] darCoordenadas() {
		return coordenadas;
	}

	@Override
	public int compareTo(Comparendo o) {
		return id-o.darId();
	}

	
}
