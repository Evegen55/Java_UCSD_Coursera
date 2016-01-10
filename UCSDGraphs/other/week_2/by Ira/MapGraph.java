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
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.Collections;

import geography.GeographicPoint;
import util.GraphLoader;


/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	 //TODO: Add your member variables here in WEEK 2
	static HashMap <GeographicPoint , MapNode> vertices;

	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		vertices = new HashMap <GeographicPoint , MapNode> ();
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{ 
		//TODO: Implement this method in WEEK 2
	   return vertices.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
	//TODO: Implement this method in WEEK 2
	return vertices.keySet();	
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		List<MapEdge> b = MapNode.getEdges();
		int NumEdges=b.size();
		return NumEdges;
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
		if(!vertices.containsKey(location)||(!(location==null))){
		MapNode node=new MapNode();
		node.start=location;
		node.edges=new LinkedList <MapEdge>();
		vertices.put(location, node);
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
		if (!(length<0)||(!(from==null)||(!(to==null))||(!(roadName==null))||(!(roadType==null)))) {
				MapEdge edge =new MapEdge();
				edge.start=from;
				edge.end=to;
				edge.streetName=roadName;
				edge.streetType=roadType;
				edge.distance=length;
				MapNode.edges.add(edge);
		}
		else {
			throw new IllegalArgumentException();
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
	
	//public Set<GeographicPoint> getNeighbors() {
	//	return MapGraph.getVertices();
	//}
	//TODO: fix this method
	public List<GeographicPoint> getNeighbors(GeographicPoint beginning) {
		LinkedList <GeographicPoint> neighbors =new LinkedList();
		//if (beginning==MapEdge.start)
	//	vertices.containsKey(beginning)
		if (beginning==MapEdge.start){
		List<MapEdge>edges=MapNode.getEdges();
		for(int i=0; i<edges.size(); i++){
		GeographicPoint neighbor=MapEdge.getEnd();
		neighbors.add(neighbor);
		}
	return neighbors;
	}
		return neighbors;
	}
	
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		
		//queue - where to go next
		//visited - which Nodes have been viisted
		//parentHashMap - track of the path from start to goal
		
		 //Initialize: queue, visited HashSet and parent HashMap
		Queue <GeographicPoint> queue = new <GeographicPoint>LinkedList();
		// Queue<Integer> queue  = new LinkedList<Integer>(); 
		// Queue  queue = new LinkedList();
		 Set<GeographicPoint> visited = new HashSet<GeographicPoint>();
		 HashMap<GeographicPoint, GeographicPoint > parent = new HashMap<GeographicPoint, GeographicPoint>();
		 //Enqueue S onto the queue  and add to visited
		 queue.add(start);
		 visited.add(start);
		 //while queue is not empty
		 	while(!queue.isEmpty()) { 
		 	//dequeue node curr from front of queue 
		 		GeographicPoint curr = queue.remove();
		 	// if curr == G return parent map
		 		if(curr ==goal) {
			 		return (List<GeographicPoint>) parent;
		 	}
		 	 	 // for each of curr's neighbors, n, not in visited set:
		 		List<GeographicPoint> neighbors=getNeighbors(curr);
		 		ListIterator<GeographicPoint> it = neighbors.listIterator(neighbors.size());
				while (it.hasPrevious()) {
		 		GeographicPoint n=it.previous();
		 		if(!visited.contains(n)){
		 			//add n to visited set	
		 			visited.add(n);
		 			//add curr as n's parent in parent map 
		 			parent.put(n, curr);
		 			 //enqueue n onto the queue 
		 			queue.add(n);
		 			return (List<GeographicPoint>) parent;
		 		}
		 		}
		 	}
			return null;
	}
		 		/*List<MazeNode> neighbors = curr.getNeighbors();
				ListIterator<MazeNode> it = neighbors.listIterator(neighbors.size());
				while (it.hasPrevious()) {
					MazeNode next = it.previous();
					if (!visited.contains(next)) {
						visited.add(next);
						parentMap.put(next, curr);
						toExplore.add(next);
		 		
		 	 // for each of curr's neighbors, n, not in visited set:
		 	 Array neighbors = new Array();
		 	 neighbors = curr.getNeighbors();
		 		for (int i = 0; i<neighbors.size(); i++){
		 			if(!(visited.contains(curr)){
		 			 //add n to visited set	
		 			 visited.add(n);
		 			 //add curr as n's parent in parent map 
		 			 parent.add(curr, n);
		 			 //enqueue n onto the queue 
		 			 queue.enqueue(n);
		 			  // If we get there than there is no path 
		 			}
		 		}
		 			// the algorithm doesn't output the path, but think about what it returns
		 		return  List<GeographicPoint> // ?
		 	}
	}
	/*public List<GeographicPoint> bfs(GeographicPoint start, 
		GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
		{
			if (start == null || goal == null) {
				System.out.println("Start or goal node is null!  No path exists.");
				return null;
			}
			MapLocale startLocale = localesMap.get(start);
			MapLocale goalLocale = localesMap.get(goal);
			if(startLocale == null || goalLocale == null) {
				System.out.println("Use addVertex(GeographicPoint location) to add all vertices the the MapGraph before attempting to find path between them!");
				return null;
			}
			....
		}
		 
		
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}
	

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
		
		MapEdge test1=new MapEdge();
		test1.start=new GeographicPoint(1,0);
		test1.end=new GeographicPoint(2,0);
		test1.streetName="A";
		test1.streetType="main";
		
		MapEdge test2=new MapEdge();
		test2.start=new GeographicPoint(3,0);
		test2.end=new GeographicPoint(4,0);
		test2.streetName="b";
		test2.streetType="other";
		
		MapNode vertice1=new MapNode();
		vertice1.start=new GeographicPoint(1,0);
		vertice1.edges = new LinkedList <MapEdge> ();
		
		MapNode vertice2=new MapNode();
		vertice2.start=new GeographicPoint(2,0);
		vertice2.edges = new LinkedList <MapEdge> ();
		
		MapNode vertice3=new MapNode();
		vertice3.start=new GeographicPoint(3,0);
		vertice3.edges = new LinkedList <MapEdge> ();
		
		
		MapGraph first =new MapGraph();
		GeographicPoint location1 = new GeographicPoint(1,0);
		GeographicPoint location2 = new GeographicPoint(2,0);
		GeographicPoint location3 = new GeographicPoint(3,0);
		first.vertices= new HashMap <GeographicPoint , MapNode>();
		vertices.put(location1, vertice1);
		vertices.put(location2, vertice2);
		vertices.put(location3, vertice3);
		
		int numVertices=first.getNumVertices();
		System.out.println(numVertices);
		System.out.println(first.getVertices());
		System.out.println(first.getNeighbors(location1));
		
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

	public void setVertices(HashMap<GeographicPoint, MapNode> vertices) {
		this.vertices = vertices;
	}
	
}
