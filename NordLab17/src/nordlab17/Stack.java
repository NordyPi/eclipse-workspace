package nordlab17;

import java.util.ArrayList;

public class Stack<T> {
	ArrayList<T> element;
	
	public Stack() {
		element = new ArrayList<T>();
	}
	
	public void push(T t) {
		element.add(t);
	}
	
	public T pop() {
		T temp = element.get(element.size()-1);
		element.remove(element.size()-1);
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

