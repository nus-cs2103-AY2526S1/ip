package cattis.exception;

/**
 * Handle parsing error
 */
public class CattisParseException extends CattisException {
    public CattisParseException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format(PARSING, super.getMessage());
    }
}
