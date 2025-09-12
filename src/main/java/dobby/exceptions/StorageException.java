package dobby.exceptions;

/** Thrown when storage operations fail. */
public class StorageException extends DobbyException {
    public StorageException(String message) {
        super(message);
    }
}
