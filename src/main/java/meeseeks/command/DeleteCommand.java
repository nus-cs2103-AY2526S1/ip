package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;


/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.delete(index);
        ui.showMessage("Noted. I've removed this task:\n  " + removed +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
