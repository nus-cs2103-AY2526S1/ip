package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Instantiates an EmptyCommand object for when user returns empty string.
 */
public class EmptyCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showNoInputError();
        return "";
    }
}
