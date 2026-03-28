package cattis.exception;

/**
 * Represents time-related parsing errors
 */
public class CattisInvalidTimeException extends CattisException {
    public CattisInvalidTimeException(String msg) {
        super(PARSE_TIME + msg);
    }
}
