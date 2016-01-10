package roadgraph;

import geography.GeographicPoint;
//This class contains information about Edges

public class MapEdge {
	
	public static GeographicPoint start;
	public static GeographicPoint end;
	 public String streetName;
	 public String streetType;
	 double distance;
	 
	 //constructor
	public MapEdge() {
		start =new GeographicPoint(0,0);
		this.end=end;
		//end =new GeographicPoint(0,0);
		streetName="";
		streetType="";
		distance=0;
	}
	
	//getters and setters
	public GeographicPoint getStart() {
		return start;
	}
	public void setStart(GeographicPoint start) {
		this.start = start;
	}
	public static GeographicPoint getEnd() {
		return end;
	}
	public void setEnd(GeographicPoint end) {
		this.end = end;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	

}
