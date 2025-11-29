package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to exit the Cate application.
 * This command does not perform any storage or task modifications.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Since this command only signals exit, it does not perform any action.
     *
     * @param storage the storage handler (not used)
     * @param tasks   the task list (not used)
     * @param ui      the UI handler (not used)
     * @return an empty string as no feedback is required
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return "";
    }

    /**
     * Indicates that this command signals the application to exit.
     *
     * @return true, since this command terminates the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
