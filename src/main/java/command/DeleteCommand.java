package command;
import application.Storage;
import application.TaskList;
import application.Ui;
import exception.RomidasException;
import tasks.Task;

/**
 * Command implementation for deleting tasks from the task list.
 * Handles task removal, user feedback, and automatic persistence to storage.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted */
    private int index;
    
    /**
     * Constructs a new DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the delete command by removing the task from the list and saving to storage.
     * Provides user feedback about the deleted task and current list size.
     * Automatically persists changes to the storage file.
     *
     * @param tasks The task list to remove the task from.
     * @param ui The user interface for displaying feedback.
     * @param storage The storage component for persisting changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + deletedTask.toString());
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
