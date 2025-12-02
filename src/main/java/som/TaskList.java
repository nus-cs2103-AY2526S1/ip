package som;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import som.task.Task;

/**
 * Manages the collection of tasks in the chatbot.
 * <p>This class encapsulates the internal list of Task objects and provides
 * methods for the list. It maintains the state of the task list and
 * ensures operations are performed safely.</p>
 *
 * @author Darien Tan
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks the initial list of tasks to include.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to be added, must not be null.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the 0-based index of the task to retrieve.
     * @return the Task at the given index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int index) {
        assert index >= 0 : "Index should not be negative";
        assert index < tasks.size() : "Index out of bounds";
        return this.tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the 0-based index of the task to remove.
     * @return the removed Task.
     * @throws SomException if the index is out of range.
     */
    public Task remove(int index) throws SomException {
        assert index >= 0 && index < tasks.size() : "Invalid task index: " + index;
        return this.tasks.remove(index);
    }

    /**
     * Marks the task at the specified index.
     *
     * @param index the 0-based index of the task to mark.
     */
    public void mark(int index) {
        assert index >= 0 && index < tasks.size() : "Invalid task index: " + index;
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index the 0-based index of the task to unmark.
     * @throws SomException if the index is out of range.
     */
    public void unmark(int index) throws SomException {
        assert index >= 0 && index < tasks.size() : "Invalid task index: " + index;
        tasks.get(index).markAsUndone();
    }

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return a new list containing all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Finds and displays all tasks that occur with the specified keyword.
     * <p>The user provides a keyword in the format {@code find book}.
     * The method parses the input and searches through the task list the keyword.
     * Matching tasks are printed in a formatted list. If no tasks are found,
     * a friendly message is shown instead.</p>
     * <p>Example usage: find book</p>
     *
     * @param keyword the item to be looked for.
     * @return a List consisting of all matching tasks with the specified date.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }
        return matches;
    }

    public void sortTasks() {
        Collections.sort(tasks);
    }
}
