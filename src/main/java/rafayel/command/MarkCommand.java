
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Represents a command that marks or unmarks a task as done.
 * The task is identified by its task number (1-based index from user input).
 */
public class MarkCommand extends Command {

    /** Stores the task number of the task to be marked/unmarked. */
    private final int taskNumber;

    /** Whether to mark (true) or unmark (false) the command */
    private final boolean isMark;

    /**
     *  Constructor to mark the task.
     *
     * @param taskNumber string of the task number (check if it is integer).
     * @param isMark whether to mark or unmark the task.
     */
    public MarkCommand(String taskNumber, boolean isMark) throws RafayelException {
        super(isMark ? CommandHandle.CommandType.MARK : CommandHandle.CommandType.UNMARK);

        try {
            this.taskNumber = Integer.parseInt(taskNumber.trim()) - 1;
            this.isMark = isMark;
        } catch (NumberFormatException e) {
            throw new RafayelException("Please state what task to be marked/unmarked.");
        }

    }

    /**
     * Executes the mark/unmark command by updating the task’s completion status.
     *
     * @param tasks the task list.
     * @param storage the storage handler.
     * @return a confirmation message of the updated task.
     * @throws RafayelException if the task number is invalid or state is inconsistent.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (taskNumber < 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }

        Task task = tasks.get(taskNumber);
        if (isMark) {
            if (task.isDone()) {
                throw new RafayelException("Task number " + taskNumber + 1 + " is already marked as done.");
            }
            task.markAsDone();
        } else {
            if (!task.isDone()) {
                throw new RafayelException("Task number " + taskNumber + 1 + " is already unmarked.");
            }
            task.markAsUndone();
        }

        storage.save(tasks.getAll());
        return "Nice! I've marked this task as " + (isMark ? "done" : "not done") + ":\n  " + task.toString();
    }
}
