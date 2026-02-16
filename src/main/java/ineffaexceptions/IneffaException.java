package ineffaexceptions;

/**
 * Creates exceptions originating from Ineffa program.
 */
public class IneffaException extends Exception {

    /**
     * Passes error message to Exception class.
     *
     * @param message Message string.
     */
    public IneffaException(String message) {
        super(message);
    }
}
