package nerpbot;

/**
 * Utility class for parsing user input into command words and arguments.
 */
public class Parser {
    /**
     * Extracts the command word from the user's input.
     *
     * @param input The user's input string.
     * @return The command word.
     */
    public static String getCommandWord(String input) {
        assert input != null : "Input should not be null";
        return input.split(" ")[0];
    }

    /**
     * Extracts the command arguments from the user's input.
     *
     * @param input The user's input string.
     * @return The command arguments, or an empty string if none.
     */
    public static String getCommandArgs(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }
}
