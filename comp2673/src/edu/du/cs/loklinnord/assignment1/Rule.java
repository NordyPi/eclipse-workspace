package edu.du.cs.loklinnord.assignment1;

import java.util.*;

public class Rule {
	private List<Production> products;
	private NonTerminal key;
	// makes a new rule with key NonTerminal and a list of productions
	public Rule(NonTerminal k, List<Production> p) {
		this.key = k;
		this.products = new LinkedList<Production>();
		this.products = p;

	}
	
	public String toString() {
		return products.toString();
	}
	
	// this will return a randomProduciton from the list based on its size
	public Production returnRandomProduction() {
		Random rand = new Random();
		return products.get(rand.nextInt(products.size()));
	}

}
