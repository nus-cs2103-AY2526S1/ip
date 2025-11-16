package command;

/**
 * Enum representing the various commands that can be executed in the task management application.
 */
public enum CommandType {
    LIST,
    FIND,
    DELETE,
    MARK,
    UNMARK,
    DEADLINE,
    EVENT,
    TODO,
    BYE,
    EDIT,
    INVALID; // Represents an invalid command

    /**
     * Converts a string input to the corresponding Command enum value.
     * The comparison is case-insensitive.
     *
     * @param input the string input representing a command
     * @return the corresponding Command enum value, or null if the input does not match any command
     */
    public static CommandType fromString(String input) {
        if (input == null) {
            return null;
        }
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID; // Return null for any invalid command string
        }
    }
}
