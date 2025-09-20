package marquess.command;

import marquess.Storage;
import marquess.TaskList;

/**
 * Command to find tasks where description contains search string.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Constructor for command to add task.
     *
     * @param searchString string to be searched.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.findTasks(searchString);
    }
}
