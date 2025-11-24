package cheryl.command;

import cheryl.task.Task;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand implements Command {
    private final int index; // 1-based index

    /**
     * Creates a new DeleteCommand.
     *
     * @param arguments The index of the task to delete (as a String)
     * @throws DukeException If the index is not a valid number
     */
    public DeleteCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments); // 1-based
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Executes the command: deletes the task at the given index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.getSize()) {
            throw new DukeException("Task number out of range: " + index);
        }

        Task removed = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removed);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");

        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
