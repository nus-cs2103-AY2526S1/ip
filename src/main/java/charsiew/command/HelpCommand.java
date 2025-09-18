package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents the command to get help information.
 * When executed, it shows a list of all commands available for user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the Help command by displaying a list of command available.
     *
     * @param tasks   The TaskList (unused in this command).
     * @param ui      The UI object to show messages.
     * @param storage The Storage object (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    /**
     * @return false, a Help Command cannot be undone.
     **/
    @Override
    public boolean canUndo() {
        return false;
    }
}
