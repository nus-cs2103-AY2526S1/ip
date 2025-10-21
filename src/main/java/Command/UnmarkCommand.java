package Command;

import JohnException.JohnException;
import Task.TaskList;
import UI.Ui;

/**
 * Command to unmark completed tasks.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Initialises unmark command.
     *
     * @param index Zero-based index of the task to delete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes command by validating input index, finding the task at the index,
     * and marking it as not completed.
     *
     * @param tasks Current task list.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If current list is empty or index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        if (tasks.getSize() == 0) {
            throw new JohnException("Your task list is empty. Nothing to unmark.");
        } else if (index > tasks.getSize() - 1) {
            throw new JohnException("Task index " + (index + 1) + " is out of range.");
        }
        tasks.unmark(index);
        ui.showUnmarked(tasks.getItem(index));
    }
}
