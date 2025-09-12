package command;
import application.Storage;
import application.TaskList;
import application.Ui;
import exception.RomidasException;
import tasks.Task;

/**
 * Command implementation for marking tasks as done or not done.
 * Handles task status changes, user feedback, and automatic persistence to storage.
 */
public class MarkCommand extends Command {
    // Constants for user feedback messages - eliminates magic strings
    private static final String TASK_MARKED_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_MARKED_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String TASK_PREFIX = "  ";
    private static final String STORAGE_FILENAME = "romidas.txt";
    
    /** The index of the task to be marked */
    private final int index;
    /** Whether to mark the task as done (true) or not done (false) */
    private final boolean isMark;
    
    /**
     * Constructs a new MarkCommand with the specified task index and mark status.
     *
     * @param index The index of the task to be marked (0-based).
     * @param isMark true to mark as done, false to mark as not done.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }
    
    /**
     * Executes the mark command by changing the task's completion status and saving to storage.
     * Provides appropriate user feedback based on whether the task was marked or unmarked.
     * Automatically persists changes to the storage file.
     *
     * @param tasks The task list containing the task to be marked.
     * @param ui The user interface for displaying feedback.
     * @param storage The storage component for persisting changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = updateTaskStatus(tasks);
        displayStatusMessage(ui, task);
        saveTasksToStorage(tasks, ui, storage);
    }
    
    /**
     * Updates the task status based on the mark operation.
     * Separates the core logic for clarity and testability.
     * 
     * @param tasks The task list containing the task to update
     * @return The updated task
     */
    private Task updateTaskStatus(TaskList tasks) {
        Task task = tasks.get(index);
        task.setIsDone(isMark);
        return task;
    }
    
    /**
     * Displays appropriate status message based on mark operation.
     * Shows different messages for marking done vs. undone.
     * 
     * @param ui The user interface for displaying messages
     * @param task The task that was marked
     */
    private void displayStatusMessage(Ui ui, Task task) {
        String statusMessage = isMark ? TASK_MARKED_DONE_MESSAGE : TASK_MARKED_UNDONE_MESSAGE;
        ui.showMessage(statusMessage);
        ui.showMessage(TASK_PREFIX + task.toString());
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
        return false; // Mark commands do not terminate the application
    }
}
