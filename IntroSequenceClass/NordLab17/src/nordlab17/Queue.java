package nordlab17;

import java.util.ArrayList;

public class Queue<T> {
	ArrayList<T> element;
	
	public Queue() {
		this.element = new ArrayList<T>();
	}
	
	public void enqueue(T t) {
		this.element.add(t);
	}
	
	public T dequeue() {
		T temp = element.get(0);
		element.remove(0);
		return temp;
		
	}
	public boolean isEmpty() {
		if (element.size() < 1) {
			return true;
		} else {
			return false;
		}
	}
}
