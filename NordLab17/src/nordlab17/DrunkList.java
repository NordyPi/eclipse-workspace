package nordlab17;

import java.util.ArrayList;

public class DrunkList<T> {
	private ArrayList<T> list;
	
	public DrunkList() {
		list = new ArrayList<T>();
	}
	public void add(T t) {
		list.add(t);
	}
	public T get() {
		int r = (int)(Math.random() * list.size());
		T temp = list.get(r);
		return temp;
	}
}
