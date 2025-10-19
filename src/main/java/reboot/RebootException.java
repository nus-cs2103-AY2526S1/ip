package reboot;

/**
 * Represents an unchecked exception that is thrown when error occurs.
 */
public class RebootException extends RuntimeException {
    public RebootException(String message) {
        super(message);
    }
}
