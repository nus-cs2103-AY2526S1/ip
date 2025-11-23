package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Handles the bye/exit command that terminates the application gracefully.
 */
public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
