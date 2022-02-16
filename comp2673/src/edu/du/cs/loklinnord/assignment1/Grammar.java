package edu.du.cs.loklinnord.assignment1;

import java.io.*;
import java.util.*;


public class Grammar {
	private File file;
	private Scanner fileScanner;
	private HashMap<NonTerminal, Rule> document; 
	
	Grammar(String fileName){//Constructor - creates the data

		 file = new File(fileName);
		 try { //Takes the file in and checks if the file is valid, if not, throws error and exits
			 fileScanner = new Scanner(file);
		 }catch(FileNotFoundException e) {
			 System.out.println(e.getMessage());
			 System.exit(0);
		 }
		 
		 
		 
		 
		 

		 while(fileScanner.hasNextLine()) { // Checks if it's the end of the file, if not, breaks the loop!
			 String temp = fileScanner.nextLine();			//Create temporary variables to store each of the values 
				List<Production> prodTemp = new LinkedList<Production>();
				NonTerminal nonTemp = null;

			while(temp != "\n") {//If it finds a blank like, quit out of the loop
				if(temp.charAt(0) == '<') {//If the line starts with "<", then add it to "nonTemp"
					 nonTemp = new NonTerminal(temp);
				} else {
					prodTemp.add(new Production(temp)); //If it doesn't contain the thingy, add it to temp
				}
				temp = fileScanner.nextLine();
			}
			document.put(nonTemp, new Rule(nonTemp, prodTemp ));
		 }
		 
		 
		
	}
	
	public String generateSentance() {
		String temp = "";
		Rule print = document.get(new NonTerminal("<start>"));
		
		
		return temp;
	}
}
