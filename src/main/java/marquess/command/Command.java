package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.exception.MarquessException;

/**
 * Parent class of commands which perform certain operations on tasks, ui or storage.
 */
public abstract class Command {
    /**
     * Execute the command to perform its designated operations.
     *
     * @param storage Storage class to be operated on
     * @param taskList TaskList class to be operated on
     * @return String result of command execution.
     */
    public abstract String execute(Storage storage, TaskList taskList) throws MarquessException;
}
