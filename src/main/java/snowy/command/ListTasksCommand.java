package snowy.command;

import snowy.TaskList;
import snowy.Storage;

public class ListTasksCommand extends Command {
    /**
     * Lists all the tasks in the task list.
     * @param tasks
     * @param storage
     * @return A string representation of all tasks in the task list
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() == 0) {
            return "You don't have upcoming tasks right now! Yeah! :D";
        } else {
            return tasks.toString();
        }
    }
}
