package task;

import java.util.ArrayList;

import exception.GenieweenieException;

/**
 * Represents a list of tasks.
 * Provides operations to add, retrieve, delete, and inspect tasks.
 */
public class TaskList {

    /** List storing the tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates a task list initialized with an existing list of tasks.
     *
     * @param tasks the initial tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list (alternate method).
     *
     * @param task the task to add
     */
    public void add(Task task) throws GenieweenieException {
        if (task == null) {
            throw new GenieweenieException("Cannot add null task.");
        }
        tasks.add(task);
    }

    /**
     * Retrieves a task at a given index.
     *
     * @param index the index (0-based)
     * @return the task
     */
    public Task getTask(int index) throws GenieweenieException {
        if (index < 0 || index >= tasks.size()) {
            throw new GenieweenieException("Invalid task number: " + (index + 1));
        }
        return tasks.get(index);
    }

    /**
     * Retrieves a task at a given index (alternate method).
     *
     * @param index the index (0-based)
     * @return the task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes and returns a task at a given index.
     *
     * @param index the index (0-based)
     * @return the removed task
     */
    public Task deleteTask(int index) throws GenieweenieException {
        if (index < 0 || index >= tasks.size()) {
            throw new GenieweenieException("Invalid task number: " + (index + 1));
        }
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
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain a keyword in their description.
     *
     * @param keyword the keyword to search
     * @return list of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty";
        ArrayList<Task> matching = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matching.add(task);
            }
        }
        return matching;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index index of the task (0-based)
     */
    public void markTask(int index) throws GenieweenieException {
        getTask(index).markAsDone();
    }

    /**
     * Unmarks the task at the given index (sets as not done).
     *
     * @param index index of the task (0-based)
     */
    public void unmarkTask(int index) throws GenieweenieException {
        getTask(index).markAsNotDone();
    }
}
