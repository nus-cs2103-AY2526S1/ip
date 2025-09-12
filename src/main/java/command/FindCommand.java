package command;

import application.Storage;
import application.TaskList;
import application.Ui;

/**
 * Command implementation for finding tasks by keyword search.
 * Searches through task descriptions for matching keywords and displays results.
 */
public class FindCommand extends Command {
    /** The keyword to search for in task descriptions */
    private final String keyword;
    
    /**
     * Constructs a new FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * Executes the find command by searching for tasks containing the keyword.
     * Displays all matching tasks with their original indices.
     *
     * @param tasks The task list to search through.
     * @param ui The user interface for displaying results.
     * @param storage The storage component (not used for find operation).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        performSearch(tasks, ui);
    }
    
    /**
     * Performs the search operation and displays results.
     * Separates the search logic for clarity and potential future enhancements.
     * Storage parameter is not used as searching doesn't modify data.
     * 
     * @param tasks The task list to search through
     * @param ui The user interface for displaying results
     */
    private void performSearch(TaskList tasks, Ui ui) {
        ui.printMatchingTasks(tasks.findTasks(keyword));
    }
    
    @Override
    public boolean isBye() {
        return false; // Find commands do not terminate the application
    }
}