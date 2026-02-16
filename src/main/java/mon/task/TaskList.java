package mon.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks with methods to add, remove and access tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList with the given list of tasks.
     * 
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the internal ArrayList of tasks.
     * 
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < tasks.size() : "Index out of bounds: " + index + " >= " + tasks.size();
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     * 
     * @param index the index of the task to remove
     */
    public void removeTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < tasks.size() : "Index out of bounds: " + index + " >= " + tasks.size();
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }
}
