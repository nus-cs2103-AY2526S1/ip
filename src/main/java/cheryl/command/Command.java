package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a user command in the Duke application.
 *
 * This is part of the Command pattern used in Cheryl:
 * each subclass encapsulates a specific user action (e.g. add, delete, exit),
 * and defines how it should be executed.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList to operate on
     * @param ui      The Ui object for displaying messages
     * @param storage The Storage object for saving tasks
     * @throws DukeException If an error occurs during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if this command exits Duke, false otherwise
     */
    public abstract boolean isExit();
}