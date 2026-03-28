package karl.exception;

/**
 * Exception class used to signal errors specific to the Karl chatbot application.
 * Used for user input errors, loading/saving failures, and command processing issues.
 */
public class KarlException extends Exception {
    /**
     * Constructs a KarlException with a specified error message.
     *
     * @param message the detail message explaining the exception
     */
    public KarlException(String message) {
        super(message);
    }
}
