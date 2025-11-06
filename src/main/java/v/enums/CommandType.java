package v.enums;

/**
 * Represents the types of commands that can be executed by V.
 * Each command type is associated with its string representation.
 */
public enum CommandType {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye");

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the command string associated with this command type.
     *
     * @return The command string.
     */
    public String getCommandString() {
        return commandString;
    }

    /**
     * Converts a string to the corresponding CommandType enum value.
     *
     * @param text The string to convert.
     * @return The corresponding CommandType, or null if no match is found.
     */
    public static CommandType fromString(String text) {
        if (text == null) {
            return null;
        }
        for (CommandType type : CommandType.values()) {
            if (type.commandString.equalsIgnoreCase(text.trim())) {
                return type;
            }
        }
        return null;
    }
}
