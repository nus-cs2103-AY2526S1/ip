package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "list".
 */
public class ListCommand extends Command {
    
    /**
     * Prints out user's task list.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user has nothing in the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        if (taskList.size() <= 0) {
            throw new JobeException("OOPS!!!! You have nothing in your list!");
        }
        
        ui.showListResponse(taskList);
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
