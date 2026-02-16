package guibot.exception;

/**
 * Exception when user tries to access a task using an index not in the list.
 */
public class TaskNotFoundException extends GuibotException {
    /**
     * Creates a TaskNotFoundException.
     */
    public TaskNotFoundException() {
        super("The index specified does not correspond to a task in the list. Please try again.");
    }
}
