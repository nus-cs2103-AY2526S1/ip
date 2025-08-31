package piper;

/**
 * Represents a recoverable application error.
 * Used to signal invalid input or storage issues with user-friendly messages.
 */
public class PiperException extends Exception {

    /**
     * Creates a PiperException with the given message.
     *
     * @param message description of the error.
     */
    public PiperException(String message) {
        super(message);
    }
}
