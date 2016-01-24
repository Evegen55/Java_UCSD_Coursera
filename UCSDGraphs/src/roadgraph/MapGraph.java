/**
 * @author UCSD MOOC development team
 * @author Evegen55
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
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
		//add a distance for week 3 - set a distance to infinity
		                                                                                                                     //System.out.println("adding" + "\t" + location.toString());
		                                                                       
		if (!listNodes.containsKey(location)) {
			MapNode addedMaNode = new MapNode(location, "");                                                                 //System.out.println("added" + "\t" + location.toString());
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
			MapNode startNode = listNodes.get(from);
			MapNode finishNode = listNodes.get(to);
			MapEdge addedMapEdge = new MapEdge(startNode, finishNode, roadName, roadType, length);                             //System.out.println(addedMapEdge.toString());
			listNodes.get(from).getListEdges().add(addedMapEdge); //add OUTCOMING edge from -> to
			}
	}
	/**
	 * 
	 * @param forSearch
	 * @return
	 */
	public List<MapNode> getNeighbours(MapNode forSearch) {
		List<MapNode> att = new ArrayList<>();
		List<MapEdge> listForSearch = forSearch.getListEdges();
		for (MapEdge sch : listForSearch) {
			MapNode mdn = sch.getFinishNode();
			att.add(mdn);                                                                                           }
		return att;
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
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal, Consumer<GeographicPoint> nodeSearched){
		// TODO: Implement this method in WEEK 2
		if (start == null || goal == null) {
			throw new NullPointerException("Cannot find route from or to null node");
			}
		MapNode startNode = listNodes.get(start);
		MapNode endNode = listNodes.get(goal);
		if (startNode == null) {
			System.err.println("Start node " + start + " does not exist");
			return null;
		}
		if (endNode == null) {
			System.err.println("End node " + goal + " does not exist");
			return null;
		}
		// setup to begin BFS
		HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
		Queue<MapNode> toExplore = new LinkedList<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		
		toExplore.add(startNode);
		MapNode next = null;
		
		while (!toExplore.isEmpty()) {
			next = toExplore.remove();
			//----------------------------
			// hook for visualization
			nodeSearched.accept(next.getNodeLocation());
			//----------------------
			if (next.equals(endNode)) break;
			List<MapNode> neighbors = getNeighbours(next);
			for (MapNode neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					parentMap.put(neighbor, next);
					toExplore.add(neighbor);
				}
			}
		}
		if (!next.equals(endNode)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		List<GeographicPoint> path = reconstructPath(parentMap, startNode, endNode);
        return path;
	}
	
	
	//===================================================================================================
	//for week 3
	//===================================================================================================
	//part 1
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
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 3
		List<GeographicPoint> lfs = new LinkedList<>();
		if (listNodes.containsKey(start) && listNodes.containsKey(goal)) {
			//initialize ADT
			//we should use a comparator!!!
			Comparator<MapNode> cmtr = createComparator();
			PriorityQueue<MapNode> pq = new PriorityQueue<>(5, cmtr);
			HashMap<MapNode, MapNode> parentMap = new HashMap<>();
			Set<MapNode> visited = new HashSet<>();
			//set a distance to infinity
			for(Map.Entry<GeographicPoint,MapNode> entry : listNodes.entrySet()) {
				entry.getValue().setDistance(Double.POSITIVE_INFINITY);
			}
			//get a start and goal node
			MapNode startNode = listNodes.get(start);
			MapNode goalNode = listNodes.get(goal);
			//set a distance start node as 0
			startNode.setDistance(0.0);
			//start working with a PriorityQueue
			pq.add(startNode);                                                                                         
			//start a loop through PriorityQueue
			while(!pq.isEmpty()) {
				MapNode curr = pq.poll();
				//--------------------------------------------
				// hook for visualization
				nodeSearched.accept(curr.getNodeLocation());
				//--------------------------------------------
				if(!visited.contains(curr)) {                                                                           
					visited.add(curr);
					if (goal.toString().equalsIgnoreCase(curr.getNodeLocation().toString())) break;
					//for each of curr's neighbors, "next", ->
					List<MapNode> neighbors = getNeighbours(curr);                                                 
					for(MapNode next : neighbors) {
						//not in visited set ->
						if(!visited.contains(next)) {
							//if path through curr to n is shorter ->
							double edgeLength = getLengthEdgeBeetwen(curr, next);
								if(curr.getDistance()+edgeLength < next.getDistance()) {
									//update next's distance
									next.setDistance(curr.getDistance()+edgeLength);
									parentMap.put(next, curr);
					            }
								//enqueue into the pq
								pq.add(next);
						}                                                                                                                        
					}
				}
			}
			lfs = reconstructPath(parentMap, startNode, goalNode);
		} else {throw new NullPointerException("Cannot find route from or to null node");}
		return lfs;
	}
	/**
	 * 
	 * @param parentMap
	 * @param start
	 * @param goal
	 * @return
	 * @author UCSD MOOC development team
	 * 
	 */
	private List<GeographicPoint> reconstructPath(HashMap<MapNode,MapNode> parentMap, MapNode start, MapNode goal){
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		MapNode current = goal;
		while (!current.equals(start)) {
			path.addFirst(current.getNodeLocation());
			current = parentMap.get(current);
		}
        path.addFirst(start.getNodeLocation());
		return path;
	}
	
	/**
	 * 
	 * @param parentMap
	 */
	private void printMap(HashMap<MapNode,MapNode> parentMap){
		for (Map.Entry<MapNode, MapNode> entry : parentMap.entrySet()) {
			System.out.print( "key" + "\t" + entry.getKey().getNodeLocation().toString() + "\t" + "distance" + entry.getKey().getDistance() + "\t");
			System.out.println( "value" + "\t" + entry.getValue().getNodeLocation().toString() + "\t" + "distance" + entry.getKey().getDistance());
		}
	}
	
	/**
	 * A helper method to get length between two nodes
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private double getLengthEdgeBeetwen(MapNode start, MapNode end) {
		// TODO Auto-generated method stub
		double length = 0.0;
		List<MapEdge> listForSearch = start.getListEdges();
		for (MapEdge sch : listForSearch) {
			if (sch.getStartNode().getNodeLocation().toString().equals(start.getNodeLocation().toString()) &&
					sch.getFinishNode().getNodeLocation().toString().equals(end.getNodeLocation().toString())	) {
				return length = sch.getStreetLength();
			}
		}
		return length;
	}

	/**
	 * a helper method for using it as distance priority
	 * @return comparator
	 */
	public Comparator<MapNode> createComparator() {
		Comparator<MapNode> comparator = new Comparator<MapNode>() {
            @Override
            public int compare(MapNode x, MapNode y) {
                // You could return x.getDistance() - y.getDistance(), which would be more efficient.
            	return (int) (x.getDistance()-y.getDistance());
            	//but for more better understanding you should use smth like this:
            	//     if (x.getDistance() < y.getDistance()) {
            	//         return -1;
            	//          }
            	//      if (x.getDistance() > y.getDistance()) {
            	//          return 1;
            	//      }
            	//       return 0;
            }
        };
		return comparator;
	}
	
	//part 2
	//===================================================================================================	

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
				List<GeographicPoint> lfs = new LinkedList<>();
				if (listNodes.containsKey(start) && listNodes.containsKey(goal)) {
					//initialize ADT
					//we should use a comparator!!!
					Comparator<MapNode> cmtr = createComparator();
					PriorityQueue<MapNode> pq = new PriorityQueue<>(5, cmtr);
					HashMap<MapNode, MapNode> parentMap = new HashMap<>();
					Set<MapNode> visited = new HashSet<>();
					//set a distance to infinity
					for(Map.Entry<GeographicPoint,MapNode> entry : listNodes.entrySet()) {
						entry.getValue().setDistance(Double.POSITIVE_INFINITY);
					}
					
					
					//getting a  reduced cost
					double redCost = getReducedCost(start,goal);                                    //System.out.println(redCost);
					
					//get a start and goal node
					MapNode startNode = listNodes.get(start);
					MapNode goalNode = listNodes.get(goal);
					//set a distance start node as 0
					startNode.setDistance(0.0);
					//start working with a PriorityQueue
					pq.add(startNode);                                                                                         
					//start a loop through PriorityQueue
					while(!pq.isEmpty()) {
						MapNode curr = pq.poll();
						//--------------------------------------------
						// hook for visualization
						nodeSearched.accept(curr.getNodeLocation());
						//--------------------------------------------
						if(!visited.contains(curr)) {                                                                           
							visited.add(curr);
							if (goal.toString().equalsIgnoreCase(curr.getNodeLocation().toString())) break;
							//for each of curr's neighbors, "next", ->
							List<MapNode> neighbors = getNeighbours(curr);                                                 
							for(MapNode next : neighbors) {
								//not in visited set ->
								if(!visited.contains(next)) {
									//if path through curr to n is shorter ->
									double edgeLength = getLengthEdgeBeetwen(curr, next);
										if((curr.getDistance()+edgeLength < next.getDistance())
												&& getReducedCost(curr.getNodeLocation(),goal)<=redCost
												) {
											
											       //                                                      System.out.println(getReducedCost(curr.getNodeLocation(),goal));
											
											//update next's distance
											next.setDistance(curr.getDistance()+edgeLength);
											parentMap.put(next, curr);
							            }
										//enqueue into the pq
										pq.add(next);
								}                                                                                                                        
							}
						}
					}
					lfs = reconstructPath(parentMap, startNode, goalNode);
				} else {throw new NullPointerException("Cannot find route from or to null node");}
				return lfs;
	}
	
	/**
	 * a helper method for getting a reduced cost
	 * @see https://en.wikipedia.org/wiki/A*_search_algorithm
	 * @param start
	 * @param goal
	 * @return
	 */
	private double getReducedCost(GeographicPoint start, GeographicPoint goal) {
		double red_cost =(Math.sqrt(Math.pow((start.x-goal.x), 2) +  Math.pow((start.y-goal.y), 2)));
		return red_cost;
	}

	public static void main(String[] args)
	{
		//System.out.print("Making a new map...");
		//MapGraph theMap = new MapGraph();
		//System.out.print("DONE. \nLoading the map...");
		//GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		//System.out.println("DONE.");
		

		//System.out.println("Num nodes: " + theMap.getNumVertices());
		//System.out.println("Num edges: " + theMap.getNumEdges());
		//List<GeographicPoint> route = theMap.dijkstra(new GeographicPoint(1.0,1.0), new GeographicPoint(8.0,-1.0));
		
		//for (GeographicPoint gp: route) {
		//	System.out.println(gp.toString());
		//}
		
		// You can use this method for testing.  
		
		//Use this code in Week 3 End of Week Quiz
		
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		System.out.println(route.size() + "\t" + route2.size());
		
		
	}
	
}
