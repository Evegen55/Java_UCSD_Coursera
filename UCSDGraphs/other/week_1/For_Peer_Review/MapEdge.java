package roadgraph;

public class MapEdge {
	
	private MapNode startNode;
	private MapNode finishNode;
	
	private String streetName;
	private String streetType;
	private double streetLength;

	public MapEdge() {
		setStartNode(null);
		setFinishNode(null);
		setStreetName("");
		setStreetType("");
		setStreetLength(0.0);
	}

	/**
	 * @return the startNode
	 */
	public MapNode getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode the startNode to set
	 */
	public void setStartNode(MapNode startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return the finishNode
	 */
	public MapNode getFinishNode() {
		return finishNode;
	}

	/**
	 * @param finishNode the finishNode to set
	 */
	public void setFinishNode(MapNode finishNode) {
		this.finishNode = finishNode;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetLength
	 */
	public double getStreetLength() {
		return streetLength;
	}

	/**
	 * @param streetLength the streetLength to set
	 */
	public void setStreetLength(double streetLength) {
		this.streetLength = streetLength;
	}

	/**
	 * @return the streetType
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @param streetType the streetType to set
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

}
