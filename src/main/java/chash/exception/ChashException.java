package chash.exception;

/** Custom runtime exception for CHASH-specific errors. */
public class ChashException extends RuntimeException {
    /**
     * Constructs a new {@code ChashException} with the specified message.
     *
     * @param msg Error message
     */
    public ChashException(String msg) {
        super(msg);
    }

    /**
     * Creates a new {@code ChashException} with the given message and cause.
     *
     * @param msg Error message
     * @param ex Underlying exception cause
     */
    public ChashException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
