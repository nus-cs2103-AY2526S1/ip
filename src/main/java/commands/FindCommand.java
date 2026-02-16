package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to search for tasks containing a specific keyword.
 * Displays all tasks that match the search criteria.
 */
public class FindCommand extends Command {

    public FindCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        String errorMessageForEmptySearchKeyword = "The search keyword cannot be empty.";
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TronaldDumpException(errorMessageForEmptySearchKeyword);
        }
        String keyword = parts[1].trim();
        TaskList taskList = this.taskList.findTasks(keyword);
        Ui.showMatchingTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
