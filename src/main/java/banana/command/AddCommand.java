package banana.command;

import java.io.IOException;

import banana.exceptions.BananaException;
import banana.task.Task;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException, IOException {
        tasks.addTask(task);
        storage.save(tasks);
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
