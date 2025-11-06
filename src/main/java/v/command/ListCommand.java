package v.command;

import v.storage.Storage;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes this command by displaying all tasks in the list.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI to render the list.
     * @param storage The storage (unused).
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return false;
    }
}
