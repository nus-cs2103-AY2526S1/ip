package tsunderechan.command;

import java.io.IOException;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command that the user has inputted and is to be carried out.
 */
public abstract class Command {
    /**
     * Executes the command entered.
     *
     * @param tasks TaskList to be modified.
     * @param ui Ui used for user input and output.
     * @param storage Storage to save changes made to file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
