package hermione.storage;

import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Interface for managing storage operations of tasks.
 */
public interface TaskStorage {
    /**
     * Gets the current list of tasks.
     *
     * @return The TaskList containing all tasks.
     */
    TaskList getTasks();

    /**
     * Adds a new task to the storage.
     *
     * @param task The Task object to be added to the storage.
     */
    void addTask(Task task);

    /**
     * Sets the completion status of a task.
     *
     * @param task       The Task object whose completion status is to be updated.
     * @param isComplete The new completion status of the task (true for complete,
     *                   false for incomplete).
     */
    void setTaskCompletion(Task task, boolean isComplete);

    /**
     * Deletes a task from the storage.
     *
     * @param index The index of the task to be deleted.
     * @return The Task object that was deleted.
     */
    Task deleteTask(int index);
}
