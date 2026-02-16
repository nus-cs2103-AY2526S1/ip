package kane;

/**
 * Custom exception class for handling application-specific errors in the Kane task manager.
 */
public class KaneException extends Exception {
    public KaneException(String message) {
        super(message);
    }
}
