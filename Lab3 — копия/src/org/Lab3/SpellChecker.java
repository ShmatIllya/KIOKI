package org.Lab3;
import java.util.*;

// class that implements spell checking functionality
public class SpellChecker {

     // Set to store the words in the dictionary
    static Set<String> dictionary;

    // Constructor to initialize the dictionary set
    public SpellChecker(Set<String> dictionary) {
        SpellChecker.dictionary = dictionary;
    }

    // ANSI_RESET code resets the text to its default color.
    public static final String ANSI_RESET = "\u001B[0m";
    // ANSI escape codes to set the color of the text being printed to the console
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // Method to check the spelling of the given input
    public  void check(String input) {
        // List to store misspelled words
        List<String> misspelled = new ArrayList<>();

        // Splitting the input string into words
        String[] words = input.split("\\s+");

        // Checking the spelling of each word
        for (String word : words) {
            // If word not found in dictionary, add to misspelled words list
            if (!dictionary.contains(word.toLowerCase())) {
                misspelled.add(word);
            }
        }
        
        System.out.print("The Sentence after checking: ");
        PrintChecked(words,misspelled);

            
        }
    

    public static void PrintChecked(String[] words, List<String> misspelled) {
        // print the sentence with misspelled words in red and correctly spelled words in blue
        for(String word : words) {
            if(misspelled.contains(word)) System.out.print(ANSI_RED+word+" "+ANSI_RESET);
            else System.out.print(ANSI_BLUE+word+" "+ANSI_RESET);
        }
        
        // print the header for the corrections section
        System.out.println();
        if(misspelled.isEmpty()) System.out.println("No mistakes, you're good.");
        else{
            System.out.println(ANSI_YELLOW+"**Possible Corrections for misspelled words**"+ANSI_RESET);

        // iterate over each misspelled word and print the correction in blue
        for (String missword : misspelled) {
            System.out.println(ANSI_RED+missword+ANSI_RESET+" --> "+ANSI_BLUE+suggest(missword)+ANSI_RESET);
        }
        }
    }

    // Method to find the Levenshtein distance between two words
    private static int levenshteinDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // 2D array to store the distance between substrings
        int[][] dp = new int[m + 1][n + 1];
        
        // Filling the 2D array to find the minimum distance
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        // Returning the minimum distance
        return dp[m][n];
    }

    private static String suggest(String word) {
        // initializes a variable "minDistance" to the maximum possible value of an integer
        int minDistance = Integer.MAX_VALUE;
        // initializes a string variable "suggestion" to an empty string.
        String suggestion = "";
        
        // loops through each word in the dictionary ("dictWord")
        for (String dictWord : dictionary) {
            /* calculates the Levenshtein distance between the misspelled word 
            and the current word*/ 
            int distance = levenshteinDistance(word, dictWord);
            
            /* If the calculated distance is less than the current minDistance, 
            the method updates the minDistance with the new distance and updates 
            the suggestion with the current dictWord.*/
            if (distance < minDistance) {
                minDistance = distance;
                suggestion = dictWord;
            }
        }
        // prints the suggestion as a correction for the misspelled word.
        return suggestion;
    }

}