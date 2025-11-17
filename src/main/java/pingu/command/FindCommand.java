package pingu.command;

import pingu.Storage;
import pingu.Task;
import pingu.TaskList;
import pingu.Ui;
import java.util.ArrayList;

/**
 * Command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        String result = tasks.getMatchingTasksString(matchingTasks);
        return result;
    }
}
