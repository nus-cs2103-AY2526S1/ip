package piper;

/**
 * Represents a recoverable application error.
 * Used to signal invalid input or storage issues with user-friendly messages.
 */
public class PiperException extends Exception {
    public PiperException(String message) { super(message); }
    public PiperException(String message, Throwable cause) { super(message, cause); }
}
