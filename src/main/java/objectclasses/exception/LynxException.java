package objectclasses.exception;

/**
 * Represents an error identified by the program itself.
 */
public class LynxException extends Exception {

    public LynxException(String message) {
        super(message);
    }

}
