package cate.exception;

/**
 * Exception thrown when the user attempts to undo a command
 * but there are no commands available to undo in the Cate application.
 */
public class NothingToUndoException extends CateException {

    /**
     * Constructs a {@code NothingToUndoException} with a default message.
     */
    public NothingToUndoException() {
        super("Nothing to undo!");
    }
}
