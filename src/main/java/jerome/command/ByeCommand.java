package jerome.command;

import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;

/**
 * Represents a command to exit bot.
 */
public class ByeCommand extends Command {
    /**
     * Shows bye message.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to show bye message.
     * @param storage The storage to save or load task data.
     * @return goodbye message to be shown in the gui.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodbye();
    }
}
