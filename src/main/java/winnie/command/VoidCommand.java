package winnie.command;

import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

/**
 * A command that does nothing. Mainly used to ensure null-safety.
 */
public class VoidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return; // do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
