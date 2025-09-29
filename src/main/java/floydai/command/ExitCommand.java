package floydai.command;

import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing a farewell message to the user.
     *
     * @param tasks   the TaskList (unused in this command)
     * @param ui      the UI for interacting with the user
     * @param storage the Storage (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return true, because this command ends the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
