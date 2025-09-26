package winnie.command;

import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for interaction.
     * @param storage The storage for saving/loading tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
