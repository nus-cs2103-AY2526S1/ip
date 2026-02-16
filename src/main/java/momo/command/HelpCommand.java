package momo.command;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that displays help information to the user.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command by returning a help message containing
     * available commands and their descriptions.
     *
     * @param tasks the task list (not used in this command).
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler (not used in this command).
     * @return the help message describing how to use the application.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        return ui.getHelpMessage();
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, since the help command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
