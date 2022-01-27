package edu.du.cs.loklinnord.lab3;

import edu.du.cs.loklinnord.lab2.*;

public class LinkedListQueue<T> implements QueueIf<T> {

	DLList linked;
	int capacity;
	
	public LinkedListQueue(int size) {
		linked = new DLList();
		this.capacity = size;
	}
	
	@Override
	public boolean isEmpty() {
		if (linked.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (linked.size() == capacity) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSize() {
		return linked.size();
	}

	@Override
	public T peek() {
		return (T) linked.get(0);
	}

	@Override
	public T dequeue() {
		if (!isEmpty()) {
			T removed = (T) linked.get(0);
			linked.remove(0);
			return removed;
		} else {
			throw new RuntimeException("Attempted to remove from an empty list");
		}
		
	}

	@Override
	public void enqueue(T o) {
		if (!isFull()) {
			linked.add(o);
		} else {
			throw new RuntimeException("Attempted to add to a full list");
		}
	}
	
	public String toString() {
		return linked.toString();
	}

}
