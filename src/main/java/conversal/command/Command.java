package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Represents a user command that can be executed against the current task list,
 * storage, and UI.
 * Implementations perform the actual action for the command.
 */
public interface Command {

    /**
     * Executes this command using the provided collaborators.
     *
     * @param tasks   the task list to read or modify
     * @param storage the storage component for persistence
     * @param ui      the UI used to show messages to the user
     * @throws ConversalException if command execution fails
     */
    void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException;

    /**
     * Returns {@code true} if executing this command should terminate the app.
     *
     * @return true to exit; false otherwise
     */
    boolean isExit();
}
