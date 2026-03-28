package chlo.command;

import chlo.exception.ChloException;
import chlo.storage.Storage;
import chlo.task.Task;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Represents an unmark command that unmarks a task in the task list.
 * Unmarks by index.
 */
public class UnmarkCommand extends Command {
    private final String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int i = Integer.parseInt(index) - 1;
            assert i < tasks.size() : "Index must be less than tasks length";
            Task task = tasks.get(i);
            task.markUndone();
            setString(ui.getUnmarkTask(task));
            storage.save(tasks);
        } catch (ChloException e) {
            setString(ui.getError(e.getMessage()));
        }
    }
}
