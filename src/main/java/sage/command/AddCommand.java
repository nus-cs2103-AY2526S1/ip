package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents an abstract command for adding a task.
 * This class provides common functionality for adding different types of tasks.
 */
public abstract class AddCommand extends Command {
    protected Task task;

    /**
     * Constructs an AddCommand with the specified task.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     * Adds the task to the TaskList, displays a confirmation message to the user,
     * and saves the updated task list to storage.
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to display messages to the user.
     * @param storage The Storage to save the updated task list.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.save(tasks.getTasks());
        } catch (SageException e) {
            return ui.showErrorAndReturn(e.getMessage());
        }
        return ui.showMessageAndReturn("Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
