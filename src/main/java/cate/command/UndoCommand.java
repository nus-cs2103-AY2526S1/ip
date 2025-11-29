package cate.command;

import cate.exception.CateException;
import cate.exception.NothingToUndoException;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to undo the most recent undoable command.
 * Uses a {@link CommandManager} to track command history.
 */
public class UndoCommand extends Command {
    private final CommandManager manager;

    /**
     * Constructs an UndoCommand with the specified command manager.
     *
     * @param manager the CommandManager that tracks executed commands
     */
    public UndoCommand(CommandManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the undo command.
     * Retrieves the most recent undoable command from the manager and undoes it.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list on which to perform the undo
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the undo operation
     * @throws CateException if there are no commands available to undo
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws CateException {
        if (!manager.hasUndoableCommand()) {
            throw new NothingToUndoException();
        }
        Command last = manager.popLastCommand();
        return last.undo(storage, tasks, ui);
    }

    /**
     * Indicates whether this command supports undo.
     * Undoing an undo command is not supported.
     *
     * @return false
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
