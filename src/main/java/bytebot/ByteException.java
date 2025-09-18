package bytebot;

/**
 * Represents errors in the Bytebot.
 */
public class ByteException extends Exception {

    /**
     * Creates a new ByteException with the given message.
     * The message is prefixed with "Bzzz!!! " for Bytebot
     *
     * @param message error details
     */
    public ByteException(String message) {
        super("Bzzzz, ByteBot does not accept this command!!! " + message);
    }
}


