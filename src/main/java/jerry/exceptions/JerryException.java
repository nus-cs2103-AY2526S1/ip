package jerry.exceptions;

/**
 * The base exception class for all specific exceptions.
 */
public class JerryException extends Exception {
    public JerryException(String message) {
        super(message);
    }
}
