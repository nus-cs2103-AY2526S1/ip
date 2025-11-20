package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "todo".
 */
public class TodoCommand extends Command {
    private String taskDescription;
    
    /**
     * Creates a TodoCommand object.
     *
     * @param taskDescription Description of task.
     * @throws JobeException Ff the user forgets to input the task description.
     */
    public TodoCommand(String taskDescription) throws JobeException {
        if (taskDescription == null || taskDescription.isBlank()) {
            throw new JobeException("OOPS!!!! The description of a todo task cannot be empty!");
        }
        
        this.taskDescription = taskDescription;
    }
    
    /**
     * Creates new to do task, adds this created task to tasklist,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If error occurs when creating todo task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.createTodoTask(this.taskDescription);
        storage.saveTasks(taskList);
        ui.showTodoResponse(task, taskList);
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
