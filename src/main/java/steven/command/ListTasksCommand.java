package steven.command;

import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Lists all tasks currently in the task list.
 * The command retrieves the formatted string representation of all tasks.
 */
public class ListTasksCommand implements Command {
    @Override
    public String execute(Storage storage, TaskList tasks) {
        return tasks.getToDoListString();
    }
}
