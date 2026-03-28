package clippy.command;

import clippy.ClippyException;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   The TaskList to operate on.
     * @param ui      The Ui for user interaction.
     * @param storage The Storage for saving/loading tasks.
     * @throws ClippyException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
