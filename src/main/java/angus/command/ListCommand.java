package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to list out the list of tasks the user has.
 * <p>
 * This class is responsible for calling the getTaskList method from the TaskList class,
 * which prints the formatted list of tasks the user has to the console.
 */
public class ListCommand extends Commands {
    private final TaskList tasks;

    /**
     * Constructs an instance of the ListCommand class with the user's list of tasks.
     * @param tasks The current list of tasks the user has.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() throws AngusException {
        return tasks.getTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
