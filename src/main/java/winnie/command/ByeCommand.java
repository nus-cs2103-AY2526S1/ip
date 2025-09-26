package winnie.command;

import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

/**
 * Represents a command to say goodbye.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
