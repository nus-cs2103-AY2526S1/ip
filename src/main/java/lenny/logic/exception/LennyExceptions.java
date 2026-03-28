package lenny.logic.exception;

/**
 * Represents an exception specific to the Lenny application.
 */
public class LennyExceptions extends Exception {
    // Default constructor
    public LennyExceptions() {
        super();
    }

    /**
     * Creates a new LennyException with the given message.
     *
     * @param message Error message.
     */
    public LennyExceptions(String message) {
        super(message);
    }

}
