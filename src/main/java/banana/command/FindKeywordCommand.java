package banana.command;

import banana.exceptions.BananaException;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword in the task list.
 */
public class FindKeywordCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindKeywordCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindKeywordCommand(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword must be a non-empty string";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BananaException {
        TaskList found = tasks.findTasks(keyword);
        if (found.size() == 0) {
            return "No tasks found containing: " + keyword;
        }
        return "Here are the matching tasks:\n" + found.listTasks();
    }
}
