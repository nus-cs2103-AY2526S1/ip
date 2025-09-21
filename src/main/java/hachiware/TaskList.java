package hachiware;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Hachiware chatbot.
 * Provides operations to add, remove, and retrieval of tasks.
 */
public class TaskList {

    /** ArrayList containing all the current tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks an existing list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "TaskList cannot be initialized with null tasks";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        assert task != null : "Cannot add null task";
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) {
        return tasks.get(index);
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
     * Returns the list of all tasks.
     *
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getAll() {
        for (Task t : tasks) {
            assert t != null : "TaskList contains null task";
        }
        return tasks;
    }
}
