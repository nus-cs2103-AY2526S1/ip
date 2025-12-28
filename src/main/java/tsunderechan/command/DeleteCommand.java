package tsunderechan.command;

import java.io.IOException;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to delete a task when executed.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates a DeleteCommand object.
     *
     * @param index Index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String s = tasks.delete(index);
        assert s != null : "string for deleting task should not be null";
        storage.save(tasks);
        return s;
    }
}
