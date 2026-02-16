package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents command object to filter task list by a given filter string
 */
public class FindCommand extends Command {

    private final String filter;

    /**
     * Constructor for command to filter list by given filter string
     * @param filter string to filter task list
     */
    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the command to filter task list given filter string, creating a new filtered task list
     * and printing the display of filtered list by ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        TaskList filtered = taskList.find(filter);
        return ui.displayFilteredList(filtered);
    }

    /**
     * Returns whether the command should terminate the program
     * @return false as program should not terminate after this command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
