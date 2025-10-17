package lux.exception;

/**
 * Exception used to represent user-facing errors and failures in the
 * application (for example: invalid command format, parsing failures).
 */
public class LuxException extends Exception {
    public LuxException(String err) {
        super(err);
    }
}
