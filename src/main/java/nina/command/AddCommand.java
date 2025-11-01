package nina.command;

import nina.task.Task;
import nina.task.TaskList;

/**
 * Represents an AddCommand that can add a task to the TaskList.
 */
public class AddCommand implements Command {
    protected Task task;

    /**
     * Constructs a AddCommand object.
     *
     * @param task task to be added into the tasks list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        int dupIndex = tasks.findDuplicated(task);
        if (dupIndex != -1) {
            return "This task has already been added!\n See task " + (dupIndex + 1) + " "
                    + tasks.get(dupIndex) + "\n This task is not added again";
        }

        tasks.addTask(task);
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
