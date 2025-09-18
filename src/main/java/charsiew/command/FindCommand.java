package charsiew.command;

import java.io.IOException;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command that finds and lists all tasks
 * whose descriptions contain the specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given search keyword.
     *
     * @param keyword the keyword to search for in the task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the task list for tasks
     * containing the keyword, then displaying the results.
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying results
     * @param storage the storage used to save tasks (not modified by this command)
     * @throws IOException if an I/O error occurs when accessing storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList matching = tasks.findTasks(keyword);
        return ui.showTaskList(matching);
    }

    /**
     * @return false, a Find Command cannot be undone.
     **/
    @Override
    public boolean canUndo() {
        return false;
    }
}
