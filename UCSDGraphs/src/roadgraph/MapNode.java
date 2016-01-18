package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
	private GeographicPoint nodeLocation;
	private String nodeName;
	private List<MapEdge> listEdges;
	private double distance;
	
	/**
	 * 
	 */
	public MapNode() {
		setNodeLocation(new GeographicPoint(0, 0)); 
		setNodeName(null);
		setListEdges(new ArrayList<>());
		setDistance(0);
	}
	/**
	 * 
	 * @param nodeLocation
	 * @param nodeName
	 */
	public MapNode(GeographicPoint nodeLocation, String nodeName) {
		this.nodeLocation = nodeLocation;
		this.nodeName = nodeName;
		setListEdges(new ArrayList<>());
		setDistance(0);
	}
	/**
	 * 
	 * @param nodeLocation
	 * @param nodeName
	 * @param listEdges
	 */
	public MapNode(GeographicPoint nodeLocation, String nodeName, List<MapEdge> listEdges) {
		this.nodeLocation = nodeLocation;
		this.nodeName = nodeName;
		this.listEdges = listEdges;
		setDistance(0);
	}


	/**
	 * @return the nodeLocation
	 */
	public GeographicPoint getNodeLocation() {
		return nodeLocation;
	}


	/**
	 * @param nodeLocation the nodeLocation to set
	 */
	public void setNodeLocation(GeographicPoint nodeLocation) {
		this.nodeLocation = nodeLocation;
	}


	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}


	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}


	/**
	 * @return the listEdges
	 */
	public List<MapEdge> getListEdges() {
		return listEdges;
	}


	/**
	 * @param listEdges the listEdges to set
	 */
	public void setListEdges(List<MapEdge> listEdges) {
		this.listEdges = listEdges;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
