package lyra.command;

import lyra.task.Task;
import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

import java.util.ArrayList;

/**
 * Command implementation for finding tasks that match a given keyword.
 * Searches through all tasks and displays those whose descriptions contain the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand with the specified search keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching through all tasks for those
     * whose descriptions contain the specified keyword (case-insensitive).
     * Displays the matching tasks to the user with improved feedback.
     *
     * @param tasks the task list to search through
     * @param ui the user interface for displaying the search results
     * @param storage the storage component (not used in this command)
     * @throws LyraException never thrown by this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ArrayList<Task> matched = new ArrayList<>();
        String needle = keyword.toLowerCase().trim();
        
        // Enhanced search with partial matching
        for (Task task : tasks.getTasks()) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(needle)) {
                matched.add(task);
            }
        }
        
        if (matched.isEmpty()) {
            ui.showMessage("No tasks found matching '" + keyword + "'. Try a different keyword or check your spelling.");
        } else {
            ui.showMatchingTasks(matched);
        }
    }
}


