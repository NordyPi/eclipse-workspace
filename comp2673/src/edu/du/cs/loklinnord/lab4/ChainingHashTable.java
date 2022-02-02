package edu.du.cs.loklinnord.lab4;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChainingHashTable<T> implements HashTable<T> {

	ArrayList<LinkedList<T>> outerList;
	int size;
	
	public ChainingHashTable(int size) {
		outerList = new ArrayList<LinkedList<T>>(size);
		this.size = size;
		for (int i = 0; i < size; i ++) {
			outerList.add(new LinkedList<T>());
		}
	}
	
	@Override
	public boolean put(Object element) {
		outerList.get(element.hashCode() % size).add((T) element);
		return true;
	}

	@Override
	public boolean contains(Object element) {
		return outerList.get(element.hashCode() % size).contains(element);
	}

	@Override
	public boolean remove(Object element) {
		if (contains(element)) {
			outerList.get(element.hashCode() % size).remove(element);
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return outerList.toString();
	}

}
