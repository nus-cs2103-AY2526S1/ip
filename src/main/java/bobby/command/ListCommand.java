package bobby.command;

import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Command to display the list of all tasks to the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, showing all tasks.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "No tasks in your list, fam!";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(System.lineSeparator())
                    .append((i + 1)).append(". ")
                    .append(tasks.get(i).toString());
        }
        return response.toString();
    }
}
