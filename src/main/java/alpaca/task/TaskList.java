package alpaca.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to modify it.
 */
public class TaskList {

    /** The underlying list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with an existing list of tasks.
     *
     * @param tasks Pre-existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        assert t != null : "Task cannot be null";
        tasks.add(t);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index Index of the task to remove.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If index is invalid.
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index Index of the task.
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If index is invalid.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
