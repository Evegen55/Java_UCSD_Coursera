/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and 
 * @author Johnn
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	private int numVertices;
	
	//DO IT LIKE PRIVATE !
	//GeographicPoint = MapNode.getNodeLocation!!!
	public HashMap<GeographicPoint,MapNode> listNodes;
	
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		listNodes = new HashMap<>();
		numVertices = 0;
		
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		numVertices = listNodes.size();
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		Set<GeographicPoint> str= listNodes.keySet();
		return str;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		int numEdges = 0;
		for(Entry<GeographicPoint, MapNode> entry : listNodes.entrySet()) {
			numEdges += entry.getValue().getListEdges().size();
		}
		
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		MapNode addedMaNode = new MapNode();
		addedMaNode.setNodeLocation(location);
		
		if (!listNodes.containsKey(location)) {
			listNodes.put(location, addedMaNode);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		//TODO: Implement this method in WEEK 2
		if(listNodes.containsKey(from) && listNodes.containsKey(to)) {
			MapEdge addedMapEdge = new MapEdge();
			MapNode startNode = listNodes.get(from);
			MapNode finishNode = listNodes.get(to);
			addedMapEdge.setStartNode(startNode);
			addedMapEdge.setFinishNode(finishNode);
			addedMapEdge.setStreetName(roadName);
			addedMapEdge.setStreetType(roadType);
			addedMapEdge.setStreetLength(length);
			listNodes.get(from).getListEdges().add(addedMapEdge);
			//listNodes.get(to).getListEdges().add(addedMapEdge);
			
			//System.out.println("\n"+ "node:" + "\t" + startNode.getNodeLocation().toString());
			//System.out.println("neighbour:" + "\t" + finishNode.getNodeLocation().toString());
		}
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		if (listNodes.containsKey(start) && listNodes.containsKey(goal)) {
			MapNode startNode = listNodes.get(start);
			MapNode finishNode = listNodes.get(goal);
			
			LinkedList<MapNode> myQueue = new LinkedList<>();
			
			LinkedList<MapNode> visited = new LinkedList<>();
			
			List<GeographicPoint> itogo = new ArrayList<GeographicPoint>();
			//LinkedList<GeographicPoint> itogo = new LinkedList<>();
			LinkedList<GeographicPoint> itogoParsedEven = new LinkedList<>();
			LinkedList<GeographicPoint> itogoParsedOdd = new LinkedList<>();
			
			myQueue.addFirst(startNode);
			visited.add(startNode);
			itogo.add(start);
			
			while (!myQueue.isEmpty()) {
				MapNode curr = myQueue.removeFirst();
				
				String currCoord = curr.getNodeLocation().toString();
				String finishNodeCoord = finishNode.getNodeLocation().toString();
				
				if(currCoord.equalsIgnoreCase(finishNodeCoord)) {
					
					
	//--------------------------------------------------------------------------
					//Parsing itogo for case with two neighbours (because we use stack)
					if(getNeighbours(startNode).size() == 2) {
						itogoParsedEven.add(start);
						itogoParsedOdd.add(start);
						for (GeographicPoint pars : itogo) {
							int idx = itogo.indexOf(pars);
							if(idx % 2 == 0) {
								itogoParsedEven.add(pars);
							} else {
								itogoParsedOdd.add(pars);
							}
						}
						if (itogoParsedEven.getLast().toString().equalsIgnoreCase(finishNodeCoord)) {
							return itogoParsedEven;
						} else {
							return itogoParsedOdd;
						}
						
					} else 
						
	//--------------------------------------------------------------------------					
					
					
					
					return itogo;
				} else  {
					for (MapNode ngh : getNeighbours(curr)) {
						if (!visited.contains(ngh)){
						myQueue.addLast(ngh);
						visited.add(ngh);
						nodeSearched.accept(ngh.getNodeLocation());
						//curr - parent, ngh - child
						itogo.add(ngh.getNodeLocation());
						}
					}
				}
			}
			
		} 
		return null;
	}
	
	public List<MapNode> getNeighbours(MapNode forSearch) {
		List<MapNode> att = new ArrayList<>();
		List<MapEdge> listForSearch = forSearch.getListEdges();
		for (MapEdge sch : listForSearch) {
			MapNode mdn = sch.getFinishNode();
			att.add(mdn);
		}
		return att;
		
	}

	//===================================================================================================
	//for week 3
	//===================================================================================================

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		/* Use this code in Week 3 End of Week Quiz
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
