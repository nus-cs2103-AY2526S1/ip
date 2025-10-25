package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    private final Task task;

    /**
     * Constructor for list command
     * 
     * @param Could be null or any task since it is not used
     */
    public ListCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command
     * 
     * @param tasks TaskManager object handling the tasks
     * @param ui Ui object handling ui
     * @param storage Storage object handling storing of tasks
     */
    public String execute(TaskManager tasks, Ui ui, Storage storage) {
        return tasks.getTasksString();
    }
}
