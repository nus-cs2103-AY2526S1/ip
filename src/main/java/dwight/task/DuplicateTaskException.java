package dwight.task;

/**
 * Exception thrown when attempting to add a duplicate task to a collection that does not allow
 * duplicates.
 */
public class DuplicateTaskException extends Exception {
    /**
     * Creates a new {@code DuplicateTaskException} with a message indicating the duplicate task.
     *
     * @param message The task identifier or description that is duplicated.
     */
    public DuplicateTaskException(String message) {
        super("Duplicate task '" + message + "'");
    }
}
