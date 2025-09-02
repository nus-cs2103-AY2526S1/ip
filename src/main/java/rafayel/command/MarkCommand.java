
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Handles marking or unmarking a task as done/not done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean isMark;

    /**
     *  Constructor to mark the task.
     *
     * @param taskNumber string of the task number (check if it is integer).
     * @param isMark whether to mark or unmark the task.
     */
    public MarkCommand(String taskNumber, boolean isMark) throws RafayelException {
        super(isMark ? Parser.CommandType.MARK : Parser.CommandType.UNMARK);

        try {
            this.taskNumber = Integer.parseInt(taskNumber.trim()) - 1;
            this.isMark = isMark;
        } catch (NumberFormatException e) {
            throw new RafayelException("Please state what task to be marked/unmarked.");
        }

    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }

        Task task = tasks.get(taskNumber - 1);
        if (isMark) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        storage.save(tasks.getAll());
        return "Nice! I've marked this task as " + (isMark ? "done" : "not done") + ":\n  " + task.toString();
    }
}
