package roadgraph;

public class MapEdge {

	//start node and finish node
    private MapNode startNode;
	private MapNode finishNode;
	//properties of an edge
    private String streetName;
	private String streetType;
	//the length of the street in meters
	private double streetLength;
	//The speed limitations with kilometers per hour
	private double speedLimit;

	/**
	 *
	 */
	public MapEdge() {
		setStartNode(null);
		setFinishNode(null);
		setStreetName("");
		setStreetType("");
		setStreetLength(0.0);
		setSpeedLimit(0.0);
	}
	/**
	 *
	 * @param first
	 * @param second
	 * @param name
	 * @param length
	 */
	public MapEdge(MapNode first, MapNode second, String name, double length) {
		setStartNode(first);
		setFinishNode(second);
		setStreetName(name);
		setStreetType("");
		setStreetLength(length);
		setSpeedLimit(0.0);
	}
	/**
	 *
	 * @param first
	 * @param second
	 * @param name
	 * @param type
	 * @param length
	 */
	public MapEdge(MapNode first, MapNode second, String name, String type, double length) {
		setStartNode(first);
		setFinishNode(second);
		setStreetName(name);
		setStreetType(type);
		setStreetLength(length);
		setSpeedLimit(0.0);
	}
	/**
	 *
	 * @param first
	 * @param second
	 * @param name
	 * @param type
	 * @param length
	 * @param limOfSpeed
	 */
	public MapEdge(MapNode first, MapNode second, String name, String type, double length, double limOfSpeed) {
		setStartNode(first);
		setFinishNode(second);
		setStreetName(name);
		setStreetType(type);
		setStreetLength(length);
		setSpeedLimit(limOfSpeed);
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
	/**
	 * @return the speedLimit
	 */
	public double getSpeedLimit() {
		return speedLimit;
	}
	/**
	 * @param speedLimit the speedLimit to set
	 */
	public void setSpeedLimit(double speedLimit) {
		this.speedLimit = speedLimit;
	}
	/**
	 * @override
	*/
	public String toString() {
		String to = "startNode " + startNode.getNodeLocation().toString() + "\t" + "goalNode " + finishNode.getNodeLocation().toString()
				 + "\t" + "street name: " + streetName+ "\t" + "street type: " + streetType + "\t" + "street length: " + streetLength + "\t" + "speed limit: " + speedLimit;
		return to;
	}

}
