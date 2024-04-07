package anagram_game;

import java.util.*;
import java.net.*;
import java.io.*;

public class AnagramGenerator {

    private static int counter = 0;
    private static boolean found = false;
    private static List<String> results;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word without space: ");
        String word = scanner.nextLine();
//        System.out.println("How many words do you want in the anagram? ");
//        int numOfWords = Integer.parseInt(scanner.nextLine());

        int numOfWords = 2;
        // Convert the word to a character array
        char[] chars = word.toCharArray();

        // Sort the character array
        Arrays.sort(chars);

        // Generate all possible anagrams
        generateAnagrams(chars, 0);

        // Print the anagrams
        if (results == null)
            System.out.println("No anagram available");
        else {
            System.out.println("Found it! Anagrams are:");
            for (String result : results)
                System.out.println(result);
        }
    }

    private static void generateAnagrams(char[] chars, int index) throws IOException {
        // If we have reached the end of the array, we have generated an anagram
        if (found)
            return;

        counter++;
        System.out.println(counter);
        if (index == chars.length) {
//            anagrams.add(new String(chars));
            System.out.println(new String(chars));
            List<String> words = separate(new String(chars));
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

    private static List<String> separate(String chars) {
        List<String> list = new ArrayList<>();
        for (int i = chars.length() / 2; i < chars.length(); i++)
        {
            String first = chars.substring(0, i);
            String second = chars.substring(i);
            if (first.length() <= 10 && second.length() <= 10) {
                list.add(first);
                list.add(second);
                return list;
            }

        }
        return list;
    }

    private static List<String> getAnagramFromWeb(List<String> words) throws IOException {
        List<String> list = new ArrayList<>();
        System.out.println("Content of words: " + words);
        for (String word : words)
        {
            // Get apiResult from web;
            final int INTRO = 14;
            //String input = ;
            //if (input.length() > 10) {throw Exception;}
            URL anagramica = new URL("http://www.anagramica.com/best/:" + word);
            URLConnection yc = anagramica.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            List<String> best = new ArrayList<>();
            in.skip(INTRO);
            while (!((inputLine = in.readLine()).contains("]"))) {
                int start = inputLine.indexOf('\"') + 1;
                int end = inputLine.indexOf('\"', start);
                best.add(inputLine.substring(start, end));
            }
            in.close();

            for (String w : best)
                System.out.println(w);
            String apiResult = best.get(0);
//            System.out.println(apiResult);
            if (apiResult.length() != word.length()) {
                list.clear();
                return null;
            }
            list.add(apiResult);
            System.out.println("List content: " + list);
        }
        return list;
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}