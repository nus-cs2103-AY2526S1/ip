package phuc.model;

import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations for task management.
 * Provides methods to add, remove, and manipulate tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList from an existing list of tasks
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task by index
     *
     * @param index the 0-based index of the task
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task by index
     *
     * @param index the 0-based index of the task to remove
     * @return the removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets all tasks as a list
     *
     * @return a copy of all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Clears all tasks from the list
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Adds all tasks from another collection to this task list.
     *
     * @param tasks the collection of tasks to add
     */
    public void addAll(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }


}
