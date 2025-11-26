package banana.command;

import banana.exceptions.BananaException;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Command to sort tasks based on a specified criterion.
 */
public class SortCommand extends Command {
    private String sortType;

    /**
     * Constructs a SortCommand with the specified sort type.
     *
     * @param sortType The type of sorting to be performed (e.g., "date").
     */
    public SortCommand(String sortType) {
        assert sortType != null : "Sort type cannot be null";
        this.sortType = sortType;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException {
        assert tasks != null : "TaskList cannot be null in SortCommand.execute";
        tasks.sortByDate();
        return "Tasks sorted by date.\n" + tasks.listTasks();
    }
}
