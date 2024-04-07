package anagram_game;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class AnagramGenerator {
    public static final int INTRO = 14;
    public static final int START = 0; 
    public static final int MAX_LENGTH = 9;
    public static final char DELIMITER = '\"';
    // TEST: private static int counter = 0;
    private static boolean found = false;
    private static List<String> results;
    private static int numOfWords = 2;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word without space: ");
        String word = scanner.nextLine();

        /* TODO: if we want to increase the amount of words possible
        * System.out.println("How many words do you want in the anagram? ");
        * numOfWords = Integer.parseInt(scanner.nextLine());
        *
        *
        */

        // Convert the word to a character array
        char[] chars = word.toCharArray();

        // Sort the character array
        Arrays.sort(chars);

        // Generate all possible anagrams
        generateAnagrams(chars, START);

        // Print the anagrams
        if (results == null)
            System.out.println("No anagram available");
        else {
            System.out.println("Found it! Anagrams are:");
            for (String result : results)
                System.out.print(result + " ");
        }
        scanner.close();
    }

    /**
     * Finds a valid anagram from the user's input.
     * 
     * @param chars user input word as a char array
     * @param index counter and tracks current swapped character
     */
    private static void generateAnagrams(char[] chars, int index) throws IOException {
        // TEST: counter++;
        // TEST: System.out.println(counter);
        if (index == chars.length) {
            // TEST: System.out.println(new String(chars));
            List<List<String>> words = separate(new String(chars));
            results = getAnagramFromWeb(words);
            if (results != null)
                found = true;
            return;
        }

        // For each character in the array, swap it with the character at the current index and generate all possible anagrams
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            generateAnagrams(chars, index + 1);
            swap(chars, index, i);
            if (found)
                return;
        }
    }

    /**
     * Splits the user's input into two separate strings.
     * 
     * @param chars user's input
     * @return list containing the two disjoint subwords
     */
    private static List<List<String>> separate(String chars) {
        List<List<String>> biglist = new ArrayList<>();
        for (int i = chars.length() / 2; i < chars.length() - 1; i++)
        {
            String first = chars.substring(0, i);
            String second = chars.substring(i);
            if (first.length() <= MAX_LENGTH && second.length() <= MAX_LENGTH) {
                List<String> list = new ArrayList<>();
                list.add(first);
                list.add(second);
                biglist.add(list);
            }
        }
        return biglist;
    }

    /**
     * Uses Anagramica API to produce an anagram.
     * 
     * @param words two strings representing the original query split into two words
     * @return list of valid anagrams
     * @throws IOException
     */
    private static List<String> getAnagramFromWeb(List<List<String>> words) throws IOException {
        List<String> list = new ArrayList<>();
        final int INTRO = 14;
        // TEST: System.out.println("Content of words: " + words);
        for (List<String> tupleList : words)
        {
//            System.out.println(tupleList);
            for (String word : tupleList)
            {
                // Get apiResult from web;
                URL anagramica = new URL("http://www.anagramica.com/best/:" + word);
                URLConnection yc = anagramica.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                String inputLine;
                List<String> best = new ArrayList<>();
                in.skip(INTRO);
                while (!((inputLine = in.readLine()).contains("]"))) {
                    int start = inputLine.indexOf(DELIMITER) + 1;
                    int end = inputLine.indexOf(DELIMITER, start);
                    best.add(inputLine.substring(start, end));
                }
                in.close();

                // TEST: for (String w : best) System.out.println(w);
                // TODO: can randomize the chosen word
                String apiResult = best.get(0);
                // System.out.println(apiResult);
                if (apiResult.length() != word.length()) {
                    list.clear();
                    continue;
                }
                list.add(apiResult);
                // TEST: System.out.println("List content: " + list);
            }
            if (list.size() == numOfWords)
                return list;
        }
        return null;
    }

    /**
     * Swaps two characters in a string/char[].
     * 
     * @param chars input
     * @param i first character
     * @param j second character
     */
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}