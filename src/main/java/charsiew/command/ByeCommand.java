package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents the command to exit the CharSiew application.
 * When executed, it shows a farewell message and signals the program to terminate.
 */
public class ByeCommand extends Command {

    /**
     * Executes the Bye command by displaying a farewell message.
     *
     * @param tasks   The TaskList (unused in this command).
     * @param ui      The UI object to show messages.
     * @param storage The Storage object (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    /**
     * Signals that this command exits the program.
     *
     * @return true, indicating that the program should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @return false, a Bye Command cannot be undone.
     **/
    @Override
    public boolean canUndo() {
        return false;
    }
}
