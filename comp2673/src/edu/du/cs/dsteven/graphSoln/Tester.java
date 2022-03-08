package edu.du.cs.dsteven.graphSoln;

public class Tester {

	public static void main(String[] args) {
		
		Graph g = new AdjListGraph();
		//Graph g = new AdjMatrixGraph(10);
		
		// Create and insert vertices
		Vertex a = new Vertex("a");
		g.insertVertex(a);
		Vertex b = new Vertex("b");
		g.insertVertex(b);
		Vertex c = new Vertex("c");
		g.insertVertex(c);
		Vertex d = new Vertex("d");
		g.insertVertex(d);
		Vertex e = new Vertex("e");
		g.insertVertex(e);
		Vertex f = new Vertex("f");
		g.insertVertex(f);
		
		// Create and insert edges
		g.insertEdge(new Edge(a, b, 5));
		g.insertEdge(new Edge(a, d, 3));
		g.insertEdge(new Edge(b, a, 1));
		g.insertEdge(new Edge(d, e, 7));
		g.insertEdge(new Edge(b, f, 9));
	
		
		// Display internal graph representation
		System.out.println(g);
		
		// Ask some graph questions
		System.out.println(g.vertices());
		System.out.println(g.edges());
		
		System.out.println(g.adjacentVertices(a));
		System.out.println(g.adjacentVertices(d));
		System.out.println(g.adjacentVertices(f));
		
		System.out.println(g.edgeWeight(a, b));
		System.out.println(g.edgeWeight(c, f));
		
		g.updateEdgeWeight(a, b, 4);
		System.out.println(g.edgeWeight(a, b));
	}
}
