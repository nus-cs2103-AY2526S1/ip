package objectclasses.exception;

/**
 * Represents an attempt to match invalid enum types.
 */
public class LynxEnumException extends LynxException {

    public LynxEnumException(String message) {
        super(message);
    }

}
