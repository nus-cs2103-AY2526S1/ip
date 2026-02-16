package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents command object that ends the program and exits
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to store the task list and exit the program, printing exit message from ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        storage.store(taskList);
        return ui.goodbye();
    }
    /**
     * Returns whether this command should terminate the program.
     * @return true as program terminates after this command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
