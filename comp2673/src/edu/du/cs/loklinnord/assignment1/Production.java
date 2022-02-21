package edu.du.cs.loklinnord.assignment1;

import java.util.*;

public class Production {

	private Stack<Symbol> stack;

	Production(String input) {
		//System.out.println(input);
		// Makes a new stack of symbols, splits the string, and adds it to the stack
		stack = new Stack<Symbol>();
		String[] split = input.split("\\s+");
		for (String s: split) {
			if (s.contains("<")) {
				stack.add(new NonTerminal(s));
			} else {
				stack.add(new Terminal(s));
			}
		}
		//System.out.println("production: " + stack.toString());
		//System.out.println();
	}
	
	public String toString() {
		return stack.toString();
	}
	
	public Symbol pop() {
		return stack.pop();
	}

}
