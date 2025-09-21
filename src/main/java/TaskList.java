package sofi;

import java.util.ArrayList;

/**
 * Manages a collection of tasks, providing operations to add, remove, and modify tasks.
 * Acts as the central data structure for task management in the SOFI application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     * 
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
     * Removes and returns the task at the specified index.
     * 
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index the index of the task
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
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
     * Returns the list of all tasks.
     * 
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done or not done.
     * 
     * @param index the index of the task to mark
     * @param isDone true to mark as done, false to mark as not done
     */
    public void markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Checks if the given index is valid for this task list.
     * 
     * @param index the index to check
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
