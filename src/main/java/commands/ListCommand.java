package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;
/**
 * The ListCommand class represents a command to display all tasks in the task list.
 * It retrieves and prints the list of tasks from the provided task list.
 * This class extends the Command class.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand. This method retrieves all tasks from the task list
     * and displays them to the user.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The UI object used to interact with the user (though not used in this method).
     * @param storage The storage object (though not used in this method).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        return ui.showList(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
