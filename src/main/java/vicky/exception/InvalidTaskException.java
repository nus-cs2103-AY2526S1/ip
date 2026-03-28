package vicky.exception;

/**
 * Represents an exception for invalid task input.
 */
public class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
