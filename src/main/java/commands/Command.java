package commands;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed in the program.
 * All concrete command classes (e.g., {@code AddTodoCommand},
 * {@code MarkCommand}, {@code ByeCommand}) must extend this class
 * and implement the {@link #execute(Object... args)} method.
 *
 * <p>A {@code Command} typically manipulates the {@link TaskList},
 * interacts with the {@link Ui}, and may read from or write to the
 * {@link Storage}.</p>
 */
public abstract class Command {
    protected String message;

    public abstract void execute(Object... args) throws RainyException;

    public String getMessage() {
        return message;
    }

    public boolean isExit() {
        return false;
    }

    public boolean isUndoable() {
        return true; // default true
    }

    /**
     * Undoes the effect of this command.
     * The default behavior is to do nothing.
     */
    public void undo(Object... args) throws RainyException {
        throw new UnsupportedOperationException("Undo not implemented for this command");
    }
}
