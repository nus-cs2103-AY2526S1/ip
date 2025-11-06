package yappy.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to find tasks using a matching substring.
 * When executed, it filters the task list for matching tasks and displays them to the user.
 */
public class FindCommand extends Command {

    private final ArrayList<String> queries;

    /**
     * Constructs a new <code>FindCommand</code> object with the specified query.
     * This constructor initializes the DeleteCommand's Task.
     *
     * @param queries Array of queries to match tasks stored
     */
    public FindCommand(String ... queries) {
        this.queries = new ArrayList<>(Arrays.asList(queries));
    }

    /**
     * Executes the find command on the given task list.
     * Iterates through task list to find tasks whose description has
     * a matching substring with the query string.
     *
     * @param taskList The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {


        ArrayList<Task> filteredList = taskList.getTasks().stream()
                .filter(task -> queries.stream().anyMatch(query -> task.getDescription().contains(query)))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.isEmpty()) {
            return ("No matching task description!");
        } else {
            return "Here are the tasks that match your queries:" + ui.showTasks(filteredList);
        }
    }
}
