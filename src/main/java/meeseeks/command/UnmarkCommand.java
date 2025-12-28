package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("Task index out of range!");
            return;
        }
        
        Task task = tasks.get(index);
        task.markAsNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
    }
}
