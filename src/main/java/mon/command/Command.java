package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and storage.
     * 
     * @param taskList the task list to operate on
     * @param storage the storage to save changes to
     * @return the result message of the command execution
     * @throws Exception if there's an error executing the command
     */
    public abstract String execute(TaskList taskList, Storage storage) throws Exception;
    
    /**
     * Returns whether this command causes the program to exit.
     * 
     * @return true if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
