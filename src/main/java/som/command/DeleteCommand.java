package som.command;

import som.SomException;
import som.Storage;
import som.TaskList;
import som.Ui;
import som.task.Task;

/**
 * Represents a command to delete a task from the task list.
 * <p>When executed, this command adds the specified task and confirms the deletion
 * through the user interface. It also triggers an automatic save of the updated list.</p>
 *
 * @author Darien Tan
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param index the 0-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        Task removed = tasks.remove(index);
        Storage.save(tasks.getAllTasks());
        return ui.showDelete(removed, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
