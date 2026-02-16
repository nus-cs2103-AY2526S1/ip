package yang;

/**
 * Represents an exception specific to the Yang application.
 */
public class YangException extends Exception {

    /**
     * Creates a YangException with the given message.
     *
     * @param message the message detail
     */
    public YangException(String message) {
        super(message);
    }
}
