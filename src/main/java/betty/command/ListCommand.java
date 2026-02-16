package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents the command object to display the list of the task list
 */
public class ListCommand extends Command {
    /**
     * Executes the command to display the list of the task list on the ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        return ui.displayList(taskList);
    }
    /**
     * Returns whether this command should terminate the program.
     * @return false as program does not terminate after this command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
