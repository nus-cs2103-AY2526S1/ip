package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command used by Jobe to execute user's commands.
 */
public abstract class Command {
    
    /**
     * Executes command.
     *
     * @param tasklist Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If error occurs.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws JobeException;
    
    /**
     * Checks whether the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public abstract boolean isExit();
    
}
