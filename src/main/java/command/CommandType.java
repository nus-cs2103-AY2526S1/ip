package command;

/**
 * Represents the types of commands supported by the application.
 * Each enum value corresponds to a specific user command.
 */
public enum CommandType {
    /**
     * Command to create a new todo task
     */
    CREATE_TODO,

    /**
     * Command to create a new event task
     */
    CREATE_EVENT,

    /**
     * Command to create a new deadline task
     */
    CREATE_DEADLINE,

    /**
     * Command to list all tasks
     */
    LIST,

    /**
     * Command to mark a task as completed
     */
    MARK,

    /**
     * Command to unmark a completed task
     */
    UNMARK,

    /**
     * Command to exit the application
     */
    BYE,

    /**
     * Represents an invalid or unrecognized command
     */
    INVALID,

    /**
     * Command to delete a task
     */
    DELETE,

    /**
     * Command to find a task with a certain String within its description
     */
    FIND,

    /**
     * Command to tag a task
     */
    TAG,

    /**
     * Command to remove a tag
     */
    DTAG
}