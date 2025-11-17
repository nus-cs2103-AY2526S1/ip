package bin.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class BinException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public BinException(String message) {
        super(message);
    }
}
