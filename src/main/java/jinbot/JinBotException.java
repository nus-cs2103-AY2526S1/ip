package jinbot;

/**
 * Represents an exception specific to the JinBot application.
 */
public class JinBotException extends Exception {
    /**
     * Constructs a JinBotException with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public JinBotException(String message) {
        super(message);
    }
}
