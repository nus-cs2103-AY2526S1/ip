package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that marks a task as done.
 *
 * <p>The command expects a single integer argument representing the index
 * of the task in the current {@link lux.data.TaskList}. The integer must be
 * a valid index; otherwise a {@link lux.exception.LuxException} is thrown.
 */
public class MarkCommand extends Command {
    private String argument;

    public MarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Marks the task at the parsed index and returns the UI message for the
     * marked task.
     *
     * @throws LuxException if the argument cannot be parsed as an integer or
     *                      the index is out of range
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            int idx = Integer.parseInt(argument);
            if (idx < 0 || idx >= tasks.getTasks().size()) {
                throw new LuxException("Task index out of range.");
            }

            tasks.getTasks().get(idx).mark();
            return ui.markTask(tasks.getTasks().get(idx));
        } catch (NumberFormatException e) {
            throw new LuxException("Invalid task index: must be an integer.");

        }
    }

    public boolean isExit() {
        return false;
    }
}
