package v.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks with operations to manage them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the given tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        // Assertion: tasks list should not be null
        assert tasks != null : "Tasks list cannot be null";
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        // Assertion: task should not be null
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws DukeException If the index is invalid.
     */
    public Task remove(int index) throws DukeException {
        // Assertion: index should be non-negative
        assert index >= 0 : "Index should be non-negative";
        try {
            Task removedTask = tasks.remove(index);
            // Assertion: removed task should not be null
            assert removedTask != null : "Removed task should not be null";
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That task doesn't exist. Use 'list' to see all tasks.");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     * @return The marked task.
     * @throws DukeException If the index is invalid.
     */
    public Task markAsDone(int index) throws DukeException {
        // Assertion: index should be non-negative
        assert index >= 0 : "Index should be non-negative";
        try {
            Task task = tasks.get(index);
            // Assertion: task should not be null
            assert task != null : "Task should not be null";
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That task doesn't exist. Use 'list' to see all tasks.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     * @throws DukeException If the index is invalid.
     */
    public Task unmarkAsDone(int index) throws DukeException {
        // Assertion: index should be non-negative
        assert index >= 0 : "Index should be non-negative";
        try {
            Task task = tasks.get(index);
            // Assertion: task should not be null
            assert task != null : "Task should not be null";
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That task doesn't exist. Use 'list' to see all tasks.");
        }
    }

    /**
     * Gets a task by its index.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        // Assertion: index should be non-negative and within bounds
        assert index >= 0 && index < tasks.size() : "Index should be within valid range";
        Task task = tasks.get(index);
        // Assertion: task should not be null
        assert task != null : "Task should not be null";
        return task;
    }

    /**
     * Gets all tasks in the list.
     *
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword Keyword to search for.
     * @return A new list containing matching tasks.
     */
    public List<Task> find(String keyword) {
        // Assertion: keyword should not be null
        assert keyword != null : "Keyword cannot be null";
        String k = keyword.toLowerCase();
        List<Task> results = tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(k))
                .toList();
        // Assertion: results should not be null
        assert results != null : "Search results should not be null";
        return results;
    }

    /**
     * Gets all completed tasks using streams.
     *
     * @return A list of completed tasks.
     */
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isDone)
                .toList();
    }

    /**
     * Gets all pending tasks using streams.
     *
     * @return A list of pending tasks.
     */
    public List<Task> getPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .toList();
    }

    /**
     * Counts completed tasks using streams.
     *
     * @return The number of completed tasks.
     */
    public long countCompletedTasks() {
        return tasks.stream()
                .filter(Task::isDone)
                .count();
    }

    /**
     * Counts pending tasks using streams.
     *
     * @return The number of pending tasks.
     */
    public long countPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .count();
    }

    /**
     * Gets task descriptions as a single string using streams.
     *
     * @return A string containing all task descriptions separated by newlines.
     */
    public String getAllDescriptions() {
        return tasks.stream()
                .map(Task::getDescription)
                .reduce("", (acc, desc) -> acc + desc + "\n");
    }
}
