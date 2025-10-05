package sage.exception;

/**
 * Represents an exception specific to the Sage chatbot application.
 * This exception is thrown when an error occurs during the execution of Sage commands.
 */
public class SageException extends Exception {
    /**
     * Constructs a SageException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public SageException(String message) {
        super(message);
    }
}
