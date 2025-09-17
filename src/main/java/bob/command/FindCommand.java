package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to find a task by description
 */
public class FindCommand extends Command {
    private static final String SEARCH_RESULT_MESSAGE = "Here are the matching tasks in your list: ";
    
    /* User input string to search for */
    private final String input;

    /**
     * Constructs FindCommand.
     *
     * @param input the string to search.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds and displays tasks that match the search keyword.
     *
     * @param tasks   the TaskList to search in
     * @param ui      the user interface for output
     * @param storage the storage (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection(SEARCH_RESULT_MESSAGE + TaskList
                .stringifyTasks(TaskList.filterTasksByKeyword(tasks.getTasks(), input)));
    }
}
