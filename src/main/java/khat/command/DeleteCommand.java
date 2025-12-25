package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.Task;
import khat.task.TaskList;
import khat.ui.Ui;

/**
 * Represents a command to remove a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand to delete a specified task from the task list.
     *
     * @param index Index of task in task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Removes task at specified index and shows
     * @throws KhatException If task is not in task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        try {
            Task t = tasks.getTask(index);
            tasks.removeTask(index);
            ui.showMessage("Ok, I've removed this task:\n" + t.toString());
            ui.showMessage("There are " + tasks.getSize() + " remaining tasks.");
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new KhatException("This task doesn't exist! Check the index again :(");
        }
    }
}
