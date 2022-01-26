package edu.du.cs.loklinnord.lab3;

public class CircularArrayQueue<T> implements QueueIf<T> {
    private T[] queue = null;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    CircularArrayQueue(int capacity) {
           this.capacity = capacity;
           queue = (T[])(new Object[capacity]);
           front = 0;
           rear = 0;
    }

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
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
		T item = queue[front];
		queue[front] = null;
		front = (front + 1) % 10;
		size --;
		return item;
	}

	@Override
	public void enqueue(T o) {
		queue[rear] = o;
		size ++;
		rear = (rear + 1) % 10;
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < capacity - 1; i++) {
			output += queue[i] + ", ";
		}
		output += queue[capacity - 1];
		return output;
	}
}