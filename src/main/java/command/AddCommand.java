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
    // Constants for user feedback messages - eliminates magic strings
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_ALREADY_EXISTS_MESSAGE = "Task already exists";
    private static final String EXISTING_TASK_PREFIX = "Existing task at position ";
    private static final String TASK_COUNT_MESSAGE_PREFIX = "Now you have ";
    private static final String TASK_COUNT_MESSAGE_SUFFIX = " tasks in your list.";
    private static final String TASK_PREFIX = "  ";
    private static final String STORAGE_FILENAME = "romidas.txt";
    
    /** The task to be added to the list */
    private final Task task;
    
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
        // Check for duplicates before adding
        if (tasks.containsDuplicate(task)) {
            displayDuplicateMessage(ui, tasks);
            return; // Don't add the task or save to storage
        }
        
        addTaskToList(tasks);
        displaySuccessMessage(ui, tasks.size());
        saveTasksToStorage(tasks, ui, storage);
    }
    
    /**
     * Adds the task to the task list.
     * Separated for single responsibility and clarity.
     * 
     * @param tasks The task list to add to
     */
    private void addTaskToList(TaskList tasks) {
        tasks.add(task);
    }
    
    /**
     * Displays message when a duplicate task is detected.
     * Shows "Task already exists" message and the position of existing task.
     * 
     * @param ui The user interface for displaying messages
     * @param tasks The task list to find the duplicate position
     */
    private void displayDuplicateMessage(Ui ui, TaskList tasks) {
        ui.showMessage(TASK_ALREADY_EXISTS_MESSAGE);
        
        int duplicateIndex = tasks.findDuplicateIndex(task);
        if (duplicateIndex != -1) {
            ui.showMessage(EXISTING_TASK_PREFIX + duplicateIndex);
        }
    }
    
    /**
     * Displays success message to the user.
     * Shows the added task and updated task count.
     * 
     * @param ui The user interface for displaying messages
     * @param totalTasks The total number of tasks after addition
     */
    private void displaySuccessMessage(Ui ui, int totalTasks) {
        ui.showMessage(TASK_ADDED_MESSAGE);
        ui.showMessage(TASK_PREFIX + task.toString());
        
        String countMessage = TASK_COUNT_MESSAGE_PREFIX + totalTasks + TASK_COUNT_MESSAGE_SUFFIX;
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
        return false; // Add commands do not terminate the application
    }
}
