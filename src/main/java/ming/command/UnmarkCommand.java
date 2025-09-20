package ming.command;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.unmark(index);
        storage.save(tasks.getTasks());
        return ui.showUnmark(task);
    }

    @Override
    public String getType() {
        return "ChangeMarkCommand";
    }
}
