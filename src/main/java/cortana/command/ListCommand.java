package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Lists all tasks currently stored in the task list. Displays the tasks to the user via the UI.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command which outputs the current task list.
     */
    @Override
    public String execute(TaskList taskList, FileHandler fileHandler) {
        if (taskList.toString().contains("no")) {
            return taskList.toString();
        } else {
            return "Here are the items in your list:\n\t" + taskList.toString();
        }
    }
}
