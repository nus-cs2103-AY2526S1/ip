package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command begging user not to leave.
 */
public class DesperateGoodbyeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showDesperateGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
