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
    /** The index of the task to be marked */
    private int index;
    /** Whether to mark the task as done (true) or not done (false) */
    private boolean isMark;
    
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
        Task task = tasks.get(index);
        task.setIsDone(isMark);
        
        if (isMark) {
            ui.showMessage("Nice! I've marked this task as done:");
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:");
        }
        ui.showMessage("  " + task.toString());
        
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
