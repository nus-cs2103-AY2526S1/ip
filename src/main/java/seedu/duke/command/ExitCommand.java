package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Shows the goodbye message.
     *
     * @param tasks   The task list (unused).
     * @param ui      UI for output.
     * @param storage Persistent storage (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns {@code true} to indicate the application should terminate.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
