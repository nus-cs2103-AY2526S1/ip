package jooh.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of tasks managed by Jooh.
 * Provides methods to add, remove, fetch, and update tasks,
 * serving as the main in-memory data structure for task management.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes the task at the specified index.
     *
     * @param n The zero-based index of the task to remove.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void removeTask(int n) {
        tasks.remove(n);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param n The zero-based index of the task.
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task getTask(int n) {
        return tasks.get(n);
    }

    /**
     * Returns the entire underlying task list.
     * Modifying the returned list directly will affect this {@code TaskList}.
     *
     * @return The list of tasks.
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param n The zero-based index of the task to mark done.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void markTaskDone(int n) {
        tasks.get(n).markDone();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param n The zero-based index of the task to mark undone.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void markTaskUndone(int n) {
        tasks.get(n).markUndone();
    }

    /**
     * Returns a string representation of the task at the given index.
     *
     * @param n The zero-based index of the task.
     * @return A string describing the task.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public String getTaskAsString(int n) {
        return tasks.get(n).toString();
    }
    /**
     * Returns a list of tasks whose descriptions contain the given keyword.
     *
     * @param desc The keyword to search for.
     * @return A list of matching tasks.
     */
    public List<Task> findTasks(String desc) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDesc().toLowerCase().contains(desc.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }
}
