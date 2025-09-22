package klalopz.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 * Allows adding, removing, and resetting tasks, as well as checking if the list is empty.
 */

public class TaskList {
    private List<Task> storage;

    /**
     * Constructs a TaskList initialized with the given list of tasks.
     *
     * @param tasks Initial list of tasks to populate the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.storage = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        storage.add(task);
    }

    public Task getTask(int index) {
        return storage.get(index);
    }

    public void removeTask(int index) {
        storage.remove(index);
    }

    public int size() {
        return storage.size();
    }

    public List<Task> getAll() {
        return storage;
    }

    /**
     * Removes all tasks and resets the internal storage to a new empty list.
     */
    public void reset() {
        storage = new ArrayList<>(100);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list has no tasks, false otherwise.
     */
    public Boolean isEmpty() {
        return this.getAll().isEmpty();
    }
}
