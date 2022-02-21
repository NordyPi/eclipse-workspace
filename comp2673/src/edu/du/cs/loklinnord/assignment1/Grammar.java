package edu.du.cs.loklinnord.assignment1;

import java.io.*;
import java.util.*;

public class Grammar {
	// Instance variables
	private HashMap<NonTerminal, Rule> hashmap;
	private Scanner scanner;
	private File file;
	private NonTerminal start;

	Grammar(String fileName) {
		hashmap = new HashMap<NonTerminal, Rule>();
		// sets up our file reference. if the file doesnt exist, throw and error
		file = new File(fileName);
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		// if there is no next line, the reader has made it to the end of the file and should stop
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			//System.out.println("non-terminal: " + line);
			if(line.charAt(0) == '<'){
				NonTerminal nonTemp = new NonTerminal(line);
				List<Production> prodTemp = new ArrayList<Production>();
				if(line.equals("<start>")) {
					start = nonTemp;
					System.out.println(start);
				}
				line = scanner.nextLine();
				while(line != "" && scanner.hasNextLine()) {
					//System.out.println("production: " + line);
					prodTemp.add(new Production(line));
					line = scanner.nextLine();
				}
				hashmap.put(nonTemp, new Rule(nonTemp, prodTemp));				

			}
				
		}
		System.out.println(hashmap.toString());
		//System.out.println(hashmap.get());

	}

	public String generateSentence() {
		//starts scan production recursive loop with starting NonTerminal <start>
		Production startingProduction = hashmap.get(start).returnRandomProduction();
		return scanProduction(startingProduction);
	}
	public String scanProduction(Production p) {
		System.out.println("production: " + p);
		String output = "";
		// get the last symbol in the production
		Symbol s = p.pop();
		System.out.println("symbol: " + s);
		// loops through all symbols in the production
		while (s != null) {
			if (s.isTerminal()) {
				// if terminal add the text to the output
				output += s.toString();
				System.out.println(output);
			} else {
				// if nonterminal, find the next rule and recursively call itself
				NonTerminal sNT = new NonTerminal(s);
				output += scanProduction(hashmap.get(sNT).returnRandomProduction());
			}
		}
		return output;
	}
}