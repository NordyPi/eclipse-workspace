package edu.du.cs.loklinnord.lab5;


// A max heap class
public class Heap<T extends Comparable<T>> {
	
	private int n;
	private int size;
	private T[] values;
	
	// Create an empty heap that can hold N elements
	@SuppressWarnings("unchecked")
	public Heap(int n) {
		this.n = n;  // max size of the heap
		this.size = 0;  // nothing added to the heap
		this.values = (T[])new Comparable[n];  // heap values
	}
	
	// Create a heap from an array
	@SuppressWarnings("unchecked")
	public Heap(int n, T[] values) {
		this.n = n;  // max size of the heap
		this.size = values.length;  // heap has values in it already
		// Make a deep copy of the heap values
		this.values = (T[])new Comparable[n];
		for (int i=0; i<size; i++) {
			this.values[i] = values[i];
		}
	}

	public void insert(T value) {
		
		if (size == n) {
			System.out.println("Heap is full");
		} else {
			// Insert at the last position
			int current = size;
			values[current] = value;
			
			// Filter up
			int parent = (current-1)/2;
			// Parent must be bigger than its child (current), otherwise we keep filtering
			while ((current > 0) &&
				   (values[parent].compareTo(values[current]) < 0)) {
				// Swap values
				T temp = values[parent];
				values[parent] = values[current];
				values[current] = temp;
				
				// Move up
				current = parent;
				parent = (current-1)/2;
			}
			size++;
		}
	}
	
	public T removeMax() {
		
		
		return null;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public String toString() {
		String result = "";
		for (int i=0; i<size; i++) {
			result += values[i] + " ";
		}
		return result;
	}
}
