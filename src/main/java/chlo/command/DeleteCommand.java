package chlo.command;

import chlo.exception.ChloException;
import chlo.storage.Storage;
import chlo.task.Task;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Represents a delete command that deletes a task from the task list.
 * Remove by index.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            setString(ui.getDeleteTask(task));
            storage.save(tasks);
        } catch (ChloException e) {
            setString(ui.getError(e.getMessage()));
        }
    }
}
