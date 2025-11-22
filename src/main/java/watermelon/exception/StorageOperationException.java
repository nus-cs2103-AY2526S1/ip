package watermelon.exception;

/**
 * An exception that is thrown when storage operations fail.
 */
public class StorageOperationException extends WatermelonException {
    /**
     * Constructs a new StorageOperationException with the specified detail message.
     * @param message The detail message explaining invalid input.
     *                The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public StorageOperationException(String message) {
        super(message);
    }
}
