package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 *
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }

        Task deletedTask = tasks.remove(taskNumber - 1);
        storage.save(tasks.getAll());

        return "Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have " + tasks.getSize()
                + " tasks in the list.";
    }
}