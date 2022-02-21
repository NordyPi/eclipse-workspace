package edu.du.cs.loklinnord.assignment1;

public class Terminal implements Symbol {
	String info;

	Terminal(String info) {
		this.info = info;
	}

	public boolean isTerminal() {
		return true;
	}
	
	public String toString() {
		return info;
	}
}
