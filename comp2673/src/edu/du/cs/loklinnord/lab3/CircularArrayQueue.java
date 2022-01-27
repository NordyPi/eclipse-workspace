package edu.du.cs.loklinnord.lab3;

public class CircularArrayQueue<T> implements QueueIf<T> {
    private T[] queue = null;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    CircularArrayQueue(int capacity) {
    	// sets up front and rear to index zero, sets capacity
           this.capacity = capacity;
           queue = (T[])(new Object[capacity]);
           front = 0;
           rear = 0;
    }

	@Override
	public boolean isEmpty() {
		// if size is 0, list is empty!
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		// if the size is equal to capacity, our list is full
		if (size == capacity) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public T peek() {
		return queue[front];
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Tried to remove item from empty queue");
		} else {
			// returns the item at the front of the list, and sets the new front to the next item
			T removed = queue[front];
			queue[front] = null;
			front = (front + 1) % capacity;
			size --;
			return removed;
		}
	}

	@Override
	public void enqueue(T o) {
		// adds item to our queue at the end of the line. if its full, dont do it.
		if (isFull()) {
			throw new RuntimeException("Tried to add to a full queue");
		} else {
			queue[rear] = o;
			size ++;
			rear = (rear + 1) % capacity;
		}
	}
	
	public String toString() {
		// builds toString - NOTE: does not start at front of line
		String output = "";
		for (int i = 0; i < capacity - 1; i++) {
			output += queue[i] + ", ";
		}
		output += queue[capacity - 1];
		return output;
	}
}