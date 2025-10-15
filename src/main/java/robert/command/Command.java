package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save/load tasks.
     * @return Response message.
     * @throws RobertException If command execution fails.
     * @throws IOException     If storage operation fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException;

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}