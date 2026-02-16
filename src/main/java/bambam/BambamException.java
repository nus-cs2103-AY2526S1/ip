package bambam;

/**
 * Represents a checked exception used in Bambam chatbot.
 * It throws an exception when an error occurs.
 */
public class BambamException extends Exception {

    /**
     * Creates an error with a specific message.
     * @param message The error message to be printed.
     */
    public BambamException(String message) {
        super(message);
    }

}
