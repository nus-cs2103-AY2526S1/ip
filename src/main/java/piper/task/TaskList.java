package piper.task;

import java.util.ArrayList;

/**
 * A mutable list of Task objects with operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks.
     *
     * @return list size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param index position in the list.
     * @return the task at index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index position in the list.
     * @return deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

}
