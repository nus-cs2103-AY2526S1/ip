package TaskList;

import tasks.Task;

/**
 * Manages a collection of {@link Task} objects and provides basic operations
 * for adding, retrieving, and deleting tasks.
 *
 * Tasks are stored in a fixed-size array (capacity 100), with indexing starting
 * from 1 to align with user-facing task IDs.
 */
public class TaskList {
    private Task[] task_list;
    private int task_id = 1;

    /** Creates an empty {@code TaskList} with a capacity of 100 tasks. */
    public TaskList() {
        this.task_list = new Task[100];
    }

    /**
     * Updates the internal task ID counter.
     *
     * @param new_id the new ID value to assign
     */
    public void setId(int new_id) {
        this.task_id = new_id;
    }

    /**
     * Clears all tasks and resets the task ID counter to 1.
     */
    public void clear() {
        this.task_list = new Task[100];
        this.task_id = 1;
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param task_id the ID of the task to retrieve
     * @return the {@link Task} at the given ID, or {@code null} if not found
     */
    public Task getTask(int task_id) {
        return task_list[task_id];
    }

    /**
     * Adds a new task to the list and increments the ID counter.
     *
     * @param t the {@link Task} to add
     */
    public void addTask(Task t) {
        task_list[task_id] = t;
        task_id++;
    }

    /**
     * Returns the current task ID counter.
     *
     * @return the next available task ID
     */
    public int getId() {
        return this.task_id;
    }

    /**
     * Deletes the task at the specified index and shifts remaining tasks left.
     * <p>
     * The method also calls {@code dropId()} on shifted tasks to update their
     * internal IDs for consistency.
     * </p>
     *
     * @param del_index the index of the task to remove
     * @return the deleted {@link Task} object
     */
    public Task deleteTask(int del_index) {
        Task t = task_list[del_index];
        for (int i = del_index; i < task_id-1; i++) {
            task_list[i] = task_list[i+1];
            task_list[i].dropId();
        }
        task_id--;
        return t;
    }
}
