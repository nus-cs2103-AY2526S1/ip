package jobe.command;

import java.util.List;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "find".
 */
public class FindCommand extends Command {
    private String keyword;
    
    /**
     * Creates a FindCommand object.
     *
     * @param keyword Keyword of task the user is looking for.
     * @throws JobeException If user forgets to input the keyword.
     */
    public FindCommand(String keyword) throws JobeException {
        if (keyword == null || keyword.isBlank()) {
            throw new JobeException("OOPS!!!! You forgot to enter your keyword!");
        }
        this.keyword = keyword;
    }
    
    /**
     * Takes user's task list and filters out tasks which does not contain the user input.
     * Then it prints out the remaining tasks.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user has no tasks in the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        List<Task> list = taskList.findTask(this.keyword);
        ui.showFindResponse(list);
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
