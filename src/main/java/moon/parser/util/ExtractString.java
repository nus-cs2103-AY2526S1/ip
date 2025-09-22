package moon.parser.util;

import java.util.Arrays;

/**
 * Utility class for string manipulation.
 * <p>
 * Provides a method to extract all tokens from an input string except for a specified one.
 */
public class ExtractString {

    /**
     * Returns a new string consisting of all words from {@code input} except those
     * equal to {@code exclude}.
     * <p>
     * Words are split on whitespace. The result is trimmed of leading and trailing spaces.
     *
     * <pre>
     * Example:
     *   input   = "delete   2  "
     *   exclude = "delete"
     *   result  = "2"
     * </pre>
     *
     * @param input   the original string
     * @param exclude the word to filter out
     * @return a new string with {@code exclude} removed
     */
    public static String extract(String input, String exclude) {
        return Arrays.stream(input.split("\\s+"))
                .filter(s -> !s.equals(exclude))
                .reduce("", (s1, s2) -> s1 + " " + s2)
                .trim();
    }
}
