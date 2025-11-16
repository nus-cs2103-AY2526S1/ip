package exception;

/**
 * Exception thrown when a file is found to be corrupted or unreadable.
 */
public class CorruptedFileException extends Exception {
    public CorruptedFileException(String message) {
        super(message);
    }
}
