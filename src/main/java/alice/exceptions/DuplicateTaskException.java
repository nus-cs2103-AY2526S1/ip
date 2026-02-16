package alice.exceptions;

/**
 * Thrown when attempting to add a task that already exists in the task list.
 */
public class DuplicateTaskException extends AliceException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
