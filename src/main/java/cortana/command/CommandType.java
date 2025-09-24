package cortana.command;

/**
 * Represents the types of commands that can be processed by cortana.core.Cortana.
 *
 * <p>Includes task-related commands, task state changes, list commands, exit command, and a special
 * unknown type for unrecognized commands.
 */
public enum CommandType {
    /**
     * cortana.command.Command for creating a 'To Do' task.
     */
    TODO,

    /**
     * cortana.command.Command for creating a 'cortana.task.Deadline' task.
     */
    DEADLINE,

    /**
     * cortana.command.Command for creating an 'cortana.task.Event' task.
     */
    EVENT,

    /**
     * cortana.command.Command to mark a task as done.
     */
    MARK,

    /**
     * cortana.command.Command to unmark a task (mark as not done).
     */
    UNMARK,

    /**
     * cortana.command.Command to delete a task.
     */
    DELETE,

    /**
     * cortana.command.Command to list all tasks.
     */
    LIST,
    /**
     * cortana.command.Command to display help information.
     */
    HELP,
    /**
     * cortana.command.Command to find tasks by keywords.
     */
    FIND,

    /**
     * cortana.command.Command to exit the chatbot application.
     */
    BYE,

    /**
     * Represents an unknown or unrecognized command.
     */
    UNKNOWN;

    /**
     * Converts a string command to corresponding cortana.command.CommandType enum value. Returns
     * UNKNOWN if no matching command is found.
     *
     * @param command String command (case-insensitive)
     * @return cortana.command.CommandType enum value
     */
    public static CommandType fromString(String command) {
        if (command == null) {
            return UNKNOWN;
        }
        try {
            return CommandType.valueOf(command.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
