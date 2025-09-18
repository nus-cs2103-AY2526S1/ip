package joobot.command;
import java.util.List;

import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;



/**
 * Represents a command that searches for tasks
 * whose descriptions contain the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> matches = tasks.findTasks(keyword);
        if (matches.isEmpty()) {
            return "No matching tasks found for keyword: " + keyword;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
