package util;

/**
 * Enum representing all valid commands supported by the Shrek application.
 */
public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE,
    ONDATE,
    FIND,
    SORT,
    HELP;

    /**
     * Converts a string input to the corresponding Command enum value.
     *
     * @param input the string input to convert
     * @return the corresponding Command enum value
     * @throws ShrekException if the input does not match any valid command
     */
    public static Command fromString(String input) throws ShrekException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ShrekException("I don't speak your language. I don't understand: " + input);
        }
    }
}
