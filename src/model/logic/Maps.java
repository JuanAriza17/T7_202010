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
			        	 vertexLocOpt.setFillColor("#000000");  // color de relleno
			        	 vertexLocOpt.setFillOpacity(0.5);
			        	 vertexLocOpt.setStrokeWeight(1.0);
			        	 
			        	//Configuracion de la linea del camino
			        	 PolylineOptions pathOpt = new PolylineOptions();
			        	 pathOpt.setStrokeColor("#0000==");	  // color de linea	
			        	 pathOpt.setStrokeOpacity(1.75);
			        	 pathOpt.setStrokeWeight(1.5);
			        	 pathOpt.setGeodesic(false);
			        	 
			        	 for (int i = 0; i < grafo.V(); i++) 
			        	 {
			        		 LatLng ubi = new LatLng(grafo.getInfoVertex(i).darLatidud(), grafo.getInfoVertex(i).darLongitud());

				        	 // Localizacion inicial
				        	 Circle startLoc = new Circle(map);
				        	 startLoc.setOptions(vertexLocOpt);
				        	 startLoc.setCenter(ubi); 
				        	 startLoc.setRadius(30); //Radio del circulo
				        	 
				        	 
				        	 Vertice<Integer,UbicacionGeografica> v = grafo.getVertex(i);
				        	 Iterator<Arco<Integer,UbicacionGeografica>> it = v.darAdyacentes();
				        	 
			        		 LatLng[] arco = new LatLng[2];
			        		 arco[0]= new LatLng(v.darInfo().darLatidud(), v.darInfo().darLongitud());

				        	 while(it.hasNext())
				        	 {
				        		 Arco<Integer,UbicacionGeografica> a = it.next();
				        		 Polyline path = new Polyline(map); 
				        		 arco[1]= new LatLng(a.darDestino().darInfo().darLatidud(), a.darDestino().darInfo().darLatidud());
				        		 path.setOptions(pathOpt); 
				        		 path.setPath(arco);
				        	 }
						 }

			        	 if(estaciones!=null)
			        	 {

				        	 vertexLocOpt.setFillColor("#0000FF");  // color de relleno
				        	 Iterator<EstacionDePolicia> es = estaciones.iterator();
				        	 
				        	 while(es.hasNext())
				        	 {
				        		 EstacionDePolicia e = es.next();
				        		 LatLng ubi = new LatLng(e.darLatitud(), e.darLongitud());

				        		 Circle startLoc = new Circle(map);
					        	 startLoc.setOptions(vertexLocOpt);
					        	 startLoc.setCenter(ubi); 
					        	 startLoc.setRadius(50); //Radio del circulo
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

		LatLngBounds bounds = new LatLngBounds(new LatLng(4.597714, -74.094723), new LatLng(4.621360,-74.062707));
		map.setOptions(mapOptions);
        map.fitBounds(bounds);
		map.setZoom(14.0);
		
	}
	
	public void initFrame()
	{
		JFrame frame = new JFrame("Mapa");
		frame.setSize(600, 600);
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}
