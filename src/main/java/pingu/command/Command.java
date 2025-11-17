package pingu.command;

import pingu.PinguException;
import pingu.Storage;
import pingu.TaskList;
import pingu.Ui;

/**
 * All command classes extends this class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The TaskList to operate on.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save or load tasks.
     * @throws PinguException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PinguException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
