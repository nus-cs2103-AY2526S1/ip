package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Command to list all current tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by displaying the list of tasks.
     *
     * @param tasks TaskList to display
     * @param ui Ui for interaction
     * @param storage Storage (not used here)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Indicates this command does not terminate the program.
     *
     * @return false as this command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
