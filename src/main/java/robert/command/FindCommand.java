package robert.command;

import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * Command to find tasks containing a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        
        if (matchingTasks.size() == 0) {
            return "No matching tasks found.";
        }
        
        return formatMatchingTasks(matchingTasks);
    }

    private String formatMatchingTasks(TaskList matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}