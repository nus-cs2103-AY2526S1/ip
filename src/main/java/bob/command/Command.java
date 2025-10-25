package bob.command;

import bob.storage.Storage;
import bob.task.TaskManager;
import bob.ui.Ui;

/**
 * Represents a abstract class for command
 */
public abstract class Command {

    public abstract String execute(TaskManager t, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
