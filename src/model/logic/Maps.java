package model.logic;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JFrame;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
//import com.teamdev.jxmaps.InfoWindowOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.LatLngBounds;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;

import model.data_structures.Arco;
import model.data_structures.IGrafoNoDirigido;
import model.data_structures.IListaEncadenada;
import model.data_structures.Vertice;

public class Maps extends MapView {

	// Objeto Google Maps
	private Map map;
	
	/**
	 *Constante de la latitud m�nima.
	 */
	private final static double LAT_MIN=4.597714;
	
	/**
	 *Constante de la longitud m�nima.
	 */
	private final static double LONG_MIN=-74.094723;
	
	/**
	 *Constante de la latitud m�xima.
	 */
	private final static double LAT_MAX=4.621360;
	
	/**
	 *Constante de la longitud m�nima.
	 */
	private final static double LONG_MAX=-74.062707;
	
	/**
	 * Visualizacion Google map con camino, marcas, circulos y texto de localizacion
	 * @param idReq
	 */
	public Maps(IGrafoNoDirigido<Integer, UbicacionGeografica> grafo, IListaEncadenada<EstacionDePolicia> estaciones)
	{	
		setOnMapReadyHandler( new MapReadyHandler() {
				@Override
				public void onMapReady(MapStatus status)
				{
			         if ( status == MapStatus.MAP_STATUS_OK )
			         {
			        	 map = getMap();
			        	 
			        	 // Configuracion de localizaciones del path (circulos)
			        	 CircleOptions vertexLocOpt= new CircleOptions(); 
			        	         	 
			        	//Configuracion de la linea del camino
			        	 PolylineOptions pathOpt = new PolylineOptions();
			        	 pathOpt.setStrokeColor("#000000");	 // color de linea	
			        	 
			        	 int numeroMaxVertices=50000;//SE IMPRIMEN SOLAMENTE 50000 PUNTOS DEBIDO A FALTA DE MEMORIA
			        	 for (int i = 0; i < numeroMaxVertices; i++) 
			        	 {
			        		 double latitud=grafo.getInfoVertex(i).darLatidud();
			        		 double longitud=grafo.getInfoVertex(i).darLongitud();
			        		 LatLng ubi = new LatLng(latitud, longitud);
			        		 if(latitud>=LAT_MIN && latitud<=LAT_MAX && longitud >=LONG_MIN && longitud <=LONG_MAX)
			        		 {
			        			 // Localizacion inicial
					        	 Circle startLoc = new Circle(map);
					        	 startLoc.setOptions(vertexLocOpt);
					        	 startLoc.setCenter(ubi); 
					        	 startLoc.setRadius(7); //Radio del circulo
					        	 
					        	 
					        	 Vertice<Integer,UbicacionGeografica> v = grafo.getVertex(i);
					        	 Iterator<Arco<Integer,UbicacionGeografica>> it = v.darAdyacentes();
					        	 
				        		 LatLng[] arco = new LatLng[2];
				        		 arco[0]= new LatLng(v.darInfo().darLatidud(), v.darInfo().darLongitud());

					        	 while(it.hasNext())
					        	 {
					        		 Arco<Integer,UbicacionGeografica> a = it.next();
					        		 Polyline path = new Polyline(map); 
					        		 arco[1]= new LatLng(a.darDestino().darInfo().darLatidud(), a.darDestino().darInfo().darLongitud());
					        		 path.setOptions(pathOpt); 
					        		 path.setPath(arco);
					        	 } 
			        		 }
			        	 }

			        	 
			        	// Configuracion de localizaciones del path (circulos)
			        	 CircleOptions verEstaciones= new CircleOptions(); 
			        	 verEstaciones.setFillColor("#FF0000");  // color de relleno
			        	 verEstaciones.setFillOpacity(0.5);
			        	 verEstaciones.setStrokeWeight(1);
			        	 
			        	 if(estaciones!=null)
			        	 {
			        	     Iterator<EstacionDePolicia> es = estaciones.iterator();
				        	 
				        	 while(es.hasNext())
				        	 {
				        		 EstacionDePolicia e = es.next();
				        		 LatLng ubi = new LatLng(e.darLatitud(), e.darLongitud());

				        		 Circle startLoc = new Circle(map);
					        	 startLoc.setOptions(verEstaciones);
					        	 startLoc.setCenter(ubi); 
					        	 startLoc.setRadius(40); //Radio del circulo
				        	 }
			        	 }
			        	 
			        	 initMap( map );
			         }
				}

		} );
		
				
	}
	
	public void initMap(Map map)
	{
		MapOptions mapOptions = new MapOptions();
		MapTypeControlOptions controlOptions = new MapTypeControlOptions();
		controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
		mapOptions.setMapTypeControlOptions(controlOptions);

		LatLngBounds bounds = new LatLngBounds(new LatLng(LAT_MIN, LONG_MIN), new LatLng(LAT_MAX,LONG_MAX));
		map.setOptions(mapOptions);
        map.fitBounds(bounds);
	}
	
	public void initFrame()
	{
		JFrame frame = new JFrame("Mapa");
		frame.setSize(600, 600);
		frame.add(this, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}
