package edu.du.cs.loklinnord.assignment1;

public class NonTerminal implements Symbol {
	String info;
	
	NonTerminal(String info){//non terminal + hashcode
		this.info = info;
	}
	
	public boolean isTerminal() {
		return false;
	}
	public int hashCode(){
		return info.hashCode();
	}



}
