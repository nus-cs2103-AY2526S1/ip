package com.oadultradeepfield.smartotter.util;

import com.oadultradeepfield.smartotter.SmartOtterConstant;

/**
 * Utility class for fuzzy string matching using the Levenshtein distance algorithm.
 * Provides methods to calculate edit distance between strings and perform approximate
 * string matching with configurable tolerance.
 */
public class FuzzyMatcher {
    // Prevents instantiation
    private FuzzyMatcher() {
    }

    /**
     * Performs fuzzy matching to determine if a keyword approximately matches
     * any word in the given text within a specified edit distance tolerance.
     * Both text and keyword are converted to lowercase for case-insensitive matching.
     *
     * @param text the text to search within, will be split into individual words
     * @param keyword the keyword to search for
     * @return true if any word in the text matches the keyword within the distance threshold
     */
    public static boolean isMatched(String text, String keyword) {
        text = text.toLowerCase();
        keyword = keyword.toLowerCase();

        // Split text into words and check each against the keyword
        for (String word : text.split("\\s+")) {
            if (calculateLevenshteinDistance(word, keyword) <= SmartOtterConstant.DEFAULT_FUZZY_MATCH_DISTANCE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the Levenshtein distance between two strings.
     * The Levenshtein distance is the minimum number of single-character edits
     * (insertions, deletions, or substitutions) required to transform one string
     * into another.
     *
     * @param s1 the first string to compare
     * @param s2 the second string to compare
     * @return the minimum edit distance between the two strings
     */
    private static int calculateLevenshteinDistance(String s1, String s2) {
        // DP table where dp[i][j] represents distance between s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // Initialize base cases: empty string to non-empty string
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        // Fill DP table using recurrence relation
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1], // substitution
                        Math.min(dp[i - 1][j], dp[i][j - 1]) // deletion or insertion
                    );
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
