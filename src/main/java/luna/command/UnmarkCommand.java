package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;

/**
 * Represents the {@code unmark} command.
 */
public class UnmarkCommand extends IntermediateCommand {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        Task task = taskList.unmarkAsDone(taskNumber);
        saveTaskList(taskList, storage);
        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}
