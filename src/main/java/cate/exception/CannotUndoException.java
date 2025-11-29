package cate.exception;

/**
 * Exception thrown when an attempt is made to undo a command
 * that does not support undo functionality in the Cate application.
 * <p>
 * Commands that override {@link cate.command.Command#canUndo()} to return
 * {@code false} will throw this exception if {@link cate.command.Command#undo}
 * is called.
 * </p>
 */
public class CannotUndoException extends CateException {

    /**
     * Constructs a {@code CannotUndoException} with a default message
     * indicating that the command cannot be undone.
     */
    public CannotUndoException() {
        super("This command cannot be undone.");
    }
}
