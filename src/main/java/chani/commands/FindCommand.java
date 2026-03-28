package chani.commands;

import java.util.List;

import chani.Storage;
import chani.TaskList;
import chani.Ui;
import chani.tasks.Task;


/**
 * Represents Command to search for tasks in the task list.
 */
public class FindCommand extends Command {
    /**
     * Creates a FindCommand with a command keyword and query arguments.
     * @param command The command keyword ("find").
     * @param args The query string to search for (first argument).
     */
    public FindCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Searches the task list for tasks containing the query and returns the results.
     * @param storage The storage handler (not used here).
     * @return Message listing all matching tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder taskMessage = new StringBuilder();
        List<Task> queries = taskList.find(args[0]);

        for (int i = 1; i <= queries.size(); i++) {
            taskMessage.append(String.format("%d. %s\n", i, queries.get(i - 1)));
        }
        return ui.showQueriedTasks(taskMessage.toString());
    }
}
