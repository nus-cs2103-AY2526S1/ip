package chlo.command;

import chlo.exception.ChloException;
import chlo.storage.Storage;
import chlo.task.Task;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Represents a mark command that marks a task as done.
 * Marks by index.
 */
public class MarkCommand extends Command {
    private String index;
    public MarkCommand(String index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int i = Integer.parseInt(index) - 1;
            assert i < tasks.size() : "Index must be less than tasks length";
            Task task = tasks.get(i);
            task.markDone();
            setString(ui.getMarkTask(task));
            storage.save(tasks);
        } catch (ChloException e) {
            setString(ui.getError(e.getMessage()));
        }
    }
}
