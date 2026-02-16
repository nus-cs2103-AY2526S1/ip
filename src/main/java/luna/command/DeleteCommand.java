package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;

/**
 * Represents the {@code delete} command.
 */
public class DeleteCommand extends IntermediateCommand {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        Task task = taskList.delete(taskNumber);
        saveTaskList(taskList, storage);
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + taskList.getSize()
                + " tasks in the list.";
    }
}
