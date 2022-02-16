package edu.du.cs.loklinnord.assignment1;

import java.util.*;

public class Rule { 
	private List<Production> products;
	private NonTerminal key; 
	public Rule( NonTerminal k, List<Production> p  ) {//parses the new productions in
		this.key = k;
		this.products = new LinkedList<Production>();
		this.products = p;
	
	}
	
	public int hashCode() { //returns a hash code for the table based off of the key value, which in this case is the nonTerminal
		return key.hashCode();
	}
	public boolean equals(Object o){ //equals method cause I felt like it
		Rule k = (Rule) o;
		return key.equals(k.key) && products.equals(k.products);
	}
	
	
}
