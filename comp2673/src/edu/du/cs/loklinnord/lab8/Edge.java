package edu.du.cs.loklinnord.lab8;

public class Edge implements Comparable<Edge> {
	
	private Vertex v1;
	private Vertex v2;
	private double weight;
	
	public Edge(Vertex v1, Vertex v2, double weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public Vertex getVertex1() {
		return v1;
	}
	
	public Vertex getVertex2() {
		return v2;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public boolean equals(Object o) {
		Edge e = (Edge)o;
		return ((this.v1.equals(e.v1)) &&
				(this.v2.equals(e.v2)) &&
				(this.weight == e.weight));
	}
	
	public int compareTo(Edge e) {
		int result = (int)(this.weight - e.weight);
		if (result == 0) {
			result = this.v1.compareTo(e.v1);
			if (result == 0) {
				result = this.v2.compareTo(e.v2);
			}
		}
		return result;
	}
	
	public int hashCode() {
		return v1.hashCode() + v2.hashCode() + (int)weight;
	}
	
	public String toString() {
		return "Edge: " + v1 + " - " + weight + " - " + v2;
	}
}
