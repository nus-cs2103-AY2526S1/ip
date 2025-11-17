package beebong.exception;

/**
 * Represents an Unknown Command exception used within the BeeBong application.
 */
public class UnknownCommandException extends BBongException {
    /**
     * Constructs a new {@code UnknownCommandException} with a pre-defined message.
     */
    public UnknownCommandException() {
        super("Unknown Command! B. Bong doesn't know what to do...");
    }

    /**
     * Constructs a new {@code UnknownCommandException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
