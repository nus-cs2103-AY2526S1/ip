package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed by the chatbot.
 * Subclasses of {@code Command} define specific types of user commands
 */
public abstract class Command {

    /**
     * Executes this command with the given task list, user interface, and storage.
     *
     * @param tasks   the task list to be modified or accessed
     * @param ui      the user interface to display messages to the user
     * @param storage the storage to save any changes to persistent storage
     * @return a response message to be displayed to the user
     */

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command will exit the application.
     *
     * @return {@code true} if the command exits the application, {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}
