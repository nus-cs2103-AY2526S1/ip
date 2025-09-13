package hermione.exceptions;

/**
 * Represents an exception that occurs in the NumberUtils class.
 */
public class NumberUtilsException extends RuntimeException {
    public NumberUtilsException(String message) {
        super("[ERROR] Number Error: " + message);
    }
}
