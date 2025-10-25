package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Class that handles unmarking of task as not done
 */
public class UnmarkCommand extends Command {
    private final Task task;
    private final int idx;

    /**
     * Constructor for list command
     * 
     * @param task Could be null or any task since it is not used
     * @param idx Id of task to be unmarked
     */
    public UnmarkCommand(Task task, int idx) {
        this.task = task;
        this.idx = idx;
    }

    /**
     * Execute the command
     * 
     * @param tasks TaskManager object handling the tasks
     * @param ui Ui object handling ui
     * @param storage Storage object handling storing of tasks
     */
    public String execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.unmark(idx);
        return ui.getUnmarkString(tasks.getTask(idx));

    }
}
