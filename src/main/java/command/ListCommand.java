package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by returning a formatted representation
     * of all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI component used to display the tasks.
     * @param storage The storage component (not used in this command).
     * @return A message showing all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
