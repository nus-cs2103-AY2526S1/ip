package minhgpt.util;

/**
 * Provides logging capability for the program.
 */
public class Logger {
    private static final String RESET = "\033[0m";
    private static final String TEXT_COLOR = "\033[0;30m";

    public static void info(String message) {
        // White background
        String infoBg = "\033[47m";
        System.out.println(String.format("%s%s INFO %s %s", TEXT_COLOR, infoBg, RESET, message));
    }

    public static void warning(String message) {
        // Yellow background
        String warningBg = "\033[43m";
        System.out.println(
                String.format("%s%s WARNING %s %s", TEXT_COLOR, warningBg, RESET, message));
    }

    public static void error(String message) {
        // Red background
        String errorBg = "\033[41m";
        System.out.println(String.format("%s%s ERROR %s %s", TEXT_COLOR, errorBg, RESET, message));
    }
}
