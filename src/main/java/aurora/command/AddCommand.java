package aurora.command;

import aurora.task.Task;
import aurora.task.TaskList;

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
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    @Override
    public String execute(TaskList list) {
        int sizeBefore = list.size();
        list.add(task);
        assert list.size() == sizeBefore + 1 : "List size should increase by 1";
        return "Added " + task.getDescription() + " as a task into your list.";
    }
}
