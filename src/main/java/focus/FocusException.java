package focus;

/**
 * Signals an error in command parsing or execution.
 */
public class FocusException extends Exception {


    /**
     * Constructs a FocusException with the given message.
     *
     * @param message Message of the error.
     */
    public FocusException(String message) {
        super(message);
    }

}
