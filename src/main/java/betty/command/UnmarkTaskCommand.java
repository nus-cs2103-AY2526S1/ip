package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents command object to unmark a given task in task list
 */
public class UnmarkTaskCommand extends Command {

    private int taskNum;

    /**
     * Constructs a command object to unmark a task in task list given a task number
     * @param taskNum index of task to be unmarked in task list
     */
    public UnmarkTaskCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to unmark a task at given taskNum index in task list and storage,
     * printing message from ui
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
        taskList.markUndone(this.taskNum);
        storage.store(taskList);
        return ui.markUndone(taskList, this.taskNum);
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
