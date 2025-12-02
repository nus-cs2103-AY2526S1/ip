package som.command;

import som.SomException;
import som.Storage;
import som.TaskList;
import som.Ui;

/**
 * Represents a command to mark a task as done.
 * <p>When executed, this command updates the completion status of the specified task
 * and displays a confirmation message.</p>
 *
 * @author Darien Tan
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param index the 0-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        tasks.mark(index);
        Storage.save(tasks.getAllTasks());
        return ui.showMarked(tasks.get(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
