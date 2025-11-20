package jobe.stringutils;

import java.util.Arrays;

/**
 * Utility class for string manipulation functions.
 */
public class StringUtils {
    
    /**
     * Splits input string, and returns string with the first word removed.
     *
     * @param input input String.
     * @return String with the first word removed.
     */
    public static String splitStringAndRemoveFirstWord(String input) {
        String[] deadlineSplit = input.split(" ");
        String[] deadlineSliced = Arrays.copyOfRange(deadlineSplit, 1, deadlineSplit.length);
        return String.join(" ", deadlineSliced).trim();
    }
    
}
