package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


/**
 * Abstract class representing a command in the chatbot system.
 * Every command must be able to execute an action using the provided
 * TaskList, Ui, and Storage objects.
 *
 * Subclasses should implement specific actions such as adding, deleting,
 * or marking tasks.
 */
public abstract class Command {

    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     * Each specific command will define its own behavior.
     *
     * @param tasks   the TaskList to perform actions on
     * @param ui      the Ui instance to interact with the user
     * @param storage the Storage instance to save or load tasks
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command should terminate the chatbot session.
     *
     * @return true if the command exits the application, false otherwise
     */
    public abstract boolean isExit();
}

