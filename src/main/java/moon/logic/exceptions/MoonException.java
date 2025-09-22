package moon.logic.exceptions;

/**
 * General exception for all errors thrown by the Moon chatbot.
 */
public class MoonException extends Exception {
    /**
     * Creates a new MoonException with the specified detail message.
     *
     * @param message Description of the error
     */
    public MoonException(String message) {
        super(message);
    }
}
