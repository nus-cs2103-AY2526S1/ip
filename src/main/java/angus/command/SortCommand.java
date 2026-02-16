package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to print the sorted version of the list of tasks
 * <p>
 * This class is responsible for calling the getSortedList() method in the taskList class
 * which prints the sorted list of tasks
 */
public class SortCommand extends Commands {
    private final TaskList tasks;
    private final String sortType;

    /**
     * Constructs an instance of the SortCommand class with a list of tasks
     * @param tasks The current list of tasks the user has
     */
    public SortCommand(TaskList tasks, String sortType) {
        this.tasks = tasks;
        this.sortType = sortType;
    }

    @Override
    public String execute() throws AngusException {
        return tasks.getSortedList(sortType);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
