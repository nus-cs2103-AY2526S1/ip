package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Abstract root of all user commands processed by the application.
 *
 * <p>Concrete subclasses implement the {@link #execute} method to perform the
 * command's action (for example: add a task, list tasks, mark a task) and
 * return a user-facing message describing the result. Commands may also
 * indicate whether executing them should terminate the application via
 * {@link #isExit()}.
 */
public abstract class Command {
    /**
     * Execute the command using the provided application state and helpers.
     *
     * @param tasks    the current task list to operate on
     * @param ui       the UI helper used to produce user-facing messages
     * @param storage  persistent storage used to read/write data if needed
     * @param aliases  alias mapping used by the parser/commands
     * @return a String message intended for display to the user
     * @throws LuxException when the command cannot be executed (e.g. invalid
     *                      arguments or IO failure reported by storage)
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException;

    /**
     * @return true if executing this command means the application should exit
     */
    public abstract boolean isExit();
}
