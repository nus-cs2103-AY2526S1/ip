package bin.exception;

/**
 * Signals an error caused by no or invalid task number input.
 */
public class NoTaskNumberException extends BinException{
    public NoTaskNumberException() {
        super("    Please state the correct task number to handle");
    }
}
