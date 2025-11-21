package david.exception;

/**
 * Throws an exception if type name from the command is invalid.
 */
public class InvalidTypeException extends DavidException {
    public InvalidTypeException(String type) {
        super("the type " + type + " you entered is invalid.");
    }
}
