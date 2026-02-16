package core;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages a collection of tasks.
 * Provides methods for adding, removing, marking, and searching tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class.
     * Initializes a task list with existing tasks.
     * @param tasks ArrayList of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks parameter should not be null";
        this.tasks = new ArrayList<>(tasks);
        assert this.tasks != null : "Tasks list should not be null after initialization";
    }

    /**
     * @param task Task to add to the TaskList.
     * @throws IllegalArgumentException if the task is null.
     */
    public void addTask(Task task) {
        String errorMessageForTaskCannotBeNull = "Task cannot be null";
        if (task == null) {
            throw new IllegalArgumentException(errorMessageForTaskCannotBeNull);
        }
        tasks.add(task);
    }

    /**
     * @param index Index of the task to retrieve (0-based).
     * @return The Task at the specified index.
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        String errorMessageForTaskIndexOutOfBounds = "Task index " + (index + 1) + " is out of bounds";
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(errorMessageForTaskIndexOutOfBounds);
        }
        Task task = tasks.get(index);
        assert task != null : "Retrieved task should not be null";
        return task;
    }

    /**
     * @param index Index of the task to remove (0-based).
     * @return The removed Task.
     * @throws IndexOutOfBoundsException
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        String errorMessageForTaskIndexOutOfBounds = "Task index " + (index + 1) + " is out of bounds";
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(errorMessageForTaskIndexOutOfBounds);
        }
        return tasks.remove(index);
    }

    /**
     * @param index Index of the task to mark (0-based).
     * @throws IndexOutOfBoundsException
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsDone();
    }

    /**
     * @param index Index of the task to unmark (0-based).
     * @throws IndexOutOfBoundsException
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).markAsNotDone();
    }

    /**
     * @return Length of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @return Boolean indicating if the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * @return ArrayList of all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }

    /**
     * Searches for tasks containing the specified keyword (case-insensitive) in their descriptions.
     * Returns a new TaskList containing the matching tasks.
     * @param keyword String keyword to search for in task descriptions.
     * @return TaskList of tasks matching the keyword.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }

        assert matchingTasks != null : "Matching tasks list should not be null";
        return matchingTasks;
    }

    /**
     * Sorts the tasks in the TaskList.
     * The sorting order is:
     * 1. Deadlines (sorted chronologically by deadline)
     * 2. Events (sorted chronologically by start time)
     * 3. Todos (sorted alphabetically by description)
     */
    public void sortTasks() {
        Collections.sort(tasks);
    }
}
