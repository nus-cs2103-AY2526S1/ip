package command;

import task.Task;
import java.util.List;

/**
 * Command to add new task to task list.
 */
public class AddCommand implements Command {
    private final Task task;

    /**
     * Creates a new {@code AddCommand}.
     *
     * @param task task to add to list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(List<Task> list) {
        list.add(task);
        return "Added " + task.getDescription() + " as a task into your list.";
    }
}
