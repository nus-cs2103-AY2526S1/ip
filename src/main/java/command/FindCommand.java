package command;

import java.util.List;

import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to find tasks containing a specific SEARCH_TERMS string.
 * Searches through task descriptions and displays matching results or a no-results message.
 */
public class FindCommand extends Command {

    private final String[] SEARCH_TERMS;

    /**
     * Constructs a FindCommand with the specified SEARCH_TERMS string.
     *
     * @param search the SEARCH_TERMS string to match against task descriptions
     */
    public FindCommand(String... search) {
        super(CommandType.FIND);
        this.SEARCH_TERMS = search;
    }

    /**
     * {@inheritDoc}
     * Executes the find command by searching for tasks containing the SEARCH_TERMS string
     * and displaying the results. Shows a message if no matching tasks are found.
     *
     * @param taskList the task list to SEARCH_TERMS through
     * @throws Exception if an error occurs during the SEARCH_TERMS operation
     */
    @Override
    public String execute(TaskList taskList) throws Exception {
        List<Task> searchResult = taskList.findTasks(SEARCH_TERMS);
        if (searchResult.isEmpty()) {
            return UI.showMessage("No result found");
        }
        StringBuilder ret = new StringBuilder();
        ret.append(UI.showMessage("Here are the matching tasks in your list:"));
        for (Task task : searchResult) {
            ret.append(UI.showMessage(task.toString()));
        }
        return ret.toString();
    }
}
