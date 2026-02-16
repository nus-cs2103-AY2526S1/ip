package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.TaskList;

/**
 * Represents an {@code IntermediateCommand} that adds a new {@code Task}.
 */
public abstract class AddTaskCommand extends IntermediateCommand {
    protected abstract Task getTaskToAdd();

    @Override
    public String execute(TaskList taskList, Storage<TaskList> storage) {
        Task newTask = getTaskToAdd();
        taskList.add(newTask);
        saveTaskList(taskList, storage);
        return "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.getSize()
                + " tasks in the list.";
    }
}
