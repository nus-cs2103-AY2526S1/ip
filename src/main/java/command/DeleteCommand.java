package command;

import task.Task;
import java.util.List;

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
        this.index = index;
    }

    @Override
    public String execute(List<Task> list) {
        if (index == 0 || index > list.size()) {
            return "There is no task numbered " + index + ".";
        }

        Task task = list.remove(index - 1);
        int len = list.size();
        return "Noted. I've removed this task:\n" + task +"\nNow you have " + len + " tasks in your list.";
    }
}
