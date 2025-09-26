package winnie.command;

import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getActiveTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
