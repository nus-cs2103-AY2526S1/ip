package borat.exception;

/**
 * Application-level checked exception for Borat.
 * <p>
 * Used for signaling user-facing errors that should be handled gracefully
 * by the caller instead of crashing the program.
 */
public class BoratExceptions extends Exception {

    /**
     * Creates a new {@code BoratExceptions} with the given message.
     *
     * @param msg description of the error condition
     */
    public BoratExceptions(String msg) {
        super(msg);
    }

}


