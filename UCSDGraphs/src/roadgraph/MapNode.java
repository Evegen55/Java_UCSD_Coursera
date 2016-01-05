package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
	private GeographicPoint nodeLocation;
	private String nodeName;
	private List<MapEdge> listEdges;
	

	public MapNode() {
		setNodeLocation(new GeographicPoint(0, 0)); 
		setNodeName(null);
		setListEdges(new ArrayList<>());
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


}
