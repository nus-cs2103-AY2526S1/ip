package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Represents a command to add tasks
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand
     * 
     * @param task The task to be added
     */
    public AddCommand(Task task) {
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
        String addTask = tasks.addTask(task);
        if (addTask.equals("Duplicates found")) {
            return ui.getDuplicateString(task);
        }
        return ui.getAddTaskString(task);
    }
}
