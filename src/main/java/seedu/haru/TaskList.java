package seedu.haru;

import java.util.*;

/**
 * Represents a list of Tasks objects.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks the list of tasks to be managed
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "List of tasks must not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        assert task != null : "Task must not be null";
        tasks.add(task);
    }

    /**
     * Removes a task at the given index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     * @throws HaruException if the index is invalid
     */
    public Task remove(int index) throws HaruException {
        if (index < 0 || index >= tasks.size()) throw new HaruException("Invalid task number");
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param index the index of the task to mark
     * @throws HaruException if the index is invalid
     */
    public void mark(int index) throws HaruException {
        if (index < 0 || index >= tasks.size()) throw new HaruException("Invalid task number");
        tasks.get(index).mark();
    }

    /**
     * Unmarks the task at the given index (sets it to not completed).
     *
     * @param index the index of the task to unmark
     * @throws HaruException if the index is invalid
     */
    public void unmark(int index) throws HaruException {
        if (index < 0 || index >= tasks.size()) throw new HaruException("Invalid task number");
        tasks.get(index).unmark();
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a new TaskList containing tasks whose names contain the given search string.
     *
     * @param args the string to search for in tasks' names
     *
     * @return a TaskList of tasks that match the search string
     */
    public TaskList find(String args) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.name.toLowerCase().contains(args.toLowerCase())) {
                filtered.add(task);
            }
        }
        return new TaskList(filtered);
    }
}
