package aqua.exception;

/**
 * Represents an exception that occurs during storage operations.
 */
public class StorageException extends AquaException {
    /**
     * Creates a new StorageException with the specified message.
     *
     * @param message the detail message
     */
    public StorageException(String message) {
        super(message);
    }
}
