package burgerburglar;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 * <p>
 * When executed, this command searches through a TaskList for tasks whose description
 * contains the given keyword (case-insensitive) and displays the matching tasks using the UI.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions; must not be null or blank
     * @throws AssertionError if the keyword is null or blank
     */
    public FindCommand(String keyword) {
        assert keyword != null && !keyword.isBlank() : "Keyword must not be null or blank";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        List<Task> matches = findMatchingTasks(tasks);

        return formatMatches(matches);
    }

    /**
     * Finds tasks in the TaskList whose descriptions contain the keyword.
     */
    private List<Task> findMatchingTasks(TaskList tasks) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            assert task != null : "Task in TaskList should not be null";
            if (task.getDescription() != null && task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Formats the matching tasks into a user-friendly message.
     */
    private String formatMatches(List<Task> matches) {
        StringBuilder sb = new StringBuilder();
        if (matches.isEmpty()) {
            sb.append("BURGER FOUND NO MATCH FOR: ").append(keyword).append("\n");
        } else {
            sb.append("BURGER FOUND THESE TASKS:\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
