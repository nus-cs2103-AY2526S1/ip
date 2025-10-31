package cookie.exception;

/**
 * Represents a custom exception class for the Cookie program.
 * Thrown when errors specific to this program occur like
 * invalid user input.
 */
public class CookieException extends Exception {

    /**
     * Constructs a new CookieException with the specified error message
     *
     * @param message Message detailing cause of error.
     */
    public CookieException(String message) {
        super(message);
    }
}
