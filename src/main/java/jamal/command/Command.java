package jamal.command;

import jamal.exception.InvalidCommandException;
import jamal.task.TaskList;
import jamal.util.Storage;
/**
 * Abstract Parent Command Class
 */

public abstract class Command {


    /**
     * Executes Command on TaskList
     *
     * @param tasks Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     * @throws InvalidCommandException Exception thrown if Command contains idx that is out of range of tasklist
     */
    public abstract String execute(TaskList tasks, Storage storage) throws InvalidCommandException;

    /**
     * Returns true if Command type is ExitCommand
     * If Command is not ExitCommand, returns false
     *
     * @return type ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
