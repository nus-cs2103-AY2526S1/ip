package watermelon.exception;

/**
 * An exception that is thrown when a required date/time field is missing or empty.
 */
public class EmptyDateException extends WatermelonException {
    /**
     * Constructs a new EmptyDateException with the specified detail message.
     *
     * @param message The detail message explaining missing date/time.
     *                The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public EmptyDateException(String message) {
        super(message);
    }

    /**
     * Constructs a new EmptyDateException with a default message.
     */
    public EmptyDateException() {
        super("missing date/time!");
    }
}
