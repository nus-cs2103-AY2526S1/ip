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
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     * 
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task removeTask(int index) {
        // Let the ArrayList handle bounds checking to maintain expected exception behavior
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index the index of the task
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        // Let the ArrayList handle bounds checking to maintain expected exception behavior
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
        // Let the ArrayList handle bounds checking to maintain expected exception behavior
        Task task = tasks.get(index);
        assert task != null : "Task at index " + index + " should not be null";
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

    /**
     * Finds tasks whose descriptions contain the given keyword (case-insensitive).
     * 
     * @param keyword the keyword to search for
     * @return an ArrayList of tasks that match the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Search keyword cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null : "Task in list should not be null";
            assert task.getDescription() != null : "Task description should not be null";
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
