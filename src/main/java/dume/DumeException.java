package dume;

/**
 * Exception thrown when the application encounters an error during operation.
 */
public class DumeException extends RuntimeException {
    /**
     * Constructs a DumeException with the specified detail message.
     *
     * @param message the detail message
     */
    public DumeException(String message) {
        super(message);
    }
}
