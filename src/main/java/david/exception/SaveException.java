package david.exception;

/**
 * Throws an exception if the execution fails to save the tasks.
 */
public class SaveException extends DavidException {
    public SaveException(String msg) {
        super(msg);
    }
}
