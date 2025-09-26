package winnie.exception;

/**
 * Base class for all exceptions thrown by the application.
 */
public class WinnieException extends Exception {
    public WinnieException(String message) {
        super(message);
    }
}
