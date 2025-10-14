package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that marks a task as not completed.
 *
 * The argument should be a numeric index pointing to the task in the
 * current {@link lux.data.TaskList}.
 */
public class UnmarkCommand extends Command {
    private String argument;

    public UnmarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Unmarks the task at the provided index and returns the UI message.
     *
     * @throws LuxException if the index is invalid or cannot be parsed
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Task index out of range.");
            }

            tasks.getTasks().get(idx).unmark();
            return ui.unmarkTask(tasks.getTasks().get(idx));
        } catch (NumberFormatException e) {
            throw new LuxException("Invalid task index: must be an integer.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
