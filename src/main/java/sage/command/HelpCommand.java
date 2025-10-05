package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents a command to display help information to the user.
 * Shows available commands and their usage formats.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command and displays available commands and their usage.
     *
     * @param tasks The TaskList (not used in help command).
     * @param ui The Ui object for displaying messages to the user.
     * @param storage The Storage object (not used in help command).
     * @return A string containing help information about available commands.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) {
        return ui.getHelpMessage();
    }

    /**
     * Indicates that the help command does not cause the application to exit.
     *
     * @return false, as help command should not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}