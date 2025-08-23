package bobbywasabi.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides basic operations for managing a collection of {@link Task} objects,
 * including adding, removing, retrieving, and getting a string summary.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the given list of tasks.
     *
     * @param tasks The initial list of tasks to populate the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks currently stored in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param indx The index of the task to retrieve (0-based).
     * @return The {@link Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int indx) {
        return this.tasks.get(indx);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task The {@link Task} to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param indx The index of the task to be removed (0-based).
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void remove(int indx) {
        this.tasks.remove(indx);
    }

    /**
     * Returns a string representation of all tasks in the list.
     * Each task is prefixed with its position in the list (starting from 1).
     *
     * @return A formatted string listing all tasks.
     */
    @Override
    public String toString() {
        StringBuilder textList = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task cur = this.tasks.get(i);

            String curTask = String.format("%d. %s\n", i + 1, cur);
            textList.append(curTask);
        }

        return textList.toString();
    }
}
