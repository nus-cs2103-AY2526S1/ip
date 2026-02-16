package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;

/**
 * Represents the {@code mark} command.
 */
public class MarkCommand extends IntermediateCommand {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        Task task = taskList.markAsDone(taskNumber);
        saveTaskList(taskList, storage);
        return "Nice! I've marked this task as done:\n  " + task;
    }
}
