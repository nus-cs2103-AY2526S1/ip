package buddy.command;

import buddy.exception.BuddyException;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;


public interface Command {
    /**
     * Perform the action and return the user-facing message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    /** Return true if this command should exit the app. */
    public default boolean isExit() {
        return false;
    }
}
