package bestie;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps the mutable list of tasks and exposes high-level operations for the
 * rest of the application.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list that reuses the provided backing list.
     *
     * @param tasks existing tasks loaded from storage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        assert tasks != null : "Backing task list must not be null";
        tasks.add(task);
    }

    /**
     * Removes the task at the specified 0-based index.
     *
     * @param index position of the task to remove
     * @return the removed task
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Removal index must be within bounds";
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the given 0-based index.
     *
     * @param index position of the task
     * @return the task stored at that index
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Access index must be within bounds";
        return tasks.get(index);
    }

    /**
     * Marks the task at the provided index as done.
     *
     * @param index position of the task to update
     */
    public void mark(int index) {
        assert index >= 0 && index < tasks.size() : "Mark index must be within bounds";
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the provided index as not done.
     *
     * @param index position of the task to update
     */
    public void unmark(int index) {
        assert index >= 0 && index < tasks.size() : "Unmark index must be within bounds";
        tasks.get(index).markAsUndone();
    }

    /**
     * Returns the number of tasks currently tracked.
     *
     * @return current task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Exposes the backing list, primarily for persistence.
     *
     * @return mutable backing list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds every task whose description contains the supplied keyword.
     *
     * @param keyword search term provided by the user
     * @return ordered list of matching tasks
     */
    public List<Task> find(String keyword) {
        assert keyword != null : "Search keyword must not be null";
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matchesKeyword(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}