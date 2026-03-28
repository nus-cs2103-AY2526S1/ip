package ackermann.functions;

import java.util.ArrayList;
import java.util.List;

import ackermann.exceptions.InvalidTargetException;
import ackermann.task.Task;

/**
 * List to store tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks list of tasks to keep track of
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for Tasklist
     * Initialised by empty array list
     * Used for first time users
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets a task from the list
     *
     * @param i index of the task to retrieve
     * @return A task object
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a task from the list
     *
     * @param task Task to append to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Tags a task
     * @param idx
     * @param tag
     */
    public void tag(int idx, String tag) {
        this.tasks
                .get(idx)
                .tag(tag);
    }

    /**
     * Deletes a task from the list
     * @param id Key to access the task to delete
     */
    public void delete(int id) throws InvalidTargetException {
        try {
            tasks.remove(id);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }
}
