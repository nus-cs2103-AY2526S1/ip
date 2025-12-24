package echo.command;

import echo.Echo;
import echo.tasklist.TaskList;

/**
 * Represents a command to find tasks containing the keyword. A <code>FindCommand</code>
 * is a subtype of <code>Command</code> and stores a string keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(Echo echo, String keyword) {
        super(echo);
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        TaskList filteredList = echo.getTasklist().getTasksWithKeyword(this.keyword);
        return echo.getUi().showKeywordList(filteredList);
    }
}
