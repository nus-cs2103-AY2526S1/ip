package hermione.utils;

import hermione.exceptions.NumberUtilsException;

/**
 * Utility class for number-related operations.
 */
public class NumberUtils {


    /**
     * Parses a string to a positive integer.
     *
     * @param str The string to parse.
     * @return The parsed positive integer.
     * @throws NumberFormatException If the string is not a valid positive integer.
     */
    public static int parsePositiveInt(String str) {
        try {
            int value = Integer.parseInt(str.trim());
            if (value <= 0) {
                throw new NumberUtilsException("Value must be a positive integer.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new NumberUtilsException("Invalid string for positive integer: " + str);
        }
    }
}
