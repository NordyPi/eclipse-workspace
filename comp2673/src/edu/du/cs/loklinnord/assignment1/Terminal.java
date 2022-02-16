package edu.du.cs.loklinnord.assignment1;

public class Terminal implements Symbol{
	String info;
	Terminal(String info){ //creating a basic constructior
		this.info = info;
	}
	public boolean isTerminal() { //if its a terminal, return true
		return true;
	}
}
