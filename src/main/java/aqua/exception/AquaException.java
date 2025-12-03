package aqua.exception;

/**
 * Base exception class for Aquaelated exceptions.
 */
public class AquaException extends Exception {
    /**
     * Constructor for Aquaception
     *
     * @param message Error message
     */
    public AquaException(String message) {
        super(message);
    }
}
