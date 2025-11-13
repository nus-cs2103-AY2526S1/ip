package john.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Mutable container for Task objects.
 * <p>
 * Responsibilities:
 * - Maintain an ordered list of tasks.
 * - Provide basic operations to add, access, remove, and query tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a TaskList backed by the provided list.
     * The list reference is stored directly; further modifications through
     * this TaskList will affect the same list instance.
     *
     * @param tasks initial list to use as the backing store
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList with a new backing list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Appends a task to the end of the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        assert task != null : "cannot add null task";
        this.tasks.add(task);
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param i index of the task to return
     * @return the task at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int i) {
        assert i >= 0 && i < tasks.size() : "index out of range for get";
        return tasks.get(i);
    }

    /**
     * Returns true if the list contains no tasks.
     *
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return current size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes and returns the task at the given zero-based index.
     *
     * @param i index of the task to remove
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task remove(int i) {
        assert i >= 0 && i < tasks.size() : "index out of range for remove";
        return tasks.remove(i);
    }

    public void set(int idx, Task updated) {
        tasks.set(idx, updated);
    }
}
