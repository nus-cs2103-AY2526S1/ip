package Command;

import JohnException.JohnException;

import Task.TaskItem;
import Task.TaskList;

import UI.Ui;

/**
 * Command that deletes a task at a given index from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a delete command.
     *
     * @param index Zero-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task at the specified index.
     * Performs bounds checking and shows a confirmation message via the UI.
     *
     * @param tasks Current task list
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If the list is empty or the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        if (tasks.getSize() == 0) {
            throw new JohnException("Your task list is empty. Nothing to delete.");
        } else if (index > tasks.getSize() - 1) {
            throw new JohnException("Task index " + (index + 1) + " is out of range.");
        }
        TaskItem removedTask = tasks.remove(index);
        ui.showDeleted(removedTask, tasks.getSize());
    }
}
