package homework6;

import java.util.*;

public class Graph {

    // "2d array" type structure to hold weighted matrix data
    private HashMap<String, HashMap<String, Integer>> dict = new HashMap<String, HashMap<String, Integer>>();

    // hashmap meant simply to hold the dictionary for checking if elements exist (for speed)
    private HashMap<String, Boolean> dictMap = new HashMap<String, Boolean>();

    private int wordLength;

    // Constructs the graph
    public Graph(ArrayList<String> words) {

        // If empty array is passed in
        if (words.size() == 0)
            throw new RuntimeException("There are no words with the size of the words you entered");

        // Sets the length of the words being used
        wordLength = words.get(0).length();

        // Puts words in HashMap to easily and quickly check if they exist
        for (int i = 0; i < words.size(); i++) {
            dictMap.put(words.get(i), true);
        }

        // Loops over each word in the array
        for (int i = 0; i < words.size(); i++) {
            String _word = words.get(i);
            HashMap<String, Integer> _wordMap = new HashMap<String, Integer>();
            dict.put(_word, _wordMap);

            // Loops over each letter of the word
            for (int f = 0; f < wordLength; f++) {
                char _letter = _word.charAt(f);

                // Loops over each letter of the alphabet
                for (int j = 0; j < 26; j++) {
                    char _current = (char) (97 + j);

                    if (_current != _letter) {

                        // Creates new word that could be gone to from the current word and places it and its weight in the weighted matrix
                        String _testWord = _word.substring(0, f) + _current;
                        if (f != wordLength - 1)
                            _testWord += _word.substring(f + 1);
                        if (dictMap.containsKey(_testWord))
                            _wordMap.put(_testWord, Math.abs(AsciiSum(_word) - AsciiSum(_testWord)));
                    }
                }
            }
        }
    }

    // Returns the weight between two nodes
    public int GetWeight(String first, String second) {
        String _first = first.toLowerCase();
        String _second = second.toLowerCase();
        if (dict.containsKey(_first) && dict.get(_first).containsKey(_second)) {
            return dict.get(_first).get(_second);
        } else {
            return -1;
        }
    }

    // Returns the sum of the ascii values of all characters in a string
    private static int AsciiSum(String word) {
        int _sum = 0;
        for (int i = 0; i < word.length(); i++) {
            _sum += word.charAt(i);
        }
        return _sum;
    }

    public void Dijkstra (String start, String end) {
        // Initialize the distance and visited arrays
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String vertex : dict.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
            visited.put(vertex, false);
        }
        distance.put(start, 0);

        // Initialize the priority queue and add the starting vertex
        //Comparator uses distance hashMap to get the value and helps determine the priority queue
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        pq.offer(start);

        // Loop through the priority queue until it is empty
        while (!pq.isEmpty()) {
            String current = pq.poll();

            // If we have reached the end vertex, print out the path and terminate
            if (current.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = previous.get(current);
                }
                Collections.reverse(path);
                int finalDistance = 0;
                for (int i = 0; i < path.size() - 1; i++) {
                    String word = path.get(i);
                    String nextWord = path.get(i + 1);
                    int dist = dict.get(word).get(nextWord);
                    finalDistance += dist;
                    System.out.printf("%s -> %s  (Distance: %d), ", word, nextWord, dist);
                }
                System.out.println(end);
                System.out.println("Final Total Distance: " + finalDistance);
                return; //breaks out
            }

            // Mark the current vertex as visited
            visited.put(current, true);

            // Update the distances of all adjacent vertices
            for (String neighbor : dict.get(current).keySet()) {
                if (!visited.get(neighbor)) {
                    int newDistance = distance.get(current) + dict.get(current).get(neighbor);
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        previous.put(neighbor, current);
                        pq.offer(neighbor);
                    }
                }
            }
        }

        // If we haven't found the end vertex, there is no path
        System.out.println("no path found");

    }

}