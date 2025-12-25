package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.Task;
import khat.task.TaskList;
import khat.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand to mark a specified task from the task list as completed.
     *
     * @param index Index of task to be marked as incomplete.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks task specified as completed and shows a preview of the completed task.
     * @throws KhatException If task index does not match any task in the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        try {
            Task curr = tasks.getTask(index);
            curr.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n" + curr);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("This task doesn't exist! Check the index again :(");
        }
    }
}
