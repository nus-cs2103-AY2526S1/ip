package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to delete a task given a task number.
 * <p>
 * This class is responsible for calling the delete task method implemented by
 * TaskList, given the task number to be deleted.
 */
public class DeleteCommand extends Commands {
    private final TaskList tasks;
    private final int taskNum;

    /**
     * Constructs a new instance of the DeleteCommand class with the given TaskList and task number to delete.
     * @param tasks The current list of tasks.
     * @param taskNum The task number (0-th index) to be deleted from the TaskList.
     */
    public DeleteCommand(TaskList tasks, int taskNum) {
        this.tasks = tasks;
        this.taskNum = taskNum;
    }

    @Override
    public String execute() throws AngusException {
        if (this.taskNum <= 0) {
            throw new AngusException("Task number must be a positive integer!");
        }
        return tasks.deleteTask(taskNum);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
