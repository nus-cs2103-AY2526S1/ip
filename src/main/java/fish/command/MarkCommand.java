package fish.command;

import fish.FishException;
import fish.storage.Storage;
import fish.task.Task;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Stands for a command that marks a task in the TaskList as completed.
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param idx Index (0-based) of the task to mark as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the MarkCommand.
     * Ticks the corresponding task.
     *
     * @param tasks The TaskList containing all the tasks currently.
     * @param ui Displays completion/incompletion status.
     * @param storage Records the change.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FishException {
        Task task = tasks.get(idx);
        if (task.isDone()) {
            throw new FishException("Heyyy this task is already done");
        }
        task.markAsDone();
        ui.printIn("Great! I've marked this task as done: " + task);
        storage.save(tasks.getTasks());
    }
}
