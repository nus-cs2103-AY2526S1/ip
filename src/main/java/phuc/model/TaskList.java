package phuc.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks with various operations for task management.
 * Provides methods to add, remove, and manipulate tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets a sorted view of tasks by date-time.
     *
     * @param ascending true for ascending order (earliest first), false for descending
     * @return a new sorted ArrayList of tasks
     */
    public ArrayList<Task> getSortedTasks(boolean ascending) {
        return tasks.stream()
                .sorted(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Adds a task to the list
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task by index
     * @param index the 0-based index of the task
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size();
        return tasks.get(index);
    }

    /**
     * Removes a task by index
     * @param index the 0-based index of the task to remove
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size();
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets all tasks as a list
     * @return a copy of all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Clears all tasks from the list
     */
    public void clear() {
        tasks.clear();
    }

    public void addAll(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Finds all the task that contain the keyword
     * Returns the list that contain all the tasks have the keyword
     *
     * @param keyword string input is the key word need to find
     * @return a task list contain all the task that need searching
     */
    public ArrayList<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
