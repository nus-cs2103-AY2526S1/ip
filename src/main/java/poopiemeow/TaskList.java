package poopiemeow;

import java.util.ArrayList;

import poopiemeow.task.Task;

/**
 * Manages a collection of tasks in the PoopieMeow application.
 * This class provides methods for adding, removing, retrieving, and querying tasks.
 * It serves as a container and manager for the task collection, providing
 * a clean interface for task operations.
 *
 * @author tch1001
 * @version 1.0
 */
public class TaskList {
    /** The internal collection of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial collection of tasks.
     *
     * @param tasks the initial tasks to populate the list with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     * Creates a new empty ArrayList to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task the task to add to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     * The task is completely removed from the list, and subsequent tasks
     * are shifted to fill the gap.
     *
     * @param index the index of the task to remove (0-based)
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index without removing it.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a reference to the internal task collection.
     * This allows external classes to iterate over or perform bulk operations
     * on the tasks. Note that modifications to the returned list will affect
     * the internal state of this TaskList.
     *
     * @return the ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return the number of tasks currently in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     * The search is case-insensitive.
     * 
     * @param keyword the keyword to search for
     * @return an ArrayList of tasks that match the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(lowerKeyword)) {
                matchingTasks.add(task);
            }
        }
        
        return matchingTasks;
    }
}
