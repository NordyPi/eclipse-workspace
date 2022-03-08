package edu.du.cs.dsteven.graphSoln;

import java.util.List;

public interface Graph {
	public void insertVertex(Vertex v); // v must not already be in graph
	public void insertEdge(Edge e); // edge vertices must be in graph
	
	public List<Vertex> vertices(); // All vertices in graph
	public List<Edge> edges(); // All edges in graph
	
	public List<Vertex> adjacentVertices(Vertex v);  // All vertices we can reach from v (or [] if none)
	public Double edgeWeight(Vertex v1, Vertex v2); // The weight of the edge from v1 to v2 (or null if doesn't exist)
	public void updateEdgeWeight(Vertex v1, Vertex v2, double newWeight); // Replace weight of existing edge
}
