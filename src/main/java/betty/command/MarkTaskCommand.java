package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents the command object that marks the task in task list as completed
 */
public class MarkTaskCommand extends Command {

    private int taskNum;

    /**
     * Constructs the command object with a given task number to be marked done in task list
     * @param taskNum index of the task to be marked done in task list
     */
    public MarkTaskCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by marking the task in task list and storage as completed, printing message from ui
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
        taskList.markDone(this.taskNum);
        storage.store(taskList);
        return ui.markDone(taskList, this.taskNum);
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
