package beebong.exception;

/**
 * Represents a custom unchecked exception used within the BeeBong application.
 */
public class BBongException extends RuntimeException {
    /**
     * Constructs a new {@code BBongException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public BBongException(String message) {
        super(message);
    }
}
