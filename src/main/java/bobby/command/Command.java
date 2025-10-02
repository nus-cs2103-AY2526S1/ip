package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Abstract base class for all commands in the Bobby application.
 * Provides the interface for executing commands and checking for exit.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving/loading tasks.
     * @return A string message describing the result of the command.
     * @throws BobbyException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
