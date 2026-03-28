package aurora.command;

import aurora.task.Task;
import aurora.task.TaskList;

/**
 * Command to delete specified task from task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Creates a new {@code DeleteCommand}.
     *
     * @param index 1-based index of the task to be deleted
     */
    public DeleteCommand(int index) {
        assert index > 0 : "Index must be non-negative";
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        if (index == 0 || index > list.size()) {
            return "There is no task numbered " + index + ".";
        }

        int sizeBefore = list.size();
        Task task = list.remove(index - 1);
        int sizeAfter = list.size();
        assert sizeAfter == sizeBefore - 1 : "List size should decrease by 1";
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + sizeAfter + " tasks in your list.";
    }
}
