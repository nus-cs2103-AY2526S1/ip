package sage.command;

import sage.storage.Storage;
import sage.task.TaskList;
import sage.task.Ui;
import sage.exception.SageException;

/**
 * Represents an abstract command that can be executed by the chatbot.
 * All concrete command classes must extend this class.
 */
public abstract class Command {
    /**
     * Executes the command with the given TaskList, Ui, and Storage components.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui to interact with the user.
     * @param storage The Storage to save/load tasks.
     * @throws SageException If an error occurs during command execution.
     */
    public abstract String executeAndReturn(TaskList tasks, Ui ui, Storage storage) throws SageException;

    /**
     * Returns true if this command is an exit command, indicating the chatbot should terminate.
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
