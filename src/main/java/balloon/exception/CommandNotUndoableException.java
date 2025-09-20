package balloon.exception;

/**
 * Represents an exception that is thrown when an undo operation is attempted
 * but the previous command cannot be undone.
 * <p>
 * This may happen if there is no previous command to undo, or if the
 * previous command does not support undoing.
 */
public class CommandNotUndoableException extends BalloonException {
    public CommandNotUndoableException() {
        super("The previous command is not undoable, or there is no previous command!");
    }
}
