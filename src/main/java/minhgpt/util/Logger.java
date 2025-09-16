package minhgpt.util;

/**
 * Provides logging capability for the program.
 */
public class Logger {
    /** String to reset terminal text style */
    private static final String RESET = "\033[0m";
    /** String to set terminal text colour to black */
    private static final String TEXT_COLOR = "\033[0;30m";

    /**
     * Prints a log message at level 'Info'.
     */
    public static void logInfo(String message) {
        // White background
        String infoBg = "\033[47m";
        System.out.println(String.format("%s%s INFO %s %s", TEXT_COLOR, infoBg, RESET, message));
    }

    /**
     * Prints a log message at level 'Warning'.
     */
    public static void logWarning(String message) {
        // Yellow background
        String warningBg = "\033[43m";
        System.out.println(
                String.format("%s%s WARNING %s %s", TEXT_COLOR, warningBg, RESET, message));
    }

    /**
     * Prints a log message at level 'Error'.
     */
    public static void logError(String message) {
        // Red background
        String errorBg = "\033[41m";
        System.out.println(String.format("%s%s ERROR %s %s", TEXT_COLOR, errorBg, RESET, message));
    }
}
