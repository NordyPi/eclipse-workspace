package nordlab17;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<T>> extends Queue {
	ArrayList<T> element;
	
	public PriorityQueue() {
		this.element = new ArrayList<T>();
	}
	
	public void enqueue(T t) {
		this.element.add(t);
	}
	
	public T dequeue() {
		T temp = element.get(0);
		int index = 0;
		for (int i = 1; i < element.size(); i ++) {
			if (element.get(i).compareTo(temp) > 0) {
				temp = (T) element.get(i);
				index = i;
			}
		}
		element.remove(index);
		return temp;
	}

}