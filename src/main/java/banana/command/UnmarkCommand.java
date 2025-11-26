package banana.command;

import java.io.IOException;

import banana.exceptions.BananaException;
import banana.task.Task;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to unmark (0-based).
     */
    public UnmarkCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException, IOException {
        Task task = tasks.getTask(index);
        if (task.isDone()) {
            tasks.unmarkTask(index);
            storage.save(tasks);
            return "Ok! I've unmarked this task as not done:\n  " + task;
        } else {
            return "This task is already unmarked as not done!";
        }
    }
}
