package jone;

import java.util.ArrayList;

/**
 * Represents a list of tasks with basic operations to add, remove, and query tasks.
 */
public class TaskList {
    private static final String MESSAGE_NO_TASKS = "You don't have any tasks yet.";
    private static final String MESSAGE_TASKS_HEADER = "Here are your tasks:";
    private static final String MESSAGE_FIND_HEADER = "Here are the matching tasks in your list:";
    private static final String MESSAGE_NO_MATCH = "No matching tasks found.";

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with existing tasks.
     *
     * @param tasks The existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        assert isValidIndex(index) : "Index out of bounds in remove()";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the index.
     */
    public Task get(int index) {
        assert isValidIndex(index) : "Index out of bounds in get()";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns all tasks as a formatted numbered list.
     */
    public String getTasksAsString() {
        if (tasks.isEmpty()) {
            return MESSAGE_NO_TASKS;
        }
        return formatTasksList(tasks, MESSAGE_TASKS_HEADER);
    }

    /**
     * Returns tasks matching the given keyword as a formatted numbered list.
     *
     * @param keyword The keyword to search for.
     * @return Matching tasks, or a no-match message.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        if (matches.isEmpty()) {
            return MESSAGE_NO_MATCH;
        }
        return formatTasksList(matches, MESSAGE_FIND_HEADER);
    }

    /**
     * Validates whether the given index is within bounds.
     *
     * @param index The index to check.
     * @return True if valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Helper to format a list of tasks with numbering and a header.
     *
     * @param list   The list of tasks to format.
     * @param header The header to prepend.
     * @return A formatted string of tasks.
     */
    private String formatTasksList(ArrayList<Task> list, String header) {
        StringBuilder builder = new StringBuilder(header).append("\n");
        for (int i = 0; i < list.size(); i++) {
            builder.append("  ")
                    .append(i + 1)
                    .append(". ")
                    .append(list.get(i))
                    .append("\n");
        }
        return builder.toString().trim();
    }
}

