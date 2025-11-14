package shef.command;

import shef.exception.ShefException;
import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Command class that all commands inherit from.
 */
public abstract class Command {
    protected String args;

    public Command(String args) {
        this.args = args;
    }

    /**
     * Executes the command that this object represents.
     *
     * @param tasks The TaskList object.
     * @param storage The Storage object that will update the save file.
     * @throws ShefException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws ShefException;

    /**
     * Checks if command is an exit command.
     * @return true if and only if this object is an instance of ExitCommand.
     */
    public abstract boolean isExit();
}
