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
    // Constants for user feedback messages - eliminates magic strings
    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_COUNT_MESSAGE_PREFIX = "Now you have ";
    private static final String TASK_COUNT_MESSAGE_SUFFIX = " tasks in your list.";
    private static final String TASK_PREFIX = "  ";
    private static final String STORAGE_FILENAME = "romidas.txt";
    
    /** The index of the task to be deleted */
    private final int index;
    
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
        Task taskToDelete = retrieveTaskToDelete(tasks);
        removeTaskFromList(tasks);
        displaySuccessMessage(ui, taskToDelete, tasks.size());
        saveTasksToStorage(tasks, ui, storage);
    }
    
    /**
     * Retrieves the task that will be deleted.
     * Separated for clarity and potential validation in future.
     * 
     * @param tasks The task list containing the task
     * @return The task to be deleted
     */
    private Task retrieveTaskToDelete(TaskList tasks) {
        return tasks.get(index);
    }
    
    /**
     * Removes the task from the task list.
     * Separated for single responsibility.
     * 
     * @param tasks The task list to remove from
     */
    private void removeTaskFromList(TaskList tasks) {
        tasks.remove(index);
    }
    
    /**
     * Displays success message to the user.
     * Shows the deleted task and updated task count.
     * 
     * @param ui The user interface for displaying messages
     * @param deletedTask The task that was deleted
     * @param remainingTasks The number of tasks remaining
     */
    private void displaySuccessMessage(Ui ui, Task deletedTask, int remainingTasks) {
        ui.showMessage(TASK_DELETED_MESSAGE);
        ui.showMessage(TASK_PREFIX + deletedTask.toString());
        
        String countMessage = TASK_COUNT_MESSAGE_PREFIX + remainingTasks + TASK_COUNT_MESSAGE_SUFFIX;
        ui.showMessage(countMessage);
    }
    
    /**
     * Saves the updated task list to storage.
     * Handles storage errors gracefully by displaying error messages.
     * 
     * @param tasks The task list to save
     * @param ui The user interface for error display
     * @param storage The storage component
     */
    private void saveTasksToStorage(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(STORAGE_FILENAME, tasks.retreive());
        } catch (RomidasException e) {
            ui.showError(e.getMessage());
        }
    }
    
    @Override
    public boolean isBye() {
        return false; // Delete commands do not terminate the application
    }
}
