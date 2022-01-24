package edu.du.cs.loklinnord.lab2;

public class DLList<T> implements ListIf<T>{
	// Nested helper class
		private class Node {
			// These are private, but LList can see them since Node is a nested class
			private T element;
			private Node next;
			// Now we have previous node as well
			private Node prev;

			public Node(T o) {
				element = o;
				next = null;
				prev = null;
			}
		}

		// LList's instance variables
		private Node first;
		private int size;
		// Now needs to keep track of the last element of the list
		private Node last;

		// Default constructor
		public DLList() {
			first = null;// empty list
			last = null;
			size = 0;
		}

		// simply allows us to call without specifying index, implying add to the end. calls add(index, object)
		public void add(T o) {
			add(size, o);
		}
		
		// Added to the middle (or start) or end
		public void add(int index, T o) {
			Node nn = new Node(o);
			// if the list is empty, object is added and it is both first and last node
			if (size == 0) {
				first = nn;
				last = nn;
			} else if (index == size) {
				// Added to the end, BUT different since we now know the last element and don't need to walk through with size
				nn.prev = last;
			    last.next = nn;
			    last = nn;  
			} else if (index == 0) {
				// Special case for adding to start
				nn.next = first;
				first.prev = nn;
				nn.prev = null;
				first = nn;
			} else { // works for middle and end cases
				// Find the node before index
				Node n = first;
				for (int i = 0; i < index - 1; i++) {
					n = n.next;
				}
				n.next.prev = nn;
				nn.next = n.next;         
				n.next = nn;
				nn.prev = n;	
			}

			size++;
		}

		// Get
		public T get(int index) {

			Node n = first;
			for (int i = 0; i < index; i++) {
				n = n.next;
			}
			return n.element;
		}

		// Remove
		// TODO: If list has only one item, code in that case in
		public T remove(int index) {
			T result = null;
			// if the array only has one element, correctly set nodes
			if (size == 0) {
				first = null;
				last = null;
			} else 
			// Special case for removal from beginning
			if (index == 0) {
				result = first.element;
				first = first.next;
				first.prev = null;
			} else 
			// Special case for removal at the end
			if (index == size-1) {
				result = last.element;
				last = last.prev;
				last.next = null;
			} else { // works for middle and end cases
				// Find the node before index
				Node n = first;
				for (int i = 0; i < index - 1; i++) {
					n = n.next;
				}
				result = n.next.element;
				n.next = n.next.next;
				n.next.prev = n;
			}

			size--;
			
			return result;
		}

		@Override
		public void set(int index, T o) {
			Node n = first;
			
			// Special case for changing the first item
			if (index == 0) {
				first.element = o;

			} else { // works for middle and end cases
				//Find our index
				for (int i = 0; i < index; i++) {
					n = n.next;
				}
				n.element = o;
			}
		}

		@Override
		public boolean remove(T o) {
			//first checks to see if item exists
			int i = indexOf(o);
			if (i >= 0) {
				//then calls remove on the index
				remove(i);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains(T o) {
			Node n = first;
			//loops through and tries to find index
			for (int i = 0; i < size; i++) {
				if (n.element.equals(o)) {
					return true;
				}
				n = n.next;
			}
			return false;
		}

		@Override
		public int indexOf(T o) {
			Node n = first;
			//loops through and tries to find index using same code as compare
			for (int i = 0; i < size; i++) {
				if (n.element.equals(o)) {
					return i;
				}
				n = n.next;
			}
			//if not found return -1
			return -1;
		}
		
		public String toString() {
			//makes blank string, starts at beginning
			Node n = first;
			String output = "";
			//loops through and builds list
			for (int i = 0; i < size-1; i++) {
				output = output + n.element + ", ";
				n = n.next;
			}
			//if last object, don't add , space
			output = output + n.element;
			return output;
		}
		
		public String reverseToString() {
			Node n = last;
			String output = "";
			// This will go back from last until first, which points to null, and then stop
			while (n != null) {
				output = output + n.element;
				n = n.prev;
			}
			// Not sure how to configure while so that last element doesn't add extra ", "
			return output;
		}
}
