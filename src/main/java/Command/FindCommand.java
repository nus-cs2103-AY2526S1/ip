package Command;

import JohnException.JohnException;
import Task.TaskList;
import UI.Ui;

/**
 * Command to find tasks based given input.
 */
public class FindCommand extends Command{
    private final String keyword;

    /**
     * Initialises the find command.
     *
     * @param keyword Input to search tasks by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching the task list based on the keyword,
     * then creating and outputting the filter list.
     *
     * @param tasks Current task list.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        TaskList filteredList = tasks.find(keyword);
        ui.showList(filteredList);
    }
}
