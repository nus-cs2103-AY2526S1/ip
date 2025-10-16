package lebron.command;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to unmark a task as not done in the task list.
 * When executed, it updates the specified task's status and displays a confirmation message.
 */
public class UnmarkCommand extends Command {
    private final String arguments;

    /* Constructor for UnmarkCommand class
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task taskToMark = getTaskIfValid(taskList);
        taskToMark.markAsNotDone();
        return unmarkTaskMessage(taskToMark);
    }

    /**
     * Parses and validates the task number from the command arguments.
     * @param taskList The current task list.
     * @return The task to be unmarked as not done.
     * @throws LeBronException If the task number is invalid or out of range.
     */
    private Task getTaskIfValid(TaskList taskList) throws LeBronException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim()) - 1;
            return taskList.getTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException(Constants.TASK_NUMBER_OUT_OF_RANGE_ERROR_SINGLE);
        } catch (NumberFormatException e) {
            throw new LeBronException(Constants.INVALID_TASK_NUMBER_ERROR_SINGLE);
        }
    }

    /**
     * Formats the message to be displayed after a task is unmarked as not done.
     * @param task The task that was unmarked as not done.
     * @return A formatted string message.
     */
    private String unmarkTaskMessage(Task task) {
        return String.format(Constants.UNMARK_TASK_MESSAGE_FORMAT, task);
    }
}
