package bin.exception;

/**
 * Signals an error caused by no or invalid input.
 */
public class NoInputException extends BinException{
    public NoInputException(String input) {
        super("    Please have a valid " + input);
    }
}
