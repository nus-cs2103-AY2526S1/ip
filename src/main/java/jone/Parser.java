package jone;

/**
 * Splits raw user input into command word and arguments.
 */
public class Parser {
    /**
     * Splits the raw user input into the command word and arguments.
     *
     * @param fullCommand The raw input string from the user.
     * @return A String array: [0] = command word, [1] = arguments (might be empty).
     */
    public static String[] parse(String fullCommand) {
        String[] commandParts = fullCommand.trim().split(" ", 2);
        String commandWord = commandParts[0].toLowerCase();
        String arguments = (commandParts.length > 1) ? commandParts[1].trim() : "";
        return new String[]{commandWord, arguments};
    }
}

