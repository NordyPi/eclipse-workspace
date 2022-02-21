package edu.du.cs.loklinnord.assignment1;

public class DocumentGenerator {
	public static void main(String[] args) {
		// Read in the grammar into data structures
		Grammar g = new Grammar("test.grm.txt");
		// Generate a random sentence from the grammar
		String sentence = g.generateSentence();
		// Display the sentence
		//System.out.println(sentence);
	}
}
