package jibjab;

import java.util.ArrayList;

/**
 * Manages a collection of tasks in the JibJab application.
 * This class provides functionality to store, add, delete, and modify tasks,
 * as well as track their completion status.
 *
 * @author niyniy123
 */
public class TaskList {
    private static final String EMPTY_LIST_MESSAGE = "You have no tasks in the list";
    private static final String ITEM_NUMBER_SEPARATOR = ".";
    private static final String NEWLINE = "\n";

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the internal task list with no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing collection of tasks.
     * This constructor can be used to initialize the TaskList with
     * pre-existing tasks, such as when loading from storage.
     *
     * @param tasks an ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if there are no tasks in the list, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task the Task object to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add must not be null";
        this.tasks.add(task);
        assert this.tasks.get(this.tasks.size() - 1) == task : "Task should be appended at end";
    }

    /**
     * Checks whether an equivalent task already exists in the list.
     * Equivalence is defined by Task.equals, which compares logical identity
     * (type + description [+ dates for Deadline/Event]) and ignores completion status.
     *
     * @param task the task to check
     * @return true if a logically equivalent task is already present
     */
    public boolean contains(Task task) {
        assert task != null : "Task to check must not be null";
        for (Task t : this.tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param idx the zero-based index of the task to be deleted
     */
    public Task deleteTask(int idx) {
        assert idx >= 0 && idx < this.tasks.size() : "Delete index out of bounds";
        Task task = this.tasks.get(idx);
        this.tasks.remove(task);
        return task;
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param idx the zero-based index of the task to mark as done
     */
    public Task markTaskAsDone(int idx) {
        assert idx >= 0 && idx < this.tasks.size() : "Mark-done index out of bounds";
        Task task = this.tasks.get(idx);
        task.setDone();
        return task;
    }

    /**
     * Marks a task at the specified index as not completed.
     * This can be used to revert a previously completed task back to undone status.
     *
     * @param idx the zero-based index of the task to mark as not done
     */
    public Task markTaskAsNotDone(int idx) {
        assert idx >= 0 && idx < this.tasks.size() : "Mark-not-done index out of bounds";
        Task task = this.tasks.get(idx);
        task.setNotDone();
        return task;
    }

    /**
     * Finds tasks whose string representation contains the given keyword.
     * Prints a header and returns the matching tasks as a newline-separated list.
     *
     * @param keyword substring to search for within each task's toString()
     * @return newline-separated list of matching tasks; empty string if none match
     */
    public String findTasks(String keyword) {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                sb.append(counter).append(ITEM_NUMBER_SEPARATOR).append(task).append(NEWLINE);
                counter++;
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks currently in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a string representation of all tasks in the list.
     * If the list is empty, returns a message indicating no tasks.
     * Otherwise, returns all tasks formatted with one task per line.
     *
     * @return a string containing all tasks separated by newlines,
     *         or a message indicating an empty list
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return EMPTY_LIST_MESSAGE;
        }
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            sb.append(task).append(NEWLINE);
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
