package aqua.command;

import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Abstract command to add a task to the task list.
 */
public abstract class AddTaskCommand extends Command {
    /**
     * Adds the task to the list of Task and prints task added message.
     *
     * @param task Task to be added
     * @param taskList List that the task should be added to
     * @return Message to be printed
     * @throws StorageException If there is an error with storage
     */
    public String addTask(Task task, TaskList taskList) throws StorageException {
        String s = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                taskList.size() + 1);
        taskList.add(task);
        return s;
    }
}
