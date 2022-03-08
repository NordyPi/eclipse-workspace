package edu.du.cs.loklinnord.lab8;

public class Vertex implements Comparable<Vertex> {
	
	private String name;

	public Vertex(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		Vertex v = (Vertex)o;
		return (this.name.equals(v.name));
	}
	
	public int compareTo(Vertex v) {
		return this.name.compareTo(v.name);
	}
	
	// Needed because Vertex is a key to a HashMap for adjListGraph
	public int hashCode() {
		return this.name.hashCode();
	}
	
	public String toString() {
		return name;
	}
}
