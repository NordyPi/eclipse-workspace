package homework6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	while(true) {
    		String start = "";
    		String end = "";
    		
    		Scanner keyboard = new Scanner(System.in);
    		System.out.println("Please enter the starting word:");
    		start = keyboard.nextLine();
    		System.out.println("Please enter the ending word. Remember, words must be of the same length:");
    		end = keyboard.nextLine();
    		ShortestPath(start, end);
    		System.out.println();
    	}
    }

    public static void ShortestPath(String startWord, String endWord) {
        //Makes the words lowercase because our dictionary is all lower case
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();

        // Throws exception if words are different lengths
        if (startWord.length() != endWord.length()) {
            throw new RuntimeException("The given and target words must be the same length");
        }

        // Creates an array list holding all words in the dictionary
        ArrayList<String> _wordList = new ArrayList<String>();

        // Reads words of length equal to that of the words were searching from the dictionary into the array list
        File _dictTxt = new File("Dict.txt");
        try {

            Scanner _reader = new Scanner(_dictTxt);
            while (_reader.hasNextLine()) {
                String _word = _reader.nextLine();
                if (_word.length() == startWord.length()) {
                    _wordList.add(_word.toLowerCase());
                }
            }
            _reader.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Creates graph
        Graph _graph = new Graph(_wordList);

        _graph.Dijkstra(startWord, endWord);

    }

}