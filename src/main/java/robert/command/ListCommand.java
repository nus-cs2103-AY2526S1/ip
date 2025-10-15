package robert.command;

import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        }
        
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}