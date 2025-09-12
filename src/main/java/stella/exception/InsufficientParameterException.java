package stella.exception;

/**
 * InsufficientParameterException is thrown when the user include
 * insufficient number of inputs (parameters) than what is required.
 */
public class InsufficientParameterException extends StellaException {
    public InsufficientParameterException(String message) {
        super(message);
    }
}

