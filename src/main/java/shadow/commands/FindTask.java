package shadow.commands;

import java.util.List;
import java.util.stream.Collectors;

import shadow.storage.Storage;
import shadow.tasks.Task;



/**
 * Represents a command that finds and displays tasks matching a given keyword.
 */
public class FindTask extends Command {

    public static final String ERROR_MESSAGE = "Usage: find <findString>";

    private final List<Task> tasks;

    /**
     * Constructs a FindTask with a filtered list of tasks.
     *
     * @param tasks the list of tasks that match the search keyword
     */
    private FindTask(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the find command.
     * If no tasks are found, it displays a "Found nothing" message.
     * Otherwise, it displays the list of found tasks with their corresponding index.
     */
    @Override
    public String execute() {
        if (tasks.isEmpty()) {
            return "You don't have any tasks matching your search";
        }
        StringBuilder sb = new StringBuilder("Found:\n");
        for (int i = 0; i < this.tasks.size(); ++i) {
            sb.append(String.format("%d: %s\n", i + 1, this.tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Creates a new FindTask based on the input command parts.
     *
     * @param parts the parts of the command input, where parts[1] should be the search string
     * @return findTask object with filtered tasks
     * @throws IllegalArgumentException if the command format is invalid
     */
    public static FindTask of(String[] parts) throws IllegalArgumentException {
        assert(parts[0].equals("find"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(FindTask.ERROR_MESSAGE);
        }
        try {
            String findString = parts[1];
            return new FindTask(FindTask.filterTasks(Storage.getInstance().getTasks(), findString));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(FindTask.ERROR_MESSAGE);
        }
    }

    /**
     * Filters the given list of tasks by the specified keyword.
     *
     * @param tasks      the list of tasks to filter
     * @param findString the keyword to filter tasks by
     * @return a list of tasks whose names contain the given keyword
     */
    private static List<Task> filterTasks(List<Task> tasks, String findString) {
        return tasks
                .stream()
                .filter(task -> task.contains(findString))
                .collect(Collectors.toList());
    }
}
