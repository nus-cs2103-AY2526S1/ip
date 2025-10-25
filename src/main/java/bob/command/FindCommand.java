package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Class for finding tasks using regex
 */
public class FindCommand extends Command {
    private final Task task;
    private final int idx;

    /**
     * Constructor for FindCommand
     * 
     * @param task Task stored with search string
     * @param idx Can be anything it is not used here
     */
    public FindCommand(Task task, int idx) {
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
        String result = tasks.find(task.getTaskName());
        return result;

    }
}
