package edu.du.cs.loklinnord.lab8;

import java.util.ArrayList;
import java.util.List;

public class AdjMatrixGraph implements Graph {

	// We need a matrix and a mapping from vertex to matrix index

	// List are longer lookup than Map, but easier to use
	//   and makes the toString look great.  If it wasn't for the 
	//   toString a map would be better
	private List<Vertex> vertexIndex; 
	
	// It is a bit silly to use a growing matrix for this since
	// when we use adj matrix we usually know the fixed size
	// So assume we will have a max size and not grow past that
	private Double[][] adjMatrix;
	
	public AdjMatrixGraph(int n) {
		vertexIndex = new ArrayList<Vertex>(n);
		
		// Non growing 2d arrays of fixed size nxn
		adjMatrix = new Double[n][n];
	}
	
	public void insertVertex(Vertex v) {
		// Assume v does not already exist and we are not over size n
		
		// Get an internal index for it by putting it in the next spot
		vertexIndex.add(v);
	}

	public void insertEdge(Edge e) {
		// Assume vertices already exist
		// Look up indices (a map would be faster here)
		// This is a direction edge so only insert it into one location
		int v1Index = vertexIndex.indexOf(e.getVertex1());
		int v2Index = vertexIndex.indexOf(e.getVertex2());
		
		// Put the weight in the graph
		adjMatrix[v1Index][v2Index] = e.getWeight();
	}
	
	public List<Vertex> vertices() {
		return vertexIndex;
	}

	public List<Edge> edges() {
		// Need to create the actual edges from the matrix
		//  since they are not stored in the matrix
		List<Edge> result = new ArrayList<Edge>();
		
		for (int i=0; i<vertexIndex.size(); i++) {
			for (int j=0; j<vertexIndex.size(); j++) {
				if (adjMatrix[i][j] != null) {
					result.add(new Edge(vertexIndex.get(i), vertexIndex.get(j), adjMatrix[i][j]));
				}
			}
		}
		
		return result;
	}

	// All vertices we can get to from v (or [] if none)
	public List<Vertex> adjacentVertices(Vertex v) {
		// Need to create the edges from the matrix
		List<Vertex> result = new ArrayList<Vertex>();
		int vIndex = vertexIndex.indexOf(v);
		for (int i=0; i<vertexIndex.size(); i++) {
			if (adjMatrix[vIndex][i] != null) {
				result.add(vertexIndex.get(i));
			}
		}
		return result;
	}

	// The weight of the edge from v1 to v2 (or null if it doesn't exist)
	public Double edgeWeight(Vertex v1, Vertex v2) {
		int v1Index = vertexIndex.indexOf(v1);
		int v2Index = vertexIndex.indexOf(v2);
		if (adjMatrix[v1Index][v2Index] == null) {
			return null;
		} else {
			return adjMatrix[v1Index][v2Index];
		}
	}
	
	 public String toString() {
		 String result = "AdjMatrix:\n";
		 for (int i=0; i<vertexIndex.size(); i++) {
			 result += "\t" + vertexIndex.get(i);
		 }
		 for (int i=0; i<vertexIndex.size(); i++) {
			 result += "\n" + vertexIndex.get(i);
			 for (int j=0; j<vertexIndex.size(); j++) {
				 result += "\t" + adjMatrix[i][j];
			 }
		 }
		 
		 return result;
	 }
	
	public void updateEdgeWeight(Vertex v1, Vertex v2, double newWeight) {
		// Assume the vertices and edge already exists
		int v1Index = vertexIndex.indexOf(v1);
		int v2Index = vertexIndex.indexOf(v2);
		
		// Put the new weight in the graph
		adjMatrix[v1Index][v2Index] = newWeight;
	}
}
