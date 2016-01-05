package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
	private GeographicPoint nodeLocation;
	private String nodeName;
	public List<MapEdge> listEdges; //DO IT LIKE PRIVATE !
	

	public MapNode() {
		nodeLocation = new GeographicPoint(0, 0) ; 
		nodeName = null;
		listEdges = new ArrayList<>();
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


}
