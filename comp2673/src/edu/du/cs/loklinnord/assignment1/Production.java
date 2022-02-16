package edu.du.cs.loklinnord.assignment1;

import java.util.*;

public class Production {

	private Queue<Symbol> queue; 

	Production(String input) {
		StringTokenizer t = new StringTokenizer(input); //parsing the input into individual lines - into one big token array
		String newString = "";
		
		while (t.hasMoreTokens()) {
			String parser = t.nextToken(); 
			if(parser.contains("<")) { //if the next token contains an '<', then add it to the next line, if not, create a new string with the parser attached
				queue.add(new Terminal(newString));
				queue.add(new NonTerminal(parser));
				newString = "";
			}else {
				newString += parser + " ";
			}

		}

	}
	
}
