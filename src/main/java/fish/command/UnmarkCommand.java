package fish.command;

import fish.FishException;
import fish.storage.Storage;
import fish.task.Task;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Stands for a command that marks a task in the TaskList as incompleted.
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a UnmarkCommand with the given task index.
     *
     * @param idx Index (0-based) of the task to unmark.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the UnmarkCommand.
     * Unticks the corresponding task.
     *
     * @param tasks The TaskList containing all the tasks currently.
     * @param ui Displays completion/incompletion status.
     * @param storage Records the change.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FishException {
        Task task = tasks.get(idx);
        if (!task.isDone()) {
            throw new FishException("You cannot unmark this task because it has not been completed!");
        }
        task.markAsUndone();
        ui.printIn("Undo" + task + ", remember to complete it soon ~");
        storage.save(tasks.getTasks());
    }
}
