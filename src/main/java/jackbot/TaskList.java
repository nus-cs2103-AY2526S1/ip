package jackbot;

import java.util.ArrayList;
import java.util.List;

import jackbot.task.Task;

/**
 * Mutable in-memory list of {@link Task} objects with 1-based indexing for
 * user-facing operations (get/delete).
 *
 * <p>Intended to be used by the command loop; persistence is handled by
 * {@link Storage}.</p>
 *
 * <p><b>Indexing:</b> methods that accept an index expect it to be <em>1-based</em>
 * (i.e., {@code 1} refers to the first task).</p>
 *
 * <p><b>Thread-safety:</b> not thread-safe.</p>
 *
 */
public class TaskList {
    /** Backing list preserving insertion order. */
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the given tasks.
     * The input is defensively copied.
     *
     * @param tasks initial tasks (order preserved)
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the live backing list.
     * Modifications to the returned list affect this {@code TaskList}.
     *
     * @return the internal list (never {@code null})
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Appends a task to the end of the list.
     *
     * @param task task to add (must not be {@code null})
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes and returns the task at the given 1-based index.
     *
     * @param index 1-based position (1 ≤ index ≤ {@link #size()})
     * @return the removed task
     * @throws JackbotException if the index is out of range
     */
    public Task delete(int index) throws JackbotException {
        int idx = index - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new JackbotException("Task not found");
        }
        return tasks.remove(idx);
    }

    /**
     * Returns the task at the given 1-based index.
     *
     * @param index 1-based position (1 ≤ index ≤ {@link #size()})
     * @return the task at that position
     * @throws JackbotException if the index is out of range
     */
    public Task get(int index) throws JackbotException {
        int idx = index - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new JackbotException("Task not found");
        }
        return tasks.get(idx);
    }

    /**
     * Finds tasks whose description contains the given keyword (case-insensitive).
     * Returns an empty list if the keyword is null/blank or no matches are found.
     * @param keyword the keyword to search for
     * @return the list of tasks matching the keyword
     */
    public List<Task> find(String keyword) {
        List<Task> result = new ArrayList<>();
        if (keyword == null || keyword.isBlank()) {
            return result;
        }
        String k = keyword.toLowerCase();
        for (Task t : tasks) {
            if (t.getDescription() != null && t.getDescription().toLowerCase().contains(k)) {
                result.add(t);
            }
        }
        return result;
    }
}
