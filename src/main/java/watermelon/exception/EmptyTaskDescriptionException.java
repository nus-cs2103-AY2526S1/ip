package watermelon.exception;

/**
 * An exception that is thrown when a required task description field is missing or empty.
 */
public class EmptyTaskDescriptionException extends WatermelonException {
    /**
     * Constructs a new EmptyTaskDescriptionException with the specified detail message.
     *
     * @param message The detail message explaining missing task description.
     *                The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}
