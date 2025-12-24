package echo.command;

import echo.Echo;
import echo.tasklist.TaskList;

public class SortCommand extends Command {
    public SortCommand(Echo echo) {
        super(echo);
    }

    @Override
    public String execute() {
        TaskList sortedList = echo.getTasklist().getSortedListBasedOnType();
        return echo.getUi().showList(sortedList);
    }
}
