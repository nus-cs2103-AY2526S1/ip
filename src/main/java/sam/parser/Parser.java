package sam.parser;

/**
 * Handles the parsing of user input commands.
 * This class provides functionality to split user input into command verbs and
 * arguments,
 * making it easier for the application to process different types of commands.
 */
public class Parser {
    /**
     * Parses user input by splitting it into a command verb and arguments.
     * The input is split at the first whitespace, with the first part becoming the
     * verb
     * and the remaining part becoming the arguments.
     *
     * @param input The raw user input string to be parsed
     * @return A String array where index 0 contains the command verb and index 1
     *         contains the arguments
     */
    public static String[] parse(final String input) {
        String[] parts = input.trim().split("\\s+", 2);
        String verb = parts[0].toLowerCase();
        String rest = (parts.length > 1) ? parts[1].trim() : "";
        return new String[] { verb, rest };
    }
}
