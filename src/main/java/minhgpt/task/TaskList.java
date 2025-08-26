package minhgpt.task;

import java.util.ArrayList;

/**
 * Responsible for all operations done on the list of tasks.
 */
public class TaskList {
    /** The list of tasks itself. */
    private ArrayList<Task> tasks;

    /**
     * Initialise the task list with 'initialTasks'.
     */
    public TaskList(ArrayList<Task> initialTasks) {
        tasks = initialTasks;
    }

    /**
     * Initialise an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Return the number of tasks in list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return the task at index 'index' in list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Add 'task' to list.
     */
    public Task add(Task task) {
        tasks.add(task);
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Mark task with index 'index' in list.
     */
    public Task mark(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmark task with index 'index' in list.
     */
    public Task unmark(int index) {
        tasks.get(index).markAsUndone();
        return tasks.get(index);
    }

    /**
     * Delete task with index 'index' in list.
     */
    public Task delete(int index) {
        Task toBeDeleted = tasks.get(index);
        tasks.remove(index);
        return toBeDeleted;
    }
}
