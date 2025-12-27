package ip.commands;

import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Default command to fallback when input does not match any other command
 */
public class RepeatCommand implements Command {

    /**
     * Calls UI to display message for unknown command
     *
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) {
        return ui.showRepeat(input);
    }
}
