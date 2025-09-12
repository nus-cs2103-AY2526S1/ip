package stella.exception;

/**
 * ExcessParameterException is thrown when the user include
 * excess number of inputs (parameters) than what is required.
 */
public class ExcessParameterException extends StellaException {
    public ExcessParameterException(String message) {
        super(message);
    }
}
