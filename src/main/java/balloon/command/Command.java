package balloon.command;

import balloon.exception.CommandNotUndoableException;
import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;

/**
 * Represents an executable user command in the application.
 * <p>
 * Each concrete subclass of {@code Command} encapsulates a specific action
 * that can be performed on the task list, such as adding, deleting, or marking
 * tasks as completed, using the execute method.
 * </p>
 */
public abstract class Command {
    /**
     * Represents the types of commands that can be issued in Balloon.
     * <p>
     * Each enum constant corresponds to a specific command the user can enter.
     */
    public enum CommandType {
        LIST, EXIT,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK, DELETE,
        FIND,
        UNDO
    }

    /**
     * Executes the actions encapsulated within this Command.
     *
     * @param tasks the list of tasks.
     * @param storage the storage handler used to load or save tasks.
     * @throws TaskNumberException if the command refers to an invalid task number.
     */
    public abstract void execute(TaskList tasks, Storage storage, Balloon balloon)
            throws TaskNumberException, CommandNotUndoableException;


    /**
     * @return true if command is Exit; false otherwise.
     */
    public abstract boolean isExit();

    /**
     * @return the GUI's response to the command passed in by the user
     */
    public abstract String getString();

    /**
     * By default, most commands are not undoable, so we throw an exception.
     * <p>
     * We override this method in subclasses of Command that are undoable, i.e.
     * {@link AddTaskCommand}, {@link DeleteCommand}, {@link MarkCommand}, and {@link UnmarkCommand},
     * to provide their implementations.
     *
     * @throws CommandNotUndoableException
     */
    public void undo(TaskList tasks, Storage storage)
            throws CommandNotUndoableException, TaskNumberException {
        throw new CommandNotUndoableException();
    }

    /**
     * Returns true iff the command is undoable; false otherwise.
     * A command is undoable iff it is a {@link AddTaskCommand}, {@link DeleteCommand},
     * {@link MarkCommand}, or {@link UnmarkCommand}
     */
    public abstract boolean isUndoable();
}
