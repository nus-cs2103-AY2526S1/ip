package stella.exception;

/**
 * Throws when the user include excess number of inputs (parameters) than what is required.
 */
public class ExcessParameterException extends StellaException {
    /**
     * Constructs a new ExcessParameterException, with the exception message.
     *
     * @param message The exception message.
     */
    public ExcessParameterException(String message) {
        super(message);
    }
}
