/**
 * Enum representing different types of commands.
 */
public enum CommandType {
    SHOW,
    HELP,
    EXIT,
    ADD,
    DONE,
    COMPLETE,
    DELETE;

    /**
     * Converts a string to the corresponding CommandType.
     *
     * @param command The string representing the command
     * @return The corresponding CommandType
     * @throws IllegalArgumentException if the command is not valid
     */
    public static CommandType fromString(String command) {
        switch (command.toLowerCase()) {
        case "show":
            return SHOW;
        case "help":
            return HELP;
        case "exit":
            return EXIT;
        case "add":
            return ADD;
        case "done":
            return DONE;
        case "complete":
            return COMPLETE;
        case "delete":
            return DELETE;
        default:
            throw new IllegalArgumentException("Unknown command");
        }
    }
}
