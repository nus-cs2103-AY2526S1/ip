package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Deadline;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents command object to add a deadline task
 */
public class AddDeadlineCommand extends Command {

    private Deadline deadlineTask;

    /**
     * Constructs a new command with the given deadline task provided
     * @param deadlineTask task provided to be added on command
     */
    public AddDeadlineCommand(Deadline deadlineTask) {
        super();
        this.deadlineTask = deadlineTask;
    }

    /**
     * Executes the command to add deadline task into task list, storage and shows message by ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addDeadline(this.deadlineTask);
        storage.store(taskList);
        return ui.addTask(this.deadlineTask, taskList);
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
