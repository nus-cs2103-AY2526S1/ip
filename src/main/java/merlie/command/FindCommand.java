package merlie.command;

import java.util.ArrayList;
import java.util.List;

import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Finds tasks whose descriptions contain a specified keyword.
 */
public class FindCommand extends Command {
    private static final String NO_MATCHES_MESSAGE = "no tasks found matching keyword: ";
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks that contain the keyword in
     * their description.
     *
     * @param list TaskList to search in.
     * @param ui Ui object to display output.
     * @param listFile ListFile (not used for find command, can be null).
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
        List<Task> matches = new ArrayList<>();

        findMatches(list, matches);

        if (matches.isEmpty()) {
            ui.errorOutput(NO_MATCHES_MESSAGE + keyword);
        } else {
            ui.showMatches(matches);
        }
    }

    /**
     * Populates the provided list with all tasks from the given task list
     * that contain the keyword.
     *
     * @param list TaskList to search in.
     * @param matches The list that will be filled with matching tasks.
     */
    private void findMatches(TaskList list, List<Task> matches) {
        for (Task task : list) {
            if (hasKeyword(task)) {
                matches.add(task);
            }
        }
    }

    /**
     * Checks whether the specified task's description contains the search keyword.
     * Comparison is case-insensitive.
     *
     * @param task Task to check.
     * @return True if the task's description contains the keyword,
     */
    private boolean hasKeyword(Task task) {
        String description = task.getDescription().toLowerCase();
        String queriedKeyword = this.keyword.toLowerCase();
        return description.contains(queriedKeyword);
    }
}
