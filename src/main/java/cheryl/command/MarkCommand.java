package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private int index; // now stores 1-based index

    /**
     * Creates a new MarkCommand.
     *
     * @param arguments The index of the task to mark (1-based from user)
     * @throws DukeException If the argument cannot be parsed as an integer
     */
    public MarkCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments); // keep as 1-based
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Executes the command: marks the task at the given index as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.getSize()) {
            throw new DukeException("Task number out of range: " + index);
        }

        tasks.markTask(index); // markTask expects 1-based index
        ui.showTaskStatusChanged(tasks.getTask(index), true);

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

