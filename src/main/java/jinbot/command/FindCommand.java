package jinbot.command;

import java.util.ArrayList;

import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to find tasks in the task list which description
 * contains a given keyword (case-insensitive).
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        assert !keyword.isBlank() : "keyword should not be blank";
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in the given task list whose description contains the keyword
     * (case-insensitive).
     *
     * @param taskList The task list to search within.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findMatchingTasks(TaskList taskList) {
        ArrayList<Task> matches = new ArrayList<>();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Formats the given list of matching tasks into a user-friendly string.
     *
     * @param matches The list of tasks that matched the search keyword.
     * @return A formatted string showing all matching tasks in numbered order,
     */
    public String formatMatches(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }
        StringBuilder resultBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            resultBuilder.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return resultBuilder.toString().trim();
    }


    /**
     * Executes the find command by searching through the given task list.
     * Tasks which descriptions contain the keyword (case-insensitive)
     * are displayed in a numbered list. If no tasks match, a message is shown.
     *
     * @param ui       The UI instance used to interact with the user.
     * @param taskList The task list to search within.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        ArrayList<Task> matches = findMatchingTasks(taskList);
        String response = formatMatches(matches);
        ui.printBox(response);
        return response;
    }
}
