package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to mark a task as done.
 * <p>
 * This command is responsible for calling the markTask method implemented by the TaskList class
 * to mark the task object as done.
 */
public class MarkCommand extends Commands {
    private final TaskList tasks;
    private final int taskNum;

    /**
     * Constructs a new instance of the MarkCommand class with the user's tasks and task number.
     * @param tasks The current list of tasks the user has.
     * @param taskNum The task number of the task to be marked as done.
     */
    public MarkCommand(TaskList tasks, int taskNum) {
        this.tasks = tasks;
        this.taskNum = taskNum;
    }

    @Override
    public String execute() throws AngusException {
        if (this.taskNum < 0) {
            throw new AngusException("Task number must be a positive integer!");
        }
        return tasks.markTask(taskNum);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
