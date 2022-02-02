package edu.du.cs.loklinnord.lab4;

public class Foo implements Comparable<Foo> {
	// instance variables
	private int num;
	// default constructor
	public Foo(int num) {
		this.num = num;
	}
	// copy constructor
	public Foo(Foo o) {
		this.num = o.num;
	}
	// toString, wasnt sure best way to do for int
	public String toString() {
		String out = "";
		return num + out;
	}
	// equals method. just compares int
	public boolean equals(Object o) {
		Foo foo = (Foo) o;
		if (foo == null) {
			return false;
		}
		if (this.num == foo.num) {
			return true;
		} else {
			return false;
		}
	}
	// returns -1 if less than compared, 1 if more, 0 if equal
	public int compareTo(Foo o) {
		int result = 0;
		if (this.num < o.num) {
			result = -1;
		} else if (this.num > o.num) {
			result = 1;
		}
		return result;
	}
	// hash code is just its num
	public int hashCode() {
		return this.num;
	}
}
