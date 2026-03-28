package hachi;

/**
 * Enumeration of supported command types in the Hachi application.
 *
 * <p>Each value corresponds to a specific user command, such as adding a task,
 * marking it as done, or exiting the application.</p>
 */
public enum CommandCode {
    MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN, DELETE, FIND, MISSING, BYE, DISPLAY, NOTE;
}
