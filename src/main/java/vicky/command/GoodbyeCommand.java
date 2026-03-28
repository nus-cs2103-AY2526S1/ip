package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to say goodbye to the user.
 */
public class GoodbyeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
