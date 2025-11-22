package watermelon.exception;
/**
 * Base exception class for all Watermelon chatbot application errors.
 * Serves as the root for all custom exceptions thrown by the Watermelon chatbot application.
 */
public class WatermelonException extends Exception {
    /**
     * Constructs a new WatermelonException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     *                The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public WatermelonException(String message) {
        super(message);
    }
}
