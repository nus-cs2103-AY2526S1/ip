package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * A command that marks a task at the specified index in the TaskList as
 * uncompleted.
 */
public class UnmarkCommand extends Command {
    protected int index;

    /**
     * Creates a new MarkCommand instance with a specified index
     * 
     * @param index Index of command to unmark at
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return ui.printInvalidNumberError();
        }

        tasks.markIncomplete(this.index);
        return ui.printUnmarkMessage(tasks, this.index);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
