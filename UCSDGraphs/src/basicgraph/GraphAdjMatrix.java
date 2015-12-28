package basicgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** A class that implements a directed graph.
 * The graph may have self-loops, parallel edges.
 * Vertices are labeled by integers 0 .. n-1
 * and may also have String labels.
 * The edges of the graph are not labeled.
 * Representation of edges via an adjacency matrix.
 *
 * @author UCSD MOOC development team and YOU
 *
 */
public class GraphAdjMatrix extends Graph {

	private final int defaultNumVertices = 5;
	private int[][] adjMatrix;

	/** Create a new empty Graph */
	public GraphAdjMatrix () {
		adjMatrix = new int[defaultNumVertices][defaultNumVertices];
	}

	/**
	 * Implement the abstract method for adding a vertex.
	 * If need to increase dimensions of matrix, double them
	 * to amortize cost.
	 */
	public void implementAddVertex() {
		int v = getNumVertices();
		if (v >= adjMatrix.length) {
			int[][] newAdjMatrix = new int[v*2][v*2];
			for (int i = 0; i < adjMatrix.length; i ++) {
				for (int j = 0; j < adjMatrix.length; j ++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}
			adjMatrix = newAdjMatrix;
		}
		for (int i=0; i < adjMatrix[v].length; i++) {
			adjMatrix[v][i] = 0;
		}
	}

	/**
	 * Implement the abstract method for adding an edge.
	 * Allows for multiple edges between two points:
	 * the entry at row v, column w stores the number of such edges.
	 * @param v the index of the start point for the edge.
	 * @param w the index of the end point for the edge.
	 */
	public void implementAddEdge(int v, int w) {
		adjMatrix[v][w] += 1;
	}

	/**
	 * Implement the abstract method for finding all
	 * out-neighbors of a vertex.
	 * If there are multiple edges between the vertex
	 * and one of its out-neighbors, this neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getNeighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[v][i]; j ++) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	/**
	 * Implement the abstract method for finding all
	 * in-neighbors of a vertex.
	 * If there are multiple edges from another vertex
	 * to this one, the neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[i][v]; j++) {
				inNeighbors.add(i);
			}
		}
		return inNeighbors;
	}

	//For learners to implement
	/**
	 * Implement the abstract method for finding all
	 * vertices reachable by two hops from v.
	 * Use matrix multiplication to record length 2 paths.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getDistance2(int v) {

		int [][] SquareAdjMatrix = MultiplyMatrix(adjMatrix, adjMatrix);

		List<Integer> twoHop = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< SquareAdjMatrix[v][i]; j++) {

				                                                                  //System.out.println("SquareAdjMatrix[v][i] "+SquareAdjMatrix[v][i]);
        twoHop.add(SquareAdjMatrix[v][i]);

			}
		}
		return twoHop;
	}

	/**
	 * Generate string representation of adjacency matrix
	 * @return the String
	 */
	public String adjacencyString() {
		int dim = adjMatrix.length;
		String s = "Adjacency matrix";
		s += " (size " + dim + "x" + dim + " = " + dim* dim + " integers):";
		for (int i = 0; i < dim; i ++) {
			s += "\n\t"+i+": ";
			for (int j = 0; j < adjMatrix[i].length; j++) {
			s += adjMatrix[i][j] + ", ";
			}
		}
		return s;
	}

	//---------------------------------------------------------------------------------------------------------
	/**
    *
    * @param FirstArr
    * @param SecondArr
    * @return
    */
   public int[][] MultiplyMatrix(int FirstArr[][],int SecondArr[][]) {
       int lengthArr = getArrayCountRow(FirstArr);
       int HeightArr = getArrayCountColumn(SecondArr);
       int MultArr[][]= new int [lengthArr][HeightArr];
       int countF = 0;
       int countS = 0;
       if(
    		   getArrayCountColumn(FirstArr) == getArrayCountRow(SecondArr) &&
    		   checkArray(FirstArr) &&
    		   checkArray(SecondArr)
    		) {
    	   for (int k=0; k<getArrayCountRow(FirstArr);k++) {
               for (int n=0; n<getArrayCountColumn(FirstArr);n++) {
                   for (int r=0; r<getArrayCountColumn(FirstArr);r++) {
                       for (int i=0;i<getArrayCountColumn(FirstArr);i++) {
                           countF = FirstArr[k][r]*SecondArr[r][n];
                           countS += countF;
                           break;
                       }
                   MultArr[k][n] = countS;
               }
               countS = 0;
               }
          }
          }
        return MultArr;
    }

   /**
    * @param firstArr is an array which has to be check for rectangularity.
    * @return boolean
    */
   public boolean checkArray(int[][] firstArr) {
      boolean checking = false;
      for (int i=1;i<firstArr.length;i++) {
          checking = firstArr[i].length == firstArr[i-1].length;

       }
      return checking;
   }
   /**
    * @param FirstA is a first array which dimension has to be check and compare
    * with other array.
    * @param SecondA is a second array which has to be check and compare
    * with other array.
    * @return boolean
    */
   public boolean checkArraysDimension(int FirstA[][], int SecondA[][])
   {
      boolean checking = false;
      if (
      checkArray(FirstA) &&
      checkArray(SecondA) &&
      FirstA.length == SecondA.length &&
      FirstA[0].length == SecondA[0].length &&
      getArrayCountColumn(FirstA)!=-1 &&
      getArrayCountRow(FirstA)!=-1
      ) {
      checking = true;
      }
      return checking;
   }
   /**
    * @param is is an  array which has to be printed.
    *
    */
   public void printMatrixArray(int[][] is) {
   if (checkArray(is)) {
           for (int [] PrintedArr1 : is) {
               for (int k = 0; k<is[0].length; k++) {
                   System.out.print(PrintedArr1[k] + "\t");
               }
               System.out.println();
           }
        }else {System.out.println("Matrix is not rectangular");}
   }
   /**
    *
    * @param firstArr is an array which rows has to be counted
    * @return amount of rows
    */
   public int getArrayCountRow(int[][] firstArr) {
        int i = 0;
        if (firstArr.length<=0) {
           i = -1;
       }else{i=firstArr.length;}
       return i;
   }
   /**
    *
    * @param secondArr is an array which columns has to be counted
    * @return amount of columns
    */
    public int getArrayCountColumn(int[][] secondArr) {
        int i = 0;
        if (secondArr.length<=0 ||
                secondArr[0].length<=0 ||
                checkArray(secondArr)==false) {
           i = -1;}else{
            i=secondArr[0].length;
        }
       return i;
   }
   /**
    *
    * @param MatrixArrayNoNameFirst
    * @param MatrixArrayNoNameSecond
    * @return
    */
    public boolean checkArraysWidth(int MatrixArrayNoNameFirst [][],
            int MatrixArrayNoNameSecond [][]) {
        boolean checking = false;
        if (getArrayCountColumn(MatrixArrayNoNameFirst)==getArrayCountColumn(MatrixArrayNoNameSecond)){
            checking = true;
       }
        return checking;
       }



}
