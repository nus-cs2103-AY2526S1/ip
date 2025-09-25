package heimerdinger.task;

import java.util.ArrayList;

/**
 * A mutable collection of {@link Task} objects.
 *
 * <p>{@code TaskList} serves as the central container for tasks in the
 * application. It provides methods to add, remove, access, and retrieve
 * tasks in bulk, wrapping an underlying {@link ArrayList}.</p>
 *
 * <p>This class is used by commands to manipulate the user's tasks and
 * by the storage system to persist them.</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * TaskList tasks = new TaskList();
 * tasks.add(new ToDo("buy milk"));
 * tasks.add(new Deadline("submit report", "2025-10-31"));
 * System.out.println(tasks.size()); // 2
 * }</pre>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} backed by an existing list of tasks.
     *
     * <p>Note: the passed list is used directly, not copied. External
     * modifications to it will be reflected in this {@code TaskList}.</p>
     *
     * @param tasks the list of tasks to back this {@code TaskList}
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the end of this list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the zero-based index of the task to remove
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index without removing it.
     *
     * @param index the zero-based index of the task
     * @return the task at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * <p>Note: this exposes the internal {@link ArrayList}, so
     * modifications to the returned list affect this {@code TaskList}.</p>
     *
     * @return the backing list of tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
