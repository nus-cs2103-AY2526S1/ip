package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

/**
 * Represents a command that marks a task as done.
 */
public class DoneCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code DoneCommand} with the task's 1-based index.
     *
     * @param index Index of the task to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as done, updating the UI,
     * and saving the updated task list to storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI used to display messages to the user.
     * @param storage The storage used to save tasks.
     * @throws BestbotException If the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (index < 1 || index > tasks.size()) {
            throw new BestbotException("Task number is invalid.");
        }

        Task task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.showTaskDone(task);
        storage.save(tasks.getTasks());
    }

    /**
     * Returns {@code false} as this command does not terminate the program.
     *
     * @return {@code false}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
