package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a command to find a task with specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * @param keyword Keyword to search for in the tasklist.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all available tasks which contains the specified keyword.
     * Outputs the list via gui.
     * @param tasks Tasklist to search for results.
     * @param gui Gui to output results.
     * @param storage Storage for writing and loading files.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {

        if (tasks.isEmpty()) {
            return gui.showError("Find function is useless without any tasks");
        }
        tasks.updateRecurringTasks();
        TaskList results = tasks.findTasks(keyword);

        if (results.isEmpty()) {
            return gui.showMessage("No tasks found matching: " + keyword);
        } else {
            return gui.showSearchResult(results, keyword);
        }
    }
}
