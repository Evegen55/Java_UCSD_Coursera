package roadgraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import geography.GeographicPoint;

//this class contains information about node and edges which refers to it

public class MapNode {
	public static  GeographicPoint start;
	static List <MapEdge> edges;
	
	//constructor
	public MapNode() {
		start = new GeographicPoint(0,0);
		edges = new LinkedList <MapEdge> ();
		
	}
	//getters and setters
	public GeographicPoint getStart() {
		return start;
	}
	public void setStart(GeographicPoint start) {
		this.start = start;
	}
	public static List<MapEdge> getEdges() {
		return edges;
	}
	public static void setEdges(List<MapEdge> edges) {
		MapNode.edges = edges;
	}
}
