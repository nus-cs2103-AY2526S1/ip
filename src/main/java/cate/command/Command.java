package cate.command;

import cate.exception.CannotUndoException;
import cate.exception.CateException;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command that can be executed by the Cate application.
 * Commands may optionally support undo and signal exit.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param storage the storage handler used to persist or retrieve tasks
     * @param tasks   the task list the command operates on
     * @param ui      the UI handler used to generate feedback messages
     * @return a string message to be shown to the user
     * @throws CateException if the command fails to execute properly
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws CateException;

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return true if the application should exit after this command; false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Indicates whether this command supports undo.
     *
     * @return true if the command can be undone; false otherwise
     */
    public boolean canUndo() {
        return false;
    }

    /**
     * Undoes the command, restoring the previous state.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list the command operates on
     * @param ui      the UI handler used to generate feedback messages
     * @return a string message to be shown to the user after undoing
     * @throws CateException if the command cannot be undone
     */
    public String undo(Storage storage, TaskList tasks, Ui ui) throws CateException {
        throw new CannotUndoException();
    }
}
