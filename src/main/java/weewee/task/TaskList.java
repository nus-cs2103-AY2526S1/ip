package weewee.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks stored internally as an ArrayList.
 * Provides operations to add, remove, retrieve, and query tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks ArrayList<>
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove (0-based).
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Checks if the list has no tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the internal list of all tasks.
     * Changes to the returned list will affect this {@code TaskList}.
     *
     * @return The ArrayList<> of tasks.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

}
