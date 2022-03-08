package edu.du.cs.dsteven.graphSoln;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class AdjListGraph implements Graph {
	
	// This implementation is similar to what they had to do
	//   in the Grammar assignment if that was given as assignment1
	Map<Vertex, List<Edge>> adjList;
	
	public AdjListGraph() {
		adjList = new HashMap<Vertex, List<Edge>>();
	}

	public void insertVertex(Vertex v) {
		// Add it into the map with a empty edge list
		// Assume it doesn't already exist
		adjList.put(v, new ArrayList<Edge>());
	}

	public void insertEdge(Edge e) {
		// Directional edge, so insert it into the v1 list
		// Note that get doesn't return a copy, but a pointer to 
		//   the actual list.  Thus, we can just add to it rather than
		//   adding to the copy and replacing the old with with the
		//   new one.
		adjList.get(e.getVertex1()).add(e);
	}
	
	public List<Vertex> vertices() {
		// Get the keys as a Set and then form the list from that set
		return new ArrayList<Vertex>(adjList.keySet());
	}

	public List<Edge> edges() {
		List<Edge> result = new ArrayList<Edge>();
		Set<Vertex> keys = adjList.keySet();
		for (Vertex v: keys) {
			result.addAll(adjList.get(v));
		}
		return result;
	}

	// All vertices we can get to from v (or [] if none)
	public List<Vertex> adjacentVertices(Vertex v) {
		List<Vertex> result = new ArrayList<Vertex>();
		List<Edge> out = adjList.get(v);
		for (int i=0; i<out.size(); i++) {
			result.add(out.get(i).getVertex2());
		}
		return result;
	}

	// The weight of the edge from v1 to v2 (or null if it doesn't exist)
	public Double edgeWeight(Vertex v1, Vertex v2) {
		List<Edge> out = adjList.get(v1);
		int i = 0;
		while (i<out.size() && !(v2.equals(out.get(i).getVertex2()))) {
			i++;
		}
		if (i==out.size()) {
			return null;
		} else {
			return out.get(i).getWeight();
		}
	}

	public String toString() {
		String result = "AdjList: ";
		Set<Vertex> keys = adjList.keySet();
		for (Vertex v: keys) {
			result += "\n  " + v.toString() + ": " + adjList.get(v).toString();
		}
		return result;
	}
	
	public void updateEdgeWeight(Vertex v1, Vertex v2, double newWeight) {
		// Since we are storing edges and they can't mutate edge weight
		//   we need to remove the old one and then add the new one
		//  which is a bit awkward
		// Making it more awkward is that we don't know the old weight
		//   in order to remove it, so we need to look it up
		double oldWeight = edgeWeight(v1, v2);
		
		// Get the list the edge would be in
		List<Edge> out = adjList.get(v1);
	
		// Remove the old one
		out.remove(new Edge(v1, v2, oldWeight));
		
		// Add the new one
		out.add(new Edge(v1, v2, newWeight));
	}
}
