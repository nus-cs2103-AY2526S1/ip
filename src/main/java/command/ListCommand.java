package command;
import application.Storage;
import application.TaskList;
import application.Ui;

/**
 * Command implementation for displaying the current task list.
 * Shows all tasks in the list with their completion status and descriptions.
 */
public class ListCommand extends Command {
    
    /**
     * Executes the list command by displaying all tasks to the user.
     * Retrieves the task list and passes it to the UI for formatted display.
     * Storage parameter is not used as listing doesn't modify data.
     * 
     * @param tasks The task list to display
     * @param ui The user interface for displaying the list
     * @param storage The storage component (not used in list operations)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.retreive());
    }
    
    @Override
    public boolean isBye() {
        return false; // List commands do not terminate the application
    }
}
