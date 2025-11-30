package briar.exception;

/**
 * Represents an exception that the chatbot Briar can throw.
 */
public class BriarException extends Exception {

    public BriarException() {
        super();
    }

    public BriarException(String message) {
        super(message);
    }
}
