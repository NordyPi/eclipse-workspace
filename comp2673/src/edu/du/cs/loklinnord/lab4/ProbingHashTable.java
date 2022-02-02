package edu.du.cs.loklinnord.lab4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProbingHashTable<T> implements HashTable {
	
	private ArrayList<T> list;
	private enum State {EMPTY, FULL, DELETED}
	private ArrayList<State> states;
	private int size;
	
	public ProbingHashTable(int size) {
		list = new ArrayList<T>(size);
		this.size = size;
		states = new ArrayList<State>(size);
		for (int i = 0; i < size; i ++) {
			states.add(State.EMPTY);
		}
		for (int i = 0; i < size; i ++) {
			list.add(null);
		}
	}
	//private class to store in our arraylist, cuz we need element and state
	/*
	private class TableData {
		private Foo element;
		private State state;
		
		private TableData() {
			element = new Foo();
			state = state.EMPTY;
		}
		
		public boolean equals(Object o) {
			TableData data = (TableData) o;
			if (this.element.equals(data.element)) {
				return true;
			} else {
				return false;
			}
		}
		
		public String toString() {
			String out = "";
			return out + "";
		}
	}
	*/
	
	@Override
	public boolean put(Object element) {
		int hash = element.hashCode();
		int i = hash % size;
		int full = 0;
		while (states.get(i) == State.FULL) {
			// keeping the mod increases index but allows looping
			i = (i + 1) % size;
			full ++;
			if (full == size) {
				return false;
			}
		}
		list.set(i, (T) element);
		states.set(i, State.FULL);
		return true;
	}


	@Override
	public boolean contains(Object element) {
		return list.contains(element);
	}

	@Override
	public boolean remove(Object element) {
		int hash = element.hashCode();
		int i = hash % size;
		while (states.get(i) != State.EMPTY) {
			i = (i + 1) % size;
			if (list.get(i).equals(element)) {
				states.set(i, State.DELETED);
				list.set(i, null);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return list.toString();
	}
	
}
