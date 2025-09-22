package jerome;

import java.util.ArrayList;
import java.util.List;

import jerome.task.Task;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing list of tasks.
     * @param loadedTasks Tasks loaded from storage.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at the specified index.
     * @param ind Index of the task to retrieve (0-based).
     * @return Task at the given index.
     */
    public Task get(int ind) {
        return tasks.get(ind);
    }

    /**
     * Removes and returns the task at the specified index.
     * @param ind Index of the task to remove (0-based).
     * @return The removed task.
     */
    public Task remove(int ind) {
        return tasks.remove(ind);
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the full list of tasks.
     * @return A List of all tasks.
     */
    public List<Task> getAll() {
        return tasks;
    }
}
