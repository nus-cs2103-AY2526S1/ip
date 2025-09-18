package tony.commands;

import java.util.ArrayList;

import tony.exceptions.EmptyTaskException;
import tony.exceptions.TonyException;
import tony.storage.Storage;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to show list of tasks from the task list
 * that contains the keyword specified by the user.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a new {@link ShowTasksOnDateCommand} by parsing the input date.
     *
     * @param keyword The raw input string of the keyword to find tasks that contain it.
     * @throws TonyException If the input is empty.
     */
    public FindCommand(String keyword) throws TonyException {
        if (keyword.isEmpty()) {
            throw new EmptyTaskException("Hey, give me something to work with.");
        } else {
            this.keyword = keyword;
        }
    }

    /**
     * Executes the {@link FindCommand}.
     * Goes through the {@link TaskList} to find tasks that contain the keyword specified by the user.
     * Displays tasks that contain the keyword specified by the user through the {@link UI}.
     *
     * @param tasks The {@link TaskList} that stores tasks.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The tasks found as a {@link String}.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getList()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return ui.showFound(matchingTasks, keyword);
    }
}
