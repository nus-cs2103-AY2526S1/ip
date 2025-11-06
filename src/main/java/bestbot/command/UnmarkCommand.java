package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

/**
 * Represents a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the task's 1-based index.
     *
     * @param index Index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (index < 1 || index > tasks.size()) {
            throw new BestbotException("Task number is invalid.");
        }

        Task task = tasks.getTask(index - 1);
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
