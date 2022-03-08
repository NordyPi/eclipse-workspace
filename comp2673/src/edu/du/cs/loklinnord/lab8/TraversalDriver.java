package edu.du.cs.loklinnord.lab8;

import java.util.List;
import java.util.ArrayList;

public class TraversalDriver {
	
	public static List<Vertex> breadthFirstTraversal(Graph g, Vertex v) {
		// make a new queue, a new processed vertex list, and set loops to 1
		LinkedListQueue toProcess = new LinkedListQueue(10);
		List<Vertex> processed = new ArrayList<Vertex>();
		int loops = 1;
		toProcess.enqueue(v);
		// enqueue the root and start looping
		for (int i = 0; i < loops; i ++) {
			// remove the front item of list and process it
			Vertex current = (Vertex) toProcess.dequeue();
			if (processed.contains(current)) {
				// do nothing since the current vertex has already been processed
			} else {
				// add vertex to the processed list, get a list of connected vertices. increase loop count
				processed.add(current);
				List<Vertex> adjacents = g.adjacentVertices(current);
				for (Vertex vert: adjacents) {
					toProcess.enqueue(vert);
					loops ++;
				}
			}
		}
		return processed;
	}
	
	public static List<Vertex> depthFirstTraversal(Graph g, Vertex v) {
		// make a new queue, a new processed vertex list, and set loops to 1
		ArrayStack toProcess = new ArrayStack(10);
		List<Vertex> processed = new ArrayList<Vertex>();
		int loops = 1;
		toProcess.push(v);
		// enqueue the root and start looping
		for (int i = 0; i < loops; i ++) {
			// remove the front item of list and process it
			Vertex current = (Vertex) toProcess.pop();
			if (processed.contains(current)) {
				// do nothing since the current vertex has already been processed
			} else {
				// add vertex to the processed list, get a list of connected vertices. increase loop count
				processed.add(current);
				List<Vertex> adjacents = g.adjacentVertices(current);
				for (Vertex vert: adjacents) {
					toProcess.push(vert);
					loops ++;
				}
			}
		}
		return processed;
	}
	
	public static List<Vertex> bestFirstTraversal(Graph g, Vertex v) {
		// make a new queue, a new processed vertex list, and set loops to 1
		Heap toProcess = new Heap(10);
		List<Vertex> processed = new ArrayList<Vertex>();
		int loops = 1;
		toProcess.insert(v);
		// enqueue the root and start looping
		for (int i = 0; i < loops; i ++) {
			// remove the front item of list and process it
			Vertex current = (Vertex) toProcess.removeMax();
			if (processed.contains(current)) {
				// do nothing since the current vertex has already been processed
			} else {
				// add vertex to the processed list, get a list of connected vertices. increase loop count
				processed.add(current);
				List<Vertex> adjacents = g.adjacentVertices(current);
				for (Vertex vert: adjacents) {
					toProcess.insert(vert);
					loops ++;
				}
			}
		}
		return processed;
	}
	
	public static void depthFirstTraversalRec(Graph g, Vertex v, List<Vertex> list) {
		if (list.contains(v)) {
			// do nothing at all. this is the base case
		} else {
			list.add(v);
			List<Vertex> adjacents = g.adjacentVertices(v);
			for (Vertex vert: adjacents) {
				depthFirstTraversalRec(g, vert, list);
			}
		}
	}

	public static void main(String[] args) {
	
		Graph g = new AdjMatrixGraph(10);
		
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
		// We will make this a bi-directional graph
		// Weights won't matter for this example
		g.insertEdge(new Edge(a, b, 1));
		g.insertEdge(new Edge(b, a, 1));
		g.insertEdge(new Edge(a, d, 1));
		g.insertEdge(new Edge(d, a, 1));
		g.insertEdge(new Edge(b, c, 1));
		g.insertEdge(new Edge(c, b, 1));
		g.insertEdge(new Edge(c, f, 1));
		g.insertEdge(new Edge(f, c, 1));
		g.insertEdge(new Edge(d, f, 1));
		g.insertEdge(new Edge(f, d, 1));
		g.insertEdge(new Edge(d, e, 1));
		g.insertEdge(new Edge(e, d, 1));
		
		
		System.out.println("Breadth First");
		System.out.println(breadthFirstTraversal(g, a));
		
		System.out.println("Depth First");
		System.out.println(depthFirstTraversal(g, a));
		
		System.out.println("Best First");
		System.out.println(bestFirstTraversal(g, a));
		
		System.out.println("Depth First Rec");
		List<Vertex> processed = new ArrayList<Vertex>();
		depthFirstTraversalRec(g, a, processed);
		System.out.println(processed);

	}

}
