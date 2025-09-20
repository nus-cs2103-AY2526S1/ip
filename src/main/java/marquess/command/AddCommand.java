package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.task.Task;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for command to add task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        String res = taskList.addTask(task);
        storage.save(taskList);
        return res;
    }
}
