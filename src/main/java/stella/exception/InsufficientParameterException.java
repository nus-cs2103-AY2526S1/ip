package stella.exception;

/**
 * Throws when the user include insufficient number of inputs (parameters) than what is required.
 */
public class InsufficientParameterException extends StellaException {
    /**
     * Constructs a new InsufficientParameterException, with the exception message.
     *
     * @param message The exception message.
     */
    public InsufficientParameterException(String message) {
        super(message);
    }
}
