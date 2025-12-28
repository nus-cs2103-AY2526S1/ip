package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.getTasksAsString());
    }
}
