package betty.exception;

/**
 * Represents Exception class to be thrown by the chatbot
 */
public class BettyException extends Exception {

    public BettyException(String message) {
        super(message);
    }
}
