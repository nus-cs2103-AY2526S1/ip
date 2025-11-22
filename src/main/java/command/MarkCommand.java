package command;

import exception.GenieweenieException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GenieweenieException {
        try {
            tasks.getTask(index - 1).markAsDone();
            response = "Nice! I've marked this task as done:\n  "
                    + tasks.getTask(index - 1);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new GenieweenieException("Invalid task number: " + index);
        }
    }
}
