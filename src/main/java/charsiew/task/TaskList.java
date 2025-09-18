package charsiew.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    /** The internal list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list at index.
     *
     * @param task The task to be added.
     * @param index The index where task is to be added after.
     */
    public void add(int index, Task task) {
        tasks.add(index, task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Finds all tasks whose description contains the given keyword.
     * The search is case-insensitive.
     *
     * @param keyword the keyword to search for
     * @return a {@code TaskList} containing all matching tasks
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matching = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matching.add(t);
            }
        }
        return new TaskList(matching);
    }
}
