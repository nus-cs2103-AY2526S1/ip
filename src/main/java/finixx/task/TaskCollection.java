package finixx.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Task objects, providing methods to add, remove, retrieve, and search tasks.
 * Also supports converting tasks to a format suitable for saving to a file.
 */
public class TaskCollection {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskCollection.
     */
    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskCollection with an initial list of tasks.
     *
     * @param tasks The initial list of tasks
     */
    public TaskCollection(List<Task> tasks) {
        assert tasks != null : "Initial tasks list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the collection.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        this.tasks.add(task);
    }

    /**
     * Removes a task from the collection by index.
     *
     * @param index Index of the task to remove
     * @throws IndexOutOfBoundsException If index is out of range
     */
    public void removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the collection by index.
     *
     * @param index Index of the task to retrieve
     * @return Task at the specified index
     * @throws IndexOutOfBoundsException If index is out of range
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return this.tasks.get(index);
    }

    /**
     * Returns all tasks in the collection.
     *
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the collection.
     *
     * @return Size of the task collection
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword Keyword to search for
     * @return List of matching tasks
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
    }

    /**
     * Converts the tasks in the collection to a format suitable for saving to a file.
     *
     * @return List of strings representing the tasks for storage
     */
    public List<String> toSaveFormat() {
        return this.tasks.stream()
                .map(Task::toSaveFormatString)
                .toList();
    }
}
