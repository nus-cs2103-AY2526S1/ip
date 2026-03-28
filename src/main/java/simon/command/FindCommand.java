package simon.command;

import java.util.ArrayList;

import simon.storage.Storage;
import simon.task.Task;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to find tasks that contain a specific keyword.
 * A <code>FindCommand</code> object searches through task descriptions for the given keyword.
 */
public class FindCommand extends Command{
    private final String taskName;
    private final ArrayList<Task> matchingTasks = new ArrayList<Task>();

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param taskName The keyword to search for in task descriptions.
     */
    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the find command by searching through all tasks for those containing
     * the keyword in their description and setting the response with matching tasks.
     *
     * @param tasks The task list to search through.
     * @param ui The user interface for displaying the search results.
     * @param storage The storage system (unused for find command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (Task t : tasks.getTasks()) {
            String[] words = t.getDescription().trim().split(" ");
            for (String word : words) {
                if (word.toLowerCase().contains(taskName.toLowerCase())) {
                    matchingTasks.add(t);
                    break;
                }
            }
        }
        setString(ui.showFindTasks(new TaskList(matchingTasks)));
    }
}
