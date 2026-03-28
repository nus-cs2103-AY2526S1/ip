package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Represents a command that can be executed by the chatbot.
 * Each command encapsulates a user command's behavior.
 */
public interface Command {
    /**
     * Executes the command.
     * @param tasks The TaskList to operate on
     * @param ui The Ui instance for interaction
     * @param storage The Storage to save/load data
     * @throws KarlException if an error occurs during command execution
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException;

    /**
     * Returns whether the command is an exit command signalling program termination.
     *
     * @return true if this command signals exit (e.g., 'bye' command)
     */
    boolean isExit();
}
