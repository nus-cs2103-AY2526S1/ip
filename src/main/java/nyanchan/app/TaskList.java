package nyanchan.app;

import nyanchan.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manage them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Creates a {@code TaskList} with existing tasks.
     *
     * @param tasks list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task by index.
     *
     * @param index index of task to remove
     * @return the removed task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a task by index.
     *
     * @param index index of task
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return list of tasks
     */
    public List<Task> getAll() {
        return tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
