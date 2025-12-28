package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
