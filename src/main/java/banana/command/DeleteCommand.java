package banana.command;

import java.io.IOException;

import banana.exceptions.BananaException;
import banana.task.Task;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to delete (0-based).
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException, IOException {
        Task removed = tasks.deleteTask(index);
        storage.save(tasks);
        return "Noted. I've removed this task:\n  " + removed
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
