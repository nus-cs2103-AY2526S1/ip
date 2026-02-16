package yang.task;

import java.util.ArrayList;
import java.util.List;

import yang.YangException;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the given tasks.
     *
     * @param initial initial tasks
     */
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index
     *
     * @param idx index of the task
     * @return task at the index
     * @throws AssertionError if index is out of bounds
     */
    public Task get(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index out of bounds";
        return tasks.get(idx);
    }

    /**
     * Adds a new task to the list.
     *
     * @param t task to add
     * @throws YangException if a duplicate task is found
     */
    public void add(Task t) throws YangException {
        boolean dup = tasks.stream().anyMatch(x -> x.sameIdentity(t));
        if (dup) {
            throw new YangException("☹ OOPS!!! Duplicate task:\n  " + t);
        }
        tasks.add(t);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param idx index of the task
     * @return removed task
     */
    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param idx index of the task
     * @throws AssertionError if index is out of bounds
     */
    public void mark(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index given is out of bounds";
        tasks.get(idx).markDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param idx index of the task
     * @throws AssertionError if index is out of bounds
     */
    public void unmark(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index given is out of bounds";
        tasks.get(idx).markUndone();
    }

    public List<Task> asList() {
        return tasks;
    }
}
