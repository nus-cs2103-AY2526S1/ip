package hachi;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks, providing methods to add, delete, and manage tasks.
 * This class stores a collection of Task objects in an ArrayList, allowing
 * for dynamic modification of the task list.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a new, empty TaskList.
     * This initializes the tasks list as an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** returns tasks
     */

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to be added to the list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified position.
     * If the position is invalid (i.e., out of bounds), a runtime exception is thrown.
     *
     * @param position the index of the task to be removed from the list
     * @throws RuntimeException if the position is invalid or out of bounds
     */
    public void delete(int position) {
        try {
            this.tasks.remove(position);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns task at specified index
     * @param idx index of returned task
     * @return
     */
    public Task getTaskAt(int idx) {
        try {
            return this.tasks.get(idx);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches the list of tasks for those whose descriptions contain the given keyword
     * (case-insensitive) and returns a formatted string of the results.
     *
     * <p>If no tasks match the keyword, the returned string will state that no matching
     * task was found. Otherwise, the returned string will list all matching tasks with
     * their index numbers.</p>
     *
     * @param keyword the word or phrase to search for within task descriptions
     * @return a formatted string showing either the matching tasks or a message if none were found
     */
    public String findTasksByKeyword(String keyword) {
        StringBuilder sb = new StringBuilder();
        List<Task> matchedTasks = tasks.stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matchedTasks.isEmpty()) {
            sb.append("no matching task found");
            return sb.toString();
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append(String.format("%d. %s\n", i + 1, matchedTasks.get(i).toString()));
            }
            return sb.toString();
        }
    }
}
