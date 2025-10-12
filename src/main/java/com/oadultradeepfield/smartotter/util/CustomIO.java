package com.oadultradeepfield.smartotter.util;

import com.oadultradeepfield.smartotter.SmartOtterConstant;

/**
 * Utility class for input/output operations in SmartOtter.
 */
public class CustomIO {
    /**
     * Normalizes whitespace by removing leading/trailing spaces and collapsing multiple spaces into
     * single spaces.
     *
     * @param input the string to sanitize
     * @return sanitized string, or empty string if input is null/empty
     */
    public static String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.replaceAll("\\s+", " ").trim();
    }

    /**
     * Prints text with horizontal bars and indentation for bot responses.
     *
     * @param input the message to display
     */
    public static void printPretty(String input) {
        String formattedInput =
            "%s\n%s\n%s"
                .formatted(SmartOtterConstant.HORIZONTAL_BAR, input, SmartOtterConstant.HORIZONTAL_BAR);
        System.out.println(formattedInput.indent(4));
    }

    /**
     * Gets error messages with emoji prefix.
     *
     * @param input the error message to display
     */
    public static String formatError(String input) {
        return "😵‍💫 Oops! - %s".formatted(input);
    }
}
