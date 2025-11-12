package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command that terminates the chatbot session.
 * When executed, it displays a farewell message and signals the chatbot to exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand:
     * - Shows a farewell message to the user
     *
     * @param tasks   the TaskList (not used in ExitCommand)
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence (not used)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        return "Bye! BEEP B00P BEEP B00P";
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return true because ExitCommand terminates the chatbot
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
