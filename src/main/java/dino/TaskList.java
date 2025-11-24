package dino;

import java.util.ArrayList;

/**
 * Represents a list of tasks and operations to manage the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /** Initialises a new tasklist */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Constructs a tasklist with an existing one */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index the index of the task to remove
     * @return the removed Task
     * @throws ArrayIndexOutOfBoundsException if the task index is invalid
     */
    public Task removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException("Invalid task index.");
        }
        return tasks.remove(index);
    }

    /** Adds a task to the list. */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Obtains the task at the specified index.
     *
     * @param index the index of the task to get
     * @return the task at the given index
     * @throws ArrayIndexOutOfBoundsException if the given index is invalid
     */
    public Task get(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException("Invalid task index.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /** Returns a list of tasks containing the required keyword.
     *
     * @param keyword the word to be searched
     * @return task list with all tasks containing the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    public ArrayList<Task> getTaskListAsArray () {
        return this.tasks;
    }
}
