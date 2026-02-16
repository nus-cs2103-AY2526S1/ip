package util;

/**
 * Utility class for parsing user input.
 * Provides methods to split and process command strings.
 */
public class Parser {
    /**
     * Parses a string input into an array of tokens.
     * @param input The input string to parse
     * @return Array of tokens split by whitespace
     */
    public static String[] parse(String input) {
        return input.trim().split("\\s+");
    }

}
