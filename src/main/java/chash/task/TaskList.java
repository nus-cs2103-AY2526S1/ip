package chash.task;

import java.util.ArrayList;
import java.util.List;

/** Represents a collection of tasks. */
public class TaskList {
    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a task list initialized with existing tasks.
     *
     * @param tasks Initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a task by index.
     *
     * @param index Index in list
     * @return Task at index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a task by index.
     *
     * @param index Index in list
     * @return Removed task
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return List size
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getAll() {
        return this.tasks;
    }
}
