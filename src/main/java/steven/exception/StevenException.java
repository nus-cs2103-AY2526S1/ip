package steven.exception;

/**
 * The base class for all custom exceptions used in the Steven chatbot.
 */
public class StevenException extends Exception {
    public StevenException(String message) {
        super(message);
    }
}
