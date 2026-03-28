package simon.command;

import simon.storage.Storage;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * A <code>ListCommand</code> displays all current tasks to the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by setting the task list display message.
     *
     * @param tasks The task list to display.
     * @param ui The user interface for displaying the task list.
     * @param storage The storage system (unused for list command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setString(ui.showTaskList(tasks));
    }
}