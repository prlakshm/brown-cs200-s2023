package sol;

import org.junit.Assert;
import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotEquals;


/**
 * Test suite for hash tables
 */
public class Homework3TestSuite {

    /**
     *This example is here to show you how to input the filenames for your Grep
     * tests
     */
    public static void testGrepExample() {
        Grep grep = new Grep("grep-test-files/ozymandias.txt");
        Set<Integer> result = grep.lookup("traveller");
    }

    /**
     * This test is here to make sure the stencil is set up correctly
     * Feel free to delete it!
     */
    @Test
    public void testExample() {
        Assert.assertEquals(2, 1 + 1);
    }

    /**
     * tests lookup with story.txt
     */
    @Test
    public void testGrepStory(){
        Grep story = new Grep("grep-test-files/story.txt");

        Set<Integer> coryLines = new HashSet<>();
        coryLines.add(1);
        Assert.assertEquals(coryLines, story.lookup("Cory"));

        Set<Integer> airportLines = new HashSet<>();
        airportLines.add(1);
        airportLines.add(2);
        Assert.assertEquals(airportLines, story.lookup("airport.")); //not same as just "airport"

        Set<Integer> metLines = new HashSet<>();
        metLines.add(1);
        metLines.add(2);
        Assert.assertEquals(metLines, story.lookup("met"));

        Assert.assertEquals(Collections.emptySet(), story.lookup(""));
    }

    /**
     * tests edge case empty file with empty.txt
     */
    @Test
    public void testGrepEmpty(){
        Grep empty = new Grep("grep-test-files/empty.txt");
        Assert.assertEquals(Collections.emptySet(), empty.lookup("met"));
        Assert.assertEquals(Collections.emptySet(),empty.lookup("stone"));
        Assert.assertEquals(Collections.emptySet(),empty.lookup(" "));

        Assert.assertEquals(Collections.emptySet(),empty.lookup(""));

    }

    /**
     * tests lookup with ozymandias.txt
     */
    @Test
    public void testGrepOzymandias(){
        Grep empty = new Grep("grep-test-files/ozymandias.txt");

        Set<Integer> thatLines = new HashSet<>();
        thatLines.add(6);
        thatLines.add(8);
        thatLines.add(13);
        Assert.assertEquals(thatLines, empty.lookup("that"));

        Set<Integer> stoneLines = new HashSet<>();
        stoneLines.add(2);
        Assert.assertEquals(stoneLines, empty.lookup("stone"));

        Assert.assertEquals(Collections.emptySet(), empty.lookup("cat"));
    }

//    @Test
//    public void testChain() throws KeyAlreadyExistsException{
//        Chaining myChain = new Chaining(7);
//        myChain.insert("key", 5);
//    }

//    /**
//     * Tests for key not found exception for calling lookup on empty list
//     */
    @Test(expected = KeyNotFoundException.class)
    public void testKeyNotFoundException() throws  KeyNotFoundException{
        Chaining myChain = new Chaining(7);
        myChain.lookup("key");
    }
    /**
     *
     */
    @Test(expected = KeyNotFoundException.class)
    public void testKeyNotFoundException2() throws KeyNotFoundException{
        Chaining myChain = new Chaining(7);
        myChain.update("key", 10);
    }

}
