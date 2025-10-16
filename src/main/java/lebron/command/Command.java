package lebron.command;

import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.TaskList;

/**
 * Represents a command that can be executed in the LeBron application.
 * Each command performs a specific action on the task list, user interface, and storage.
 */
public abstract class Command {
    /**
     * Returns true if this command signals the application to exit.
     */
    public abstract boolean isExit();

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving tasks.
     * @return A string message to be displayed to the user after execution.
     * @throws LeBronException If an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException;
}
