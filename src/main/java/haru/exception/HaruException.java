package haru.exception;

/**
 * Base class for all Haru exceptions.
 */
public abstract class HaruException extends Exception {

    /**
     * Constructs a HaruException with a message.
     *
     * @param message the exception message
     */
    public HaruException(String message) {
        super(message);
    }
}
