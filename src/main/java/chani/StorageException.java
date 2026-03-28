package chani;

/**
 * Exception thrown to indicate errors that occur during storage operations,
 * such as loading tasks from or saving tasks to a file.
 */
public class StorageException extends Exception {
    /**
     * Constructs a new {@code StorageException} with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public StorageException(String message) {
        super(message);
    }
}
