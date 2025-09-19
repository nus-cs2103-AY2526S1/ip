package jerome.command;

import jerome.JeromeException;
import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {
    /**
     * Executes the command with access to the task list, UI, and storage.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load task data.
     * @return response message to be shown in the gui.
     * @throws JeromeException If the command cannot be executed properly.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException;
}
