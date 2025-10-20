package bobbywasabi.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of Task objects with utility methods to manipulate and query tasks.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Finds all tasks that match a given keyword.
     * Matches are determined by the Task's find method.
     *
     * @param keyword The keyword to search for in tasks.
     * @return A formatted string containing all matching tasks.
     */
    public String findTasksThatMatchKeyword(String keyword) {
        ArrayList<Task> matchingTasks = this
                .stream()
                .filter(task -> task.find(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        return this.convertTasksToString(matchingTasks);
    }

    /**
     * Converts a single task to a formatted string with its index.
     *
     * @param index The 1-based index of the task.
     * @param task  The Task to convert.
     * @return A formatted string representing the task.
     */
    public String convertTaskToString(int index, Task task) {
        assert (index - 1) < this.size() : "Invalid index being converted to task string!";
        return String.format("%d. %s\n",
                index, task);
    }

    /**
     * Converts a list of tasks into a single formatted string.
     *
     * @param tasks The list of Task objects to convert.
     * @return A formatted string representing all tasks.
     */
    public String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder textList = new StringBuilder();

        // iterate over the tasks to add their string representations to the textList
        IntStream
                .range(0, tasks.size())
                .forEach(i -> {
                    Task task = tasks.get(i);
                    textList.append(convertTaskToString(i + 1, task));
                });

        return textList.toString();
    }

    /**
     * Returns a string representation of all tasks stored in the TaskList.
     *
     * @return A formatted string listing all tasks.
     */
    @Override
    public String toString() {
        return this.convertTasksToString(this);
    }
}
