package yap.core;

/**
 * Custom exception class for Yap application errors.
 */
public class YapException extends Exception {
    public YapException(String message) {
        super(message);
    }

    public YapException(String message, Throwable cause) {
        super(message, cause);
    }
}
