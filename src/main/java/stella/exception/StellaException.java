package stella.exception;

/**
 * Throws when an exception specific to the Stella application occur.
 */
public class StellaException extends RuntimeException {
    /**
     * Constructs a new StellaException, with the exception message.
     *
     * @param message The exception message.
     */
    public StellaException(String message) {
        super(message);
    }
}
