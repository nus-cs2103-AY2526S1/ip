package ming.command;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.mark(index);
        storage.save(tasks.getTasks());
        return ui.showMark(task);
    }

    @Override
    public String getType() {
        return "ChnageMarkCommand";
    }
}
