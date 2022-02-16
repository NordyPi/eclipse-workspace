package edu.du.cs.loklinnord.lab5;

public class HeapTest {

	public static void main(String[] args) {
	
		// Create heap using values from ppt
		Integer[] heapvalues = {63, 30, 40, 10, 25, 8, 38, 5, 3, 18};
		Heap<Integer> h = new Heap<Integer>(15, heapvalues);
		System.out.println(h);
		
		// Insert 50 just like in ppt
		h.insert(50);
		System.out.println(h);
		
		// Reset heap to original starting values
		h = new Heap<Integer>(15, heapvalues);
		System.out.println(h);
		
		// Remove the top just like in ppt
		System.out.println(h.removeMax());
		System.out.println(h);
				
		// Remove all the elements from the heap
		for (int i=0; i<heapvalues.length-1; i++) {
			System.out.println(h.removeMax());
			System.out.println(h);
		}
	}
}
