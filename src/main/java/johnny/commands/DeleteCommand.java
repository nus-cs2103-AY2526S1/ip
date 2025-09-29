package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.Task;
import johnny.ui.Ui;

/**
 * A command that deletes a particular task in the TaskList at a given index.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Creates a new DeleteCommand instance with the particular index to delete at.
     * 
     * @param index The index to delete at
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return ui.printInvalidNumberError();
        }

        Task deleted = tasks.deleteTask(this.index);
        return ui.printDeleteMessage(tasks, deleted);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
