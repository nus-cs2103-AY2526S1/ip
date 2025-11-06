package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

/**
 * Deletes a task from the task list by its index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a {@code DeleteCommand} with the given 1-based index.
     *
     * @param index Index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the specified task from the task list.
     * Updates the UI and saves the task list to storage.
     *
     * @param tasks   The task list to delete from.
     * @param ui      The UI used to display feedback.
     * @param storage The storage used to save tasks persistently.
     * @throws BestbotException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (index < 1 || index > tasks.size()) {
            throw new BestbotException("Index out of range.");
        }

        Task removed = tasks.remove(index - 1);
        ui.showTaskDeleted(removed, tasks.size());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command causes the program to exit.
     *
     * @return Always {@code false}, since deleting a task does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

