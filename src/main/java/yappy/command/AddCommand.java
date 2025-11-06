package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to add tasks into the TaskList.
 * An <code>AddCommand</code> object has a Task field representing the task to be added.
 *
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs a new {@code AddCommand} object with the specified Task.
     * This constructor initializes the AddCommand's Task.
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        return ("Alrighty I have added the following task:\n\t   " + this.task.toString());
    }
}
