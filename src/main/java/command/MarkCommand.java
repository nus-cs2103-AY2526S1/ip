package command;

import task.Task;
import java.util.List;

/**
 * Command to mark specified task as complete.
 */
public class MarkCommand implements Command{
    private final int index;

    /**
     * Creates a new {@code MarkCommand}.
     *
     * @param index 1-based index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(List<Task> list) {
        if (index == 0 || index > list.size()) {
            return "There is no task numbered " + index + ".";
        }

        Task task = list.get(index - 1);
        task.complete();
        return "Nice! I've marked this task as completed.\n" + task;
    }
}
