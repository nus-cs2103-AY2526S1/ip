package lux.commands;

import lux.data.AliasList;
import lux.data.Task;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that deletes a task from the task list by index.
 *
 * <p>The argument should be a numeric index referring to the position of
 * the task in the {@link lux.data.TaskList}.
 */
public class DeleteCommand extends Command {
    private String argument;

    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Remove the task at the specified index and return a confirmation
     * message. Throws {@link lux.exception.LuxException} when the argument
     * is invalid or out of range.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Please specify the task number you want to delete.");
            }

            Task task = tasks.getTasks().remove(idx);
            return ui.deleteTask(task);
        } catch (NumberFormatException e) {
            throw new LuxException("Please specify the task number you want to delete.");

        }
    }

    public boolean isExit() {
        return false;
    }
}
