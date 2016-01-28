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



    //*************************************************************************************************
	//list of helper methods

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
			MapEdge addedMapEdge = new MapEdge(startNode, finishNode, roadName, roadType, length);

			/*
			set a speed limit on the road that depending od a type of a street
			motorway_link  with no limitatios (we could use 500 km/h)
			             (A) road, specially designed and built for motor traffic,
			             which does not serve properties bordering on it, and which:
			             is provided, except at special points or temporarily,
			             with separate carriageways for the two directions of traffic,
			             separated from each other, either by a dividing strip not intended for traffic,
			             or exceptionally by other means;
			             does not cross at level with any road, railway or tramway track, or footpath;
			             is specially signposted as a motorway and is reserved for specific categories of road motor vehicles.
			             'Entry and exit lanes of motorways are included irrespectively
			             of the location of the signposts. Urban motorways are also included.'
			             @see https://www.wikiwand.com/en/Controlled-access_highway
			residential (max speed limit in most cases 60 km/h)
			             Residential throughways such as 19th Avenue, Guerrero, California, Oak and Fell Streets
			             have high levels of fast-moving traffic with residential land uses.
			             As such, they are often not designed to serve residential uses, and can be unpleasant to walk or live along.
			             Streetscape improvements should focus on buffering the sidewalk and adjacent homes from vehicles
			             passing in the street and providing a generous, useable public realm through landscaping, curb extensions,
			             or widened sidewalks where roadway space allows.
			             @see http://www.sfbetterstreets.org/design-guidelines/street-types/residential-throughways/
			living_street (max speed limit on the world 20 km/h)
			             A living street is a street designed primarily with the interests of pedestrians
			             and cyclists in mind and as a social space where people can meet and where
			             children may also be able to play legally and safely.
			             These roads are still available for use by motor vehicles, however their design aims
			             to reduce both the speed and dominance of motorised transport.
			             This is often achieved using the shared space approach, with greatly reduced demarcations
			             between vehicle traffic and pedestrians. Vehicle parking may also be restricted to designated bays.
			             It became popular during the 1970s in the Netherlands, which is why
			             the Dutch word for a living street (woonerf) is often used as a synonym.

			             Country-specific living street implementations include: home zone (United Kingdom),
			             residential zone (ru:Жилая зона, Russia), shared zone (Australia/New Zealand),
			             woonerf (Netherlands and Flanders) and zone residentielle (France).
            secondary
                         a simple secondary highway. The maximum speed, in kilometers per hour which is allowed on the road, for example 120
			tertiary
			             The highway=tertiary tag is used for roads connecting smaller settlements, and within large settlements for roads connecting local centres.
			             In terms of the transportation network, OpenStreetMap "tertiary" roads commonly also connect minor streets to more major roads.
			             (max speed limit in most cases 80 km/h)
			unclassified (max speed limit in most cases 80 km/h)
			             The tag highway=unclassified is used for minor public roads typically at the lowest level of the interconnecting grid network.
			             Unclassified roads have lower importance in the road network than tertiary roads, and are not residential streets or agricultural tracks.
			             highway=unclassified should be used for roads used for local traffic and used to connect other towns, villages or hamlets.
			             Unclassified roads are considered usable by motor cars.
			             @see http://wiki.openstreetmap.org/wiki/Tag:highway%3Dunclassified
			
			@see https://www.wikiwand.com/en/Hierarchy_of_roads
			@see https://www.wikiwand.com/en/Street_hierarchy
			*/
			if ((roadType.compareTo("motorway") == 0) || (roadType.compareTo("motorway_link") == 0)) {
				addedMapEdge.setSpeedLimit(500);
			} else if (roadType.compareTo("living_street") == 0) {
				addedMapEdge.setSpeedLimit(20);
			} else if (roadType.compareTo("residential") == 0) {
				addedMapEdge.setSpeedLimit(60);
			} else if (roadType.compareTo("unclassified") == 0) {
				addedMapEdge.setSpeedLimit(80);
			} else if ((roadType.compareTo("secondary") == 0) || (roadType.compareTo("secondary_link") == 0)) {
				addedMapEdge.setSpeedLimit(120);
			} else if ((roadType.compareTo("tertiary") == 0) || (roadType.compareTo("tertiary_link") == 0)) {
				addedMapEdge.setSpeedLimit(80);
			}else if ((roadType.compareTo("primary") == 0) || (roadType.compareTo("primary_link") == 0)) {
				addedMapEdge.setSpeedLimit(80);
			}else if ((roadType.compareTo("trunk") == 0) || (roadType.compareTo(" trunk_link") == 0)) {
				addedMapEdge.setSpeedLimit(90);
			}

			//test it
            System.out.println(addedMapEdge.toString());

            //add OUTCOMING edge from -> to
			listNodes.get(from).getListEdges().add(addedMapEdge);

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
			att.add(mdn);
			}
		return att;
	}
	/**
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	private double getSpeedLimit(MapNode start, MapNode end) {
		double lim = 0;
		List<MapEdge> listForSearch = start.getListEdges();
		for (MapEdge sch : listForSearch) {
			if ((sch.getStartNode().getNodeLocation().toString().compareTo(start.getNodeLocation().toString()) == 0) &&
					(sch.getFinishNode().getNodeLocation().toString().compareTo(end.getNodeLocation().toString()) == 0)	) {
				return lim = sch.getSpeedLimit();
			}
		}
		return lim;
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
	private void printNodesMap(HashMap<MapNode,MapNode> parentMap){
		for (Map.Entry<MapNode, MapNode> entry : parentMap.entrySet()) {
			System.out.print( "key" + "\t" + entry.getKey().getNodeLocation().toString() + "\t" + "distance " + entry.getKey().getDistance() + "\t" + "time " + entry.getKey().getTime() + "\t");
			System.out.println( "value" + "\t" + entry.getValue().getNodeLocation().toString() + "\t" + "distance " + entry.getValue().getDistance() + "\t" + "time " + entry.getValue().getTime());
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
			if ((sch.getStartNode().getNodeLocation().toString().compareTo(start.getNodeLocation().toString()) == 0) &&
					(sch.getFinishNode().getNodeLocation().toString().compareTo(end.getNodeLocation().toString()) == 0)	) {
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
	/**
	 * a helper method for using it as distance priority
	 * @return comparator
	 */
	public Comparator<MapNode> createComparatorByTime() {
		Comparator<MapNode> comparator = new Comparator<MapNode>() {
            @Override
            public int compare(MapNode x, MapNode y) {
                // You could return x.getDistance() - y.getDistance(), which would be more efficient.
            	return (int) (x.getTime()-y.getTime());
            	}
            };
		return comparator;
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
	/**
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	private double getTimeBetweenNodes(MapNode start, MapNode end) {
		// TODO Auto-generated method stub
		double limit = getSpeedLimit(start, end);
		double dist = getLengthEdgeBeetwen(start, end);
		return dist/limit;
	}





    //*************************************************************************************************
	//list of searching methods


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
					if ((goal.toString().compareTo(curr.getNodeLocation().toString())) == 0) break;
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
					double redCost = getReducedCost(start,goal);                                             //System.out.println(redCost);
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
											//test it
                                            //System.out.println(getReducedCost(curr.getNodeLocation(),goal));

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

	//==============================================================================================================
	//MY ECSTENSION
	//==============================================================================================================

	/** Find the path from start to goal using Dijkstra's algorithm
	 *
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the faster path from
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstraBySpeed(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstraBySpeed(start, goal, temp);
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 *
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the faster path from
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstraBySpeed(GeographicPoint start, GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 3
		List<GeographicPoint> lfs = new LinkedList<>();
		if (listNodes.containsKey(start) && listNodes.containsKey(goal)) {
			//initialize ADT
			//we should use a comparator created depends on time!!!
			Comparator<MapNode> cmtr = createComparatorByTime();
			PriorityQueue<MapNode> pq = new PriorityQueue<>(5, cmtr);
			HashMap<MapNode, MapNode> parentMap = new HashMap<>();
			Set<MapNode> visited = new HashSet<>();
			//set a time to goal to infinity
			for(Map.Entry<GeographicPoint,MapNode> entry : listNodes.entrySet()) {
				entry.getValue().setTime(Double.POSITIVE_INFINITY);
			}
			//get a start and goal node
			MapNode startNode = listNodes.get(start);
			MapNode goalNode = listNodes.get(goal);
			//set a time start node as 0
			startNode.setTime(0.0);
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
					if ((goal.toString().compareTo(curr.getNodeLocation().toString())) == 0) break;
					//for each of curr's neighbors, "next", ->
					List<MapNode> neighbors = getNeighbours(curr);
					for(MapNode next : neighbors) {
						//not in visited set ->
						if(!visited.contains(next)) {
							//if path through curr to n is faster ->
							double timeToNextNode = getTimeBetweenNodes(curr, next);
							                                                                 //test it
							                                                                 //System.out.println("timeToNextNode" + "\t" +timeToNextNode);
							if(curr.getTime()+timeToNextNode < next.getTime()) {
									//update next's speed Limit
									next.setTime(curr.getTime()+timeToNextNode);
									parentMap.put(next, curr);
					            }
								//enqueue into the pq
								pq.add(next);
						}
					}
				}
			}
                                                                                            //test it
                                                                                            //System.out.println("--------------------------------------");
			                                                                                //printNodesMap(parentMap);
			lfs = reconstructPath(parentMap, startNode, goalNode);
		} else {throw new NullPointerException("Cannot find route from or to null node");}
		return lfs;
	}
	

	/** Find the path from start to goal using A-Star search
	 *
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearchByTime(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearchByTime(start, goal, temp);
	}
	/** Find the path from start to goal using A-Star search
	 *
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearchByTime(GeographicPoint start,
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
				List<GeographicPoint> lfs = new LinkedList<>();
				if (listNodes.containsKey(start) && listNodes.containsKey(goal)) {
					//initialize ADT
					//we should use a comparator created depends on time!!!
					Comparator<MapNode> cmtr = createComparatorByTime();
					PriorityQueue<MapNode> pq = new PriorityQueue<>(5, cmtr);
					HashMap<MapNode, MapNode> parentMap = new HashMap<>();
					Set<MapNode> visited = new HashSet<>();
					//set a time to goal to infinity
					for(Map.Entry<GeographicPoint,MapNode> entry : listNodes.entrySet()) {
						entry.getValue().setTime(Double.POSITIVE_INFINITY);
					}
                   //getting a  reduced cost
					double redCost = getReducedCost(start,goal);                                             //System.out.println(redCost);
                   //get a start and goal node
					MapNode startNode = listNodes.get(start);
					MapNode goalNode = listNodes.get(goal);
					//set a distance start node as 0
					startNode.setTime(0.0);
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
									//if path through curr to n is faster ->
									double timeToNextNode = getTimeBetweenNodes(curr, next);
									                                                                 //test it
									                                                                 //System.out.println("timeToNextNode" + "\t" +timeToNextNode);
									if((curr.getTime()+timeToNextNode < next.getTime())
												&& getReducedCost(curr.getNodeLocation(),goal)<=redCost
												) {
											//test it
                                           //System.out.println(getReducedCost(curr.getNodeLocation(),goal));

											//update next's distance
										    next.setTime(curr.getTime()+timeToNextNode);
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



	public static void main(String[] args)
	{
		/*
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
        */

		//System.out.println("Num nodes: " + theMap.getNumVertices());
		//System.out.println("Num edges: " + theMap.getNumEdges());
		//List<GeographicPoint> route = theMap.dijkstra(new GeographicPoint(1.0,1.0), new GeographicPoint(8.0,-1.0));

		//for (GeographicPoint gp: route) {
		//	System.out.println(gp.toString());
		//}

		
		
		//-----------------------------------------------------
		//basic map
		//Use this code in Week 3 End of Week Quiz
		/*

		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);

		List<GeographicPoint> route_1 = theMap.bfs(start,end);
		List<GeographicPoint> route_2 = theMap.dijkstra(start,end);
		List<GeographicPoint> route_3 = theMap.aStarSearch(start,end);
		List<GeographicPoint> route_4 = theMap.dijkstraBySpeed(start,end);
		List<GeographicPoint> route_5 = theMap.aStarSearchByTime(start,end);

		System.out.println(
				"Using BFS algorithm to find SHORTEST path" + "\t" + route_1.size() + "\n" +
				"Using Dijkstra algorithm to find SHORTEST path" + "\t" + route_2.size() + "\n" +
				"Using A-star algorithm to find SHORTEST path" + "\t" + route_3.size() + "\n" +
				"Using Dijkstra algorithm to find FASTER path" + "\t" + route_4.size() + "\n"
				+
				"Using A-star algorithm to find FASTER path" + "\t" + route_5.size()
				);
		
		*/
		
		//-----------------------------------------------------
		//another map
		
		System.out.print("Making a new map...");
		MapGraph mapOfMyDistrict = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/myDistrict_big.map", mapOfMyDistrict);
		System.out.println("DONE.");
		
		GeographicPoint startMy = new GeographicPoint(59.9305655, 30.4824903);
		GeographicPoint endMy = new GeographicPoint(59.8980511, 30.4444886);
		
		List<GeographicPoint> My_route_1 = mapOfMyDistrict.bfs(startMy,endMy);
		List<GeographicPoint> My_route_2 = mapOfMyDistrict.dijkstra(startMy,endMy);
		List<GeographicPoint> My_route_3 = mapOfMyDistrict.aStarSearch(startMy,endMy);
		List<GeographicPoint> My_route_4 = mapOfMyDistrict.dijkstraBySpeed(startMy,endMy);
		List<GeographicPoint> My_route_5 = mapOfMyDistrict.aStarSearchByTime(startMy,endMy);
		
		System.out.println(
				"Using BFS algorithm to find SHORTEST path" + "\t" + My_route_1.size() + "\n"
		        +
				"Using Dijkstra algorithm to find SHORTEST path" + "\t" + My_route_2.size() + "\n"
		        +
				"Using A-star algorithm to find SHORTEST path" + "\t" + My_route_3.size() + "\n"
		        +
				"Using Dijkstra algorithm to find FASTER path" + "\t" + My_route_4.size() + "\n"
				+
				"Using A-star algorithm to find FASTER path" + "\t" + My_route_5.size()
				);
				
		


	}

}
