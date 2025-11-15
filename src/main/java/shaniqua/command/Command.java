package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;
    /**
     * Executes the command with the given parameters.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for interaction
     * @param storage the storage system for persistence
     * @throws CommandFailException if the command execution fails
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if this command should cause the application to exit.
     *
     * @return true if the application should exit after this command, false otherwise
     */
    public boolean isExit() {
        return isExit;
    }
}
