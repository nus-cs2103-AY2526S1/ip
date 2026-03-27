package fish.command;

import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Stands for a command that terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     * Displays the exit message to the user.
     *
     * @param tasks   The current TaskList.
     * @param ui      The Ui object for displaying messages.
     * @param storage The Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Returns true to indicate the app is exited.
     * @return Always true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
