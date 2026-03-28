package chani;

import java.util.ArrayList;
import java.util.List;

import chani.tasks.Task;

/**
 * Represents a list of {@link Task} objects and provides operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList from an existing list of tasks.
     * @param tasks List of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets a task by its 1-based indexing.
     * @param taskNumber The 1-based index of the tasks displayed.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is less than 1 or greater than the list size.
     */
    public Task get(int taskNumber) {
        boolean isOutsideMinRange = taskNumber < 1;
        boolean isOutsideMaxRange = taskNumber > tasks.size();

        if (isOutsideMinRange || isOutsideMaxRange) {
            throw new IndexOutOfBoundsException(
                    "Invalid task number " + taskNumber + ". Must be between 1 and " + tasks.size()
            );
        }
        int i = taskNumber - 1;
        return this.tasks.get(i);
    }

    /**
     * Removes a task from the list.
     * @param task The task to remove.
     */
    public void remove(Task task) {
        this.tasks.remove(task);
    }
    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Finds tasks containing the given keyword in their description.
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public List<Task> find(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns all tasks in the list.
     * @return The list of all tasks.
     */
    public List<Task> getAllTasks() {
        return this.tasks;
    }
}
