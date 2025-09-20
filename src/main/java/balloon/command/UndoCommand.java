package balloon.command;

import balloon.exception.CommandNotUndoableException;
import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;

/**
 * Represents a command that undoes the previous command successfully executed.
 * <p>
 * The user can do a maximum of 1 UndoCommand in a row.
 * <p>
 * Not all commands can be undone.
 * In the case that the previous command is not undoable, a {@link CommandNotUndoableException}
 * will be thrown if the user tries to undo it.
 * <p>
 * The concrete commands undoable are: {@link TodoCommand}, {@link DeadlineCommand},
 * {@link EventCommand}, {@link DeleteCommand}, {@link MarkCommand}, and {@link UnmarkCommand}
 */
public class UndoCommand extends Command {

    /**
     * Undoes the last command successfully executed, if the last command is a
     * AddTaskCommand (TodoCommand, DeadlineCommand, or EventCommand) ,
     * DeleteCommand, MarkCommand or UnmarkCommand
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon)
            throws TaskNumberException, CommandNotUndoableException {
        Command lastCommand = balloon.getLastCommand();
        if (lastCommand == null || !lastCommand.isUndoable()) {
            throw new CommandNotUndoableException();
        }
        lastCommand.undo(tasks, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Previous command successfully undone!";
    }

    /**
     * An UndoCommand is not undoable!
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
