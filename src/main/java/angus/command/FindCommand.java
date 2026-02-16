package angus.command;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to find tasks with descriptions containing the filter argument provided.
 */
public class FindCommand extends Commands {
    private final TaskList tasks;
    private final String filter;

    /**
     * Constructs a new instance of the FindCommand class given the TaskList and filter argument.
     * @param tasks The current list of task the user has.
     * @param filter The argument used to filter the tasks.
     */
    public FindCommand(TaskList tasks, String filter) {
        this.tasks = tasks;
        this.filter = filter;
    }

    @Override
    public String execute() throws AngusException {
        return tasks.findTask(this.filter);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
