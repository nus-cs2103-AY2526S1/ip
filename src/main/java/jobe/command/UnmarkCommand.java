package jobe.command;

import jobe.exception.JobeException;
import jobe.parser.Parser;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "unmark".
 */
public class UnmarkCommand extends Command {
    private int index;
    
    public UnmarkCommand(String args) throws JobeException {
        this.index = Parser.parseTaskIndex(args);
    }
    
    /**
     * Unmarks a task corresponding to user's input index,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user is trying to unmark a task which does not exist.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.unmarkTask(this.index);
        storage.saveTasks(taskList);
        ui.showUnmarkResponse(task);
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
