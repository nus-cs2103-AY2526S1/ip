package airy;

import java.util.ArrayList;

/**
 * This class holds the task ArrayList
 * Handles logic for the ArrayList
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor that takes in an ArrayList from Storage.load()
     *
     * @param tasks the initial collection of tasks to manage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this ArrayList
     *
     * @param task the task object to add to the ArrayList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task to this ArrayList
     *
     * @param index the index of the task to remove
     * @return the task that was removed from the ArrayList
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for deleteTask";
        return tasks.remove(index);
    }

    /**
     * Marks a task in ArrayList
     *
     * @param index mark task as completed
     * @return the task that was marked as completed
     */
    public Task markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for markTask";
        Task t = tasks.get(index);
        t.markCompleted();
        return t;
    }

    /**
     * Unmarks a task in ArrayList
     *
     * @param index mark task as not completed
     * @return the task that was marked as not completed
     */
    public Task unmarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for unmarkTask";
        Task t = tasks.get(index);
        t.markUncompleted();
        return t;
    }

    /**
     * Returns the size of this ArrayList
     *
     * @return the number of tasks in the collection
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Fetches this ArrayList
     *
     * @return the ArrayList containing all managed tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the specified index in the ArrayList.
     *
     * @param index the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for get";
        return tasks.get(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list contains no tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds all tasks that contain the given keyword in their description.
     *
     * @param searchInput the search term to look for in task name
     * @return a new TaskList containing only the tasks that match the search criteria
     */
    public TaskList findTasks(String searchInput) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        assert searchInput != null : "SearchInput should not be null";
        String lowerSearchInput = searchInput.toLowerCase();

        // Iterate over whole tasks ArrayList to see if any match the search input
        for (Task task : tasks) {
            if (task.getTaskName().toLowerCase().contains(lowerSearchInput)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
