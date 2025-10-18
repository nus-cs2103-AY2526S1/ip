package uy;

/**
 * Exception thrown when a task line or type cannot be parsed from storage.
 */
public class UnknownTaskError extends Exception {
    public UnknownTaskError(String message) {
        super(message);
    }
}
