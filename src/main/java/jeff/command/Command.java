package jeff.command;

/**
 * Enumeration of all available commands in the Jeff chatbot system.
 */
public enum Command {
    LIST, BYE, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, FIXEDDURATION;

    /**
     * Convert string to Command enum value.
     *
     * @param command the string representation of the command
     * @return the corresponding Command enum value
     */
    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
