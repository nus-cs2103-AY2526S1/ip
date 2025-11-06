package v.command;

import v.storage.Storage;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return True if the command should exit, false otherwise.
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
