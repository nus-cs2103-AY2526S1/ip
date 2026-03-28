package sam.task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages a collection of tasks in the task management system.
 * This class provides operations to add, remove, retrieve, and manage tasks
 * in a list structure, serving as the main container for all user tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList with no initial tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks to populate the TaskList
     */
    public TaskList(final ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added to the list
     */
    public void add(final Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Adds multiple tasks to the task list using varargs.
     * 
     * @param tasksToAdd Variable number of tasks to be added to the list
     */
    public void addAll(final Task... tasksToAdd) {
        Collections.addAll(tasks, tasksToAdd);
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index The index of the task to be removed
     * @return The removed task
     */
    public Task remove(final int index) {
        assert index >= 0 && index < tasks.size() : "Index must be valid (0 <= index < size)";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index from the task list.
     *
     * @param index The index of the task to be retrieved
     * @return The task at the specified index
     */
    public Task get(final int index) {
        assert index >= 0 && index < tasks.size() : "Index must be valid (0 <= index < size)";
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }
}
