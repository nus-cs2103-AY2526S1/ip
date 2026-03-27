package command;

import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;

/**
 * Abstract base for all commands that the user can run.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param taskList task list
     * @param ui       UI for user interaction
     * @param storage  storage to save changes
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;

    /**
     * @return true if this command exits the program
     */
    public boolean isExit() {
        return false;
    }
}
