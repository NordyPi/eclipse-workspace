package intro;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
	
	private T[] elements;
	private int size;
	public MyArrayList() {
		elements = (T[]) new Comparable [10];
		size = 0;
	}
	
	public void add(T elem) {
		//Check to see if we need to grow array
		if (size == elements.length) {
			T[] elements2 = (T[]) new Comparable [size+1];
			
			for (int i = 0; i<elements.length; i++) {
				elements2[i] = elements[i];
			}
			
			elements = elements2;
		}
		elements[size] = elem;
		size++;
	}
	public int size() {
		return size;
	}
	
	public String toString() {
		String result = "[";
		
		for (int i=0; i<size-1; i++) {
			result += elements[i] + ", ";
		}
		if (size > 0) { //special case of empty
			result += elements[size-1];
		}
		
		result += "]";
		return result;
	}
	
	public void remove(int index) {
		for (int i = index; i<size-1; i++) {
			elements[i] = elements[i+1];
		}
		size --;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T target) {
		int i = 0;
		while ((i < size) && !(elements[i].equals(target))) {
			i ++;
		}
		if (i == size) {
			return false;
		} else {
			return true;
		}
	}
	
	public void sort() {
		
		
		for (int i=0; i<size-1; i++) {
			for (int j=i+1; j<size; j ++) {
				if (elements[i].compareTo(elements[j]) > 0) {
					T o = elements[i];
					elements[i] = elements[j];
					elements[j] = o;
				}
			}
		}
	}
}
