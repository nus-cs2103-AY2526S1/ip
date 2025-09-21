package hachiware;

import java.util.ArrayList;
//AI helped with generating javadoc comments

/**
 * Represents a command that searches for tasks containing a specific keyword.
 * <p>
 * The {@code FindCommand} filters the tasks in the {@link TaskList} that contain
 * the specified keyword in their description. It returns a formatted string
 * of matching tasks or a message indicating no matches were found.
 * </p>
 */
public class FindCommand extends Command {
    /** Message returned when no tasks match the keyword. */
    public static final String SEARCH_NO_MATCH = "No matching tasks found for: ";

    /** Header message for search results. */
    public static final String SEARCH_RESULTS_HEADER = "Here are the matching tasks in your list:\n";

    /** The keyword to search for in task descriptions. */
    private final String keyword;



    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since searching does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find command by validating the keyword, filtering tasks
     * containing the keyword, and returning a formatted result string.
     *
     * @param tasks     the task list to search
     * @param ui        the user interface (not used in this command)
     * @param storeFile the storage handler (not used in this command)
     * @return a formatted string containing the matching tasks or a no-match message
     * @throws HachiwareException if the keyword is null or blank
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storeFile) throws HachiwareException {

        validateKeyword();

        ArrayList<Task> filteredTasks = filterTasks(tasks);

        return formatResults(filteredTasks);

    }

    /**
     * Validates that the keyword is not null or blank.
     *
     * @throws HachiwareException if the keyword is null or blank
     */
    private void validateKeyword() throws HachiwareException {
        if (keyword == null || keyword.isBlank()) {
            throw new HachiwareException("MEOW! You need to provide a keyword to search for!");
        }
    }

    /**
     * Filters the tasks that contain the keyword in their description.
     *
     * @param tasks the task list to filter
     * @return an ArrayList of tasks that match the keyword
     */
    private ArrayList<Task> filterTasks(TaskList tasks) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : tasks.getAll()) {
            if (task.description.contains(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    /**
     * Formats the filtered tasks into a string for display.
     *
     * @param filteredTasks the list of tasks that match the keyword
     * @return a formatted string of matching tasks, or a no-match message
     */
    private String formatResults(ArrayList<Task> filteredTasks) {
        if (filteredTasks.isEmpty()) {
            return SEARCH_NO_MATCH + keyword;
        }
        StringBuilder sb = new StringBuilder(SEARCH_RESULTS_HEADER);
        for (int i = 0; i < filteredTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(filteredTasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}

