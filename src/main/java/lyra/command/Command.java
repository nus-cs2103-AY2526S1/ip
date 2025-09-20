package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Abstract base class for all command implementations in the Lyra application.
 * Defines the interface that all command classes must implement.
 */
public abstract class Command {
    
    /**
     * Executes the command with the given task list, UI, and storage components.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for displaying messages
     * @param storage the storage component for persisting data
     * @throws LyraException if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException;
    
    /**
     * Indicates whether this command should cause the application to exit.
     * Default implementation returns false.
     *
     * @return true if the command should exit the application, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
