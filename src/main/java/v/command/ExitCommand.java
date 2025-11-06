package v.command;

import v.storage.Storage;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by showing a goodbye message.
     *
     * @param tasks   The task list (unused).
     * @param ui      The UI to display the farewell.
     * @param storage The storage (unused).
     * @return True to indicate the application should exit.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return true;
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return True always.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
