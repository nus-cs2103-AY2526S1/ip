package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to unmark a task (i.e., mark it as not done).
 * <p>This command modifies the task's status by unmarking it as completed, and provides feedback via the UI.</p>
 * <p>It changes the task's state back to "not done" and notifies the user accordingly.</p>
 */
public class UnmarkCommand extends Command {

    /** The 1-based position of the task to be unmarked. */
    private final int position;

    /**
     * Constructs an {@code UnmarkCommand} with the given command string and task position.
     * The position is a 1-based index representing the task's position in the list.
     *
     * @param command  the command keyword (typically "unmark")
     * @param position the position of the task to unmark in the task list (1-based index)
     * @throws AssertionError if the position is less than or equal to 0
     */
    public UnmarkCommand(String command, int position) {
        super(command);
        assert position > 0 : "Position must be greater than 0";
        this.position = position;
    }

    /**
     * Executes the unmark command by updating the status of the specified task.
     * This method changes the task's status to "not done" and returns a confirmation message.
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} used for displaying messages (not used in this command)
     * @param storage the {@code Storage} responsible for saving task data (not used in this command)
     * @return a message indicating that the task has been unmarked as done
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.unmark(position);
        try {
            storage.updateTaskStatus(position, tasks.getTask(position));
        } catch (Exception e) {
            return "There was an error trying to unmark your task master: " + e.getMessage();
        }
        return message;
    }
}
