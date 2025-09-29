package floydai.command;

import java.io.IOException;

import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by showing all tasks in the TaskList to the user.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the UI for displaying tasks
     * @param storage the Storage (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        ui.showTasks(tasks.getAll());
    }
}
