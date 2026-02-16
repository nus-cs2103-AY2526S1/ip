package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Task;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents a command object that deletes the task at a given index in task list
 */
public class DeleteCommand extends Command {

    private int taskNum;

    /**
     * Constructs a delete command object with the given task number
     * @param taskNum task number provided to be deleted from task list
     */
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to delete the task number from task list and storage, printing message from ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        if (this.taskNum > taskList.size() || this.taskNum <= 0) {
            throw new BettyException("OOPS!!! The task number provided is invalid.");
        }
        Task task = taskList.get(taskNum - 1);
        taskList.deleteTask(this.taskNum);
        storage.store(taskList);
        return ui.deleteTask(task, taskList);
    }
    /**
     * Returns whether this command should terminate the program.
     * @return false as program does not terminate after this command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
