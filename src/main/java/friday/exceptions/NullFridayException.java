package friday.exceptions;

/**
 * Represents a potential exceptions cause by app.Friday chatbot due to the user
 * trying to add or retrieve a something that does not exist.
 */
public class NullFridayException extends Exception {
    public NullFridayException(String message) {
        super(message);
    }
}
