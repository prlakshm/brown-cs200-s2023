package sol;

import org.junit.Assert;
import src.IGrep;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

/**
 * Class for finding the line numbers where a given word appears in a file.
 */
public class Grep implements IGrep {

    private HashMap<String, Set<Integer>> mapWords;

    /**
     * Constructor for grep
     * @param filename the name of the file
     */
    public Grep(String filename) {
        this.mapWords = new HashMap<>();

        //read contents of file as string
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            String lines = line;
            while (line != null) {
                // get the next line from the file
                line = reader.readLine();
                // append to string with all string contents
                lines = lines + " \n " + line;
            }
            reader.close();

            //create hashmap if lines of file not empty
            if(lines != null){
            String[] words = lines.split(" ");
            List<String> wordsParts = Arrays.asList(words);
            HashSet<String> wordsSet = new HashSet<String>(wordsParts );

            String[] lineList = lines.split("\n");

            for(String word : wordsSet) {
                Set<Integer> ints = new HashSet<>();
                int count = 1;
                for (String l : lineList) {
                    String[] splitLine = l.split(" ");
                    for (String wordInLine : splitLine) {
                        if (word.equals(wordInLine)) {
                            ints.add(count);
                        }
                    }
                    count++;
                }
                this.mapWords.put(word, ints);
            }
            }
        } catch (IOException e) {
            System.out.println("Encountered an error: " + e.getMessage());
        }
    }

    /**
     * The lookup function
     * @param word - the word to look up
     *
     * @return The set of all line numbers where the word appears
     */
    @Override
    public Set<Integer> lookup(String word) {
        Set<Integer> lineNums = this.mapWords.get(word);
        if(lineNums == null){
            return Collections.emptySet();
        }
        else {
            return lineNums;
        }
    }

    // Feel free to write additional helper methods :)

    /**
     * This is the main method. It takes in arguments (i.e. a file name and a word(s))
     * and calls your implementation of Grep.
     * @param args - file name and word(s) you are looking up
     */
    public static void main(String[] args) {
        Grep story = new Grep("grep-test-files/story.txt");
        System.out.println("Cory was found on lines: " + story.lookup("Cory"));
        System.out.println("airport was found on lines: " + story.lookup("airport"));
        System.out.println("airport. was found on lines: " + story.lookup("airport."));
        System.out.println("met was found on lines: " + story.lookup("met"));

        Grep empty = new Grep("grep-test-files/empty.txt");
        System.out.println("stone was found on lines: " + empty.lookup("stone"));
        System.out.println("met was found on lines: " + empty.lookup("met"));
        System.out.println("' ' was found on lines: " + empty.lookup(" "));

        Grep ozy = new Grep("grep-test-files/ozymandias.txt");
        System.out.println("that was found on lines: " + ozy.lookup("that"));
        System.out.println("stone was found on lines: " + ozy.lookup("stone"));
        System.out.println("stone was found on lines: " + ozy.lookup("cat"));

        Grep error1 = new Grep("story.txt");
        Grep error2 = new Grep("grep-test-files/moon.txt");
    }

}
