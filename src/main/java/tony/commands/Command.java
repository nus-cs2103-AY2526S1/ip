package tony.commands;

import tony.exceptions.TonyException;
import tony.storage.Storage;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command given by the user input
 */
public abstract class Command {

    /**
     * Executes the command given by the user.
     *
     * @param tasks The {@link TaskList} that stores tasks.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @throws TonyException If the command is invalid.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws TonyException;
}
