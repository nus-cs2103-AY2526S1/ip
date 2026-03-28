package clippy;

/**
 * Represents a custom exception specific to the Clippy application.
 */
public class ClippyException extends Exception {
    /**
     * Constructs a new ClippyException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ClippyException(String message) {
        super(message);
    }
}
