package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents the command to exit the chatbot application.
 * When executed, this command displays a farewell message and
 * signals the application to terminate.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, which displays a goodbye message to the user.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface to display the goodbye message
     * @param storage the storage (not used in this command)
     * @return the goodbye message to be displayed to the user
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }


    /**
     * Indicates that this command will exit the application.
     *
     * @return {@code true} as this command exits the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
