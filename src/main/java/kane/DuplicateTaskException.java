package kane;

/**
 * Represents an exception thrown when attempting to add a duplicate task.
 */
public class DuplicateTaskException extends KaneException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}