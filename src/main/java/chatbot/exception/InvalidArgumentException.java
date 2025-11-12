package chatbot.exception;


/**
 * Runtime exception for invalid or missing arguments.
 */
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return getMessage(); // show only the message (no class name)
    }
}