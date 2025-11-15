package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructs FindCommand object by encapsulating searchTerm
     * @param searchTerm String subsequence to be found
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes command for search function
     * @param tasks the task list to operate on
     * @param ui the user interface for interaction
     * @param storage the storage system for persistence
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(searchTerm, ui);
    }
}
