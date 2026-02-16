package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to mark a task as not done.
 * <p>
 * This command is responsible for calling the unmarkTask method implemented by the TaskList class
 * to mark the task object as not done.
 */
public class UnmarkCommand extends Commands {
    private final TaskList tasks;
    private final int taskNum;

    /**
     * Constructs a new instance of the UnmarkCommand class with the user's tasks and task number.
     * @param tasks The current list of tasks the user has.
     * @param taskNum The task number of the task to be marked as not done.
     */
    public UnmarkCommand(TaskList tasks, int taskNum) {
        this.tasks = tasks;
        this.taskNum = taskNum;
    }

    @Override
    public String execute() throws AngusException {
        if (this.taskNum < 0) {
            throw new AngusException("Task number must be a positive integer!");
        }
        return tasks.unmarkTask(taskNum);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
