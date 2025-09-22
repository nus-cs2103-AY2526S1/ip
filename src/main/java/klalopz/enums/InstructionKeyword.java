package klalopz.enums;

/**
 * Enum representing all valid instruction keywords.
 */
public enum InstructionKeyword {
    LIST,
    MARK,
    UNMARK,
    FIND,
    DEADLINE,
    EVENT,
    TODO,
    DELETE,
    ADD_TAG,
    DELETE_TAG,
    HELP,
    EXIT;

    /**
     * Convert a string to an InstructionKeyword.
     * Supports aliases like "remove" -> DELETE, "bye" -> EXIT.
     *
     * @param input the raw keyword string
     * @return the matching InstructionKeyword
     * @throws IllegalArgumentException if no match is found
     */
    public static InstructionKeyword fromString(String input) {
        return switch (input.toLowerCase().trim()) {
            case "list" -> LIST;
            case "mark" -> MARK;
            case "unmark" -> UNMARK;
            case "find" -> FIND;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            case "todo" -> TODO;
            case "delete", "remove" -> DELETE;
            case "add_tag" -> ADD_TAG;
            case "del_tag", "delete_tag", "remove_tag" -> DELETE_TAG;
            case "help" -> HELP;
            case "bye" -> EXIT;
            default -> throw new IllegalArgumentException("Invalid instruction: " + input);
        };
    }
}
