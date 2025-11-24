package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand implements Command {
    private final int index; // 1-based index

    /**
     * Creates a new UnmarkCommand.
     */
    public UnmarkCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments); // 1-based
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.getSize()) {
            throw new DukeException("Task number out of range: " + index);
        }

        tasks.unmarkTask(index);
        ui.showTaskStatusChanged(tasks.getTask(index), false);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
