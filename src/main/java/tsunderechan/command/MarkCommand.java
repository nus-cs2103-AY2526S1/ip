package tsunderechan.command;

import java.io.IOException;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to mark a task when executed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Instantiates a MarkCommand object.
     *
     * @param index Index of the Task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String s = tasks.mark(index);
        assert s != null : "string for marking task should not be null";
        storage.save(tasks);
        return s;
    }
}
