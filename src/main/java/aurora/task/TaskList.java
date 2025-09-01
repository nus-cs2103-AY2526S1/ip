package aurora.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@link Task} objects.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with the given list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list contains no tasks, {@code false} otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
