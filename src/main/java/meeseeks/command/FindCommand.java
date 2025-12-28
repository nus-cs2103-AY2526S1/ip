package meeseeks.command;

import meeseeks.task.Task;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;
import meeseeks.storage.Storage;

import java.util.List;

/**
 * Command to search for tasks containing a specific keyword.
 * Performs case-insensitive search on task descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     * 
     * @param keyword the search keyword (will be converted to lowercase and trimmed)
     * @throws IllegalArgumentException if keyword is null or empty
     */
    public FindCommand(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be null or empty");
        }
        this.keyword = keyword.toLowerCase().trim();
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     * 
     * @param tasks the task list to search in
     * @param ui the UI component for displaying results
     * @param storage the storage component (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.findTasksByKeyword(keyword);

        if (matches.isEmpty()) {
            ui.showMessage("No tasks found containing: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                ui.showMessage((i + 1) + "." + matches.get(i));
            }
        }
    }
}
