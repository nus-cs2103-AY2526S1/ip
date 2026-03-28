/**
 * Represents the different commands that can be executed by the Sam
 * application.
 * This enum defines all the valid command types and provides a method to parse
 * string input into the corresponding command.
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, PRIORITY, UNKNOWN;

    /**
     * Converts a string verb to the corresponding Command enum value.
     * The comparison is case-insensitive.
     *
     * @param verb The string representation of the command
     * @return The corresponding Command enum value, or UNKNOWN if no match is found
     */
    public static Command of(final String verb) {
        switch (verb) {
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "find":
                return FIND;
            case "priority":
                return PRIORITY;
            default:
                return UNKNOWN;
        }
    }
}
