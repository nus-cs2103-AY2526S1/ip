package eve;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eve.tasks.Task;

/**
 * Represents the in-memory list of {@link Task} objects managed by Eve.
 * <p>
 * Provides a thin wrapper around an {@link ArrayList} of tasks with
 * convenience methods for adding, deleting, retrieving, and marking tasks.
 * </p>
 */
public class TaskList {
    /** Underlying list storing the tasks. */
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with an existing list of tasks.
     *
     * @param initial tasks to copy into this list
     */
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return true if no tasks are stored, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param idx0 zero-based index of the task
     * @return the task at that index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int idx0) {
        assert idx0 >= 0 && idx0 < tasks.size() : "Index out of bounds";
        return tasks.get(idx0);
    }

    /**
     * Returns the underlying list of tasks.
     * <p>
     * Note: this exposes the internal list directly; callers should
     * avoid mutating it directly unless necessary.
     * </p>
     *
     * @return the list of tasks
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to add
     * @return the same task for convenience
     */
    public Task add(Task t) {
        tasks.add(t);
        return t;
    }

    /**
     * Deletes the task at the given zero-based index.
     *
     * @param idx0 zero-based index of the task
     * @return the deleted task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task deleteAt(int idx0) {
        return tasks.remove(idx0);
    }

    /**
     * Marks the task at the given zero-based index as done or not done.
     *
     * @param idx0 zero-based index of the task
     * @param done true to mark as done, false to mark as not done
     * @return the updated task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task setDone(int idx0, boolean done) {
        Task t = tasks.get(idx0);
        if (done) {
            t.markAsDone();
        } else {
            t.markAsNotDone();
        }
        return t;
    }

    /** Returns tasks whose descriptions contain the needle (case-insensitive). */
    public List<eve.tasks.Task> find(String needle) {
        String n = needle.toLowerCase();
        return tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(n))
                .collect(Collectors.toList());
    }

}
