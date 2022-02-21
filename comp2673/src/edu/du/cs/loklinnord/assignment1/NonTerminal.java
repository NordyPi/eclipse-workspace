package edu.du.cs.loklinnord.assignment1;

public class NonTerminal implements Symbol {
	String info;

	public NonTerminal(String info) {
		this.info = info;
	}
	// copy constructor when converting unknown symbol to NonTerminal
	public NonTerminal(Symbol s) {
		this.info = s.toString();
		System.out.println(this.info);
	}

	public boolean isTerminal() {
		return false;
	}
	
	public int hashCode() {
		return info.hashCode();
	}
	
	public String toString() {
		return info;
	}

}
