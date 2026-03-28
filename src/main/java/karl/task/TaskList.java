package karl.task;

import karl.exception.KarlException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the in-memory list of tasks.
 * Provides operations to add, delete, find, mark, unmark and access tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList instance.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with pre-existing tasks.
     * @param tasks initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     * @param index zero-based index
     * @return the removed task
     * @throws KarlException if index is out of bounds
     */
    public Task delete(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        return tasks.remove(index);
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks; empty list if none found
     */
    public List<Task> find(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    /**
     * Marks the task at index as done.
     * @param index zero-based index
     * @throws KarlException if index out of bounds
     */
    public void mark(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at index as not done.
     * @param index zero-based index
     * @throws KarlException if index out of bounds
     */
    public void unmark(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Gets the task at specified index.
     * @param index zero-based index
     * @return the task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks.
     * @return size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the internal list of tasks.
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
