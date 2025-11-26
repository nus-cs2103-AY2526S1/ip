package banana.command;

import java.io.IOException;

import banana.exceptions.BananaException;
import banana.task.Task;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to mark (0-based).
     */
    public MarkCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException, IOException {
        Task task = tasks.getTask(index);
        if (!task.isDone()) {
            tasks.markTask(index);
            storage.save(tasks);
            return "Nice! I've marked this task as done:\n  " + task;
        } else {
            return "This task is already marked as done!";
        }
    }
}
