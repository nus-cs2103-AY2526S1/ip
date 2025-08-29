package command;
import application.Storage;
import application.TaskList;
import application.Ui;
import exception.RomidasException;
import tasks.Task;

/**
 * Command implementation for adding new tasks to the task list.
 * Handles task addition, user feedback, and automatic persistence to storage.
 */
public class AddCommand extends Command {
    /** The task to be added to the list */
    private Task task;
    
    /**
     * Constructs a new AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    
    /**
     * Executes the add command by adding the task to the list and saving to storage.
     * Provides user feedback about the added task and current list size.
     * Automatically persists changes to the storage file.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface for displaying feedback.
     * @param storage The storage component for persisting changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
        try {
            storage.saveTasks("romidas.txt", tasks.retreive());
        } catch (RomidasException e) {
            ui.showError(e.getMessage());
        }
    }
    
    @Override
    public boolean isBye() {
        return false;
    }
}
