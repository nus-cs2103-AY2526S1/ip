package rafayel;

/**
 * Exception class that handles the errors in Rafayel.
 */
public class RafayelException extends Exception {

    /**
     * Constructs a new RafayelException with a user input message.
     * @param message user input message about the error.
     */
    public RafayelException(String message) {
        super(message);
    }

}