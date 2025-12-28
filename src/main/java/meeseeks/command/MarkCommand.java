package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("Task index out of range!");
            return;
        }
        
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + task);
    }
}
